package com.vbg.map;

import java.util.HashMap;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.AsyncResult;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.kroll.common.TiMessenger;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.TiBlob;
import org.appcelerator.titanium.TiC;
import org.appcelerator.titanium.TiContext;
import org.appcelerator.titanium.TiDimension;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.util.TiConvert;
import org.appcelerator.titanium.view.TiDrawableReference;

import android.graphics.Bitmap;
import android.os.Message;
import android.view.View;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

@Kroll.proxy(creatableInModule = MapModule.class, propertyAccessors = {
		TiC.PROPERTY_SUBTITLE, TiC.PROPERTY_SUBTITLEID, TiC.PROPERTY_TITLE,
		TiC.PROPERTY_TITLEID, TiC.PROPERTY_LATITUDE, TiC.PROPERTY_LONGITUDE,
		MapModule.PROPERTY_DRAGGABLE, TiC.PROPERTY_IMAGE,
		TiC.PROPERTY_PINCOLOR, MapModule.PROPERTY_CUSTOM_VIEW,
		TiC.PROPERTY_LEFT_BUTTON, TiC.PROPERTY_LEFT_VIEW,
		TiC.PROPERTY_RIGHT_BUTTON, TiC.PROPERTY_RIGHT_VIEW,
		MapModule.PROPERTY_SHOW_INFO_WINDOW, TiC.PROPERTY_DATA })
public class AnnotationProxy extends KrollProxy {
	private static final String TAG = "AnnotationProxy";

	private MarkerOptions markerOptions;
	private TiMarker marker;
	private AnnotationInfo annotationInfo;
	String title;
	private static final String defaultIconImageHeight = "40dip"; // The height
																	// of the
																	// default
																	// marker
																	// icon
	// The height of the marker icon in the unit of "px". Will use it to analyze
	// the touch event to find out
	// the correct clicksource for the click event.
	private int iconImageHeight = 0;

	private static final int MSG_FIRST_ID = KrollProxy.MSG_LAST_ID + 1;

	private static final int MSG_SET_LON = MSG_FIRST_ID + 300;
	private static final int MSG_SET_LAT = MSG_FIRST_ID + 301;
	private static final int MSG_SET_DRAGGABLE = MSG_FIRST_ID + 302;
	private static final int MSG_UPDATE_INFO_WINDOW = MSG_FIRST_ID + 303;

	public AnnotationProxy() {
		super();
		markerOptions = new MarkerOptions();
		defaultValues.put(MapModule.PROPERTY_SHOW_INFO_WINDOW, true);
	}

	public AnnotationProxy(TiContext tiContext) {
		this();
	}

	@Override
	protected KrollDict getLangConversionTable() {
		KrollDict table = new KrollDict();
		table.put(TiC.PROPERTY_SUBTITLE, TiC.PROPERTY_SUBTITLEID);
		table.put(TiC.PROPERTY_TITLE, TiC.PROPERTY_TITLEID);
		return table;
	}

	@Override
	public boolean handleMessage(Message msg) {
		AsyncResult result = null;
		switch (msg.what) {

		case MSG_SET_LON: {
			result = (AsyncResult) msg.obj;
			setPosition(TiConvert.toDouble(getProperty(TiC.PROPERTY_LATITUDE)),
					(Double) result.getArg());
			result.setResult(null);
			return true;
		}

		case MSG_SET_LAT: {
			result = (AsyncResult) msg.obj;
			setPosition((Double) result.getArg(),
					TiConvert.toDouble(getProperty(TiC.PROPERTY_LONGITUDE)));
			result.setResult(null);
			return true;
		}

		case MSG_SET_DRAGGABLE: {
			result = (AsyncResult) msg.obj;
			marker.getMarker().setDraggable((Boolean) result.getArg());
			result.setResult(null);
			return true;
		}

		case MSG_UPDATE_INFO_WINDOW: {
			updateInfoWindow();
			return true;
		}

		default: {
			return super.handleMessage(msg);
		}
		}
	}

	public void setPosition(double latitude, double longitude) {
		LatLng position = new LatLng(latitude, longitude);
		marker.getMarker().setPosition(position);
	}

	public void processOptions() {
		double longitude = 0;
		double latitude = 0;

		if (hasProperty(TiC.PROPERTY_LONGITUDE)) {
			longitude = TiConvert.toDouble(getProperty(TiC.PROPERTY_LONGITUDE));
		}
		if (hasProperty(TiC.PROPERTY_LATITUDE)) {
			latitude = TiConvert.toDouble(getProperty(TiC.PROPERTY_LATITUDE));
		}
		LatLng position = new LatLng(latitude, longitude);
		markerOptions.position(position);

		if (hasProperty(TiC.PROPERTY_LEFT_BUTTON)
				|| hasProperty(TiC.PROPERTY_LEFT_VIEW)
				|| hasProperty(TiC.PROPERTY_RIGHT_BUTTON)
				|| hasProperty(TiC.PROPERTY_RIGHT_VIEW)
				|| hasProperty(TiC.PROPERTY_TITLE)
				|| hasProperty(TiC.PROPERTY_SUBTITLE)) {

			// =======
			// Object leftButton = getProperty(TiC.PROPERTY_LEFT_BUTTON);
			// Object leftView = getProperty(TiC.PROPERTY_LEFT_VIEW);
			// Object rightButton = getProperty(TiC.PROPERTY_RIGHT_BUTTON);
			// Object rightView = getProperty(TiC.PROPERTY_RIGHT_VIEW);
			// if (leftButton != null) {
			// infoWindow.setLeftOrRightPane(leftButton,
			// TiMapInfoWindow.LEFT_PANE);
			// } else {
			// infoWindow.setLeftOrRightPane(leftView,
			// TiMapInfoWindow.LEFT_PANE);
			// }
			// if (rightButton != null) {
			// infoWindow.setLeftOrRightPane(rightButton,
			// TiMapInfoWindow.RIGHT_PANE);
			// } else {
			// infoWindow.setLeftOrRightPane(rightView,
			// TiMapInfoWindow.RIGHT_PANE);
			// }
			// ======
			if (hasProperty(TiC.PROPERTY_TITLE)) {
				title = TiConvert.toString(getProperty(TiC.PROPERTY_TITLE));
				markerOptions.title(title);
			} else {
				markerOptions.title(null);
			}
			if (hasProperty(TiC.PROPERTY_SUBTITLE)) {
				markerOptions.snippet(TiConvert
						.toString(getProperty(TiC.PROPERTY_SUBTITLE)));
			} else {
				markerOptions.snippet(null);
			}
		}

		// ==========
		if (hasProperty(TiC.PROPERTY_DATA)) {
			Object obj = getProperty(TiC.PROPERTY_DATA);
			HashMap mHashMap = (HashMap) obj;

			if (mHashMap.containsKey("header")
					|| mHashMap.containsKey("timing")
					|| mHashMap.containsKey("isFirstType")
					|| mHashMap.containsKey("name")
					|| mHashMap.containsKey("isClosed")
					|| mHashMap.containsKey("rating")) {
				String header = "";
				String name = "";
				String timing = "";
				String sundayClosed = "";
				Boolean isFirstType = false;
				int rating = 0;
				String ratingHeader = "";
				String defaultImagePath = "";
				String selectedImagePath = "";

				Object data_header = mHashMap.get("header");
				Object data_timing = mHashMap.get("timing");
				Object data_isFirstType = mHashMap.get("isFirstType");
				Object data_name = mHashMap.get("name");
				Object data_isClosed = mHashMap.get("isClosed");
				Object data_rating = mHashMap.get("rating");

				Object data_ratingHeader = mHashMap.get("ratingHeader");
				Object data_defaultImage = mHashMap.get("defaultRatingImage");
				Object data_selectedImage = mHashMap.get("selectedRatingImage");

				if (data_isFirstType instanceof Boolean) {
					isFirstType = (Boolean) data_isFirstType;
				}

				if (data_header instanceof String
						&& data_timing instanceof String
						&& data_name instanceof String
						&& data_isClosed instanceof String
						&& data_rating instanceof Integer
						&& data_ratingHeader instanceof String
						&& data_defaultImage instanceof String
						&& data_selectedImage instanceof String) {

					header = (String) data_header;
					timing = (String) data_timing;
					name = (String) data_name;
					sundayClosed = (String) data_isClosed;
					rating = (Integer) data_rating;
					ratingHeader = (String) data_ratingHeader;
					defaultImagePath = (String) data_defaultImage;
					selectedImagePath = (String) data_selectedImage;

				}

				annotationInfo = new AnnotationInfo(header, name, timing,
						isFirstType, sundayClosed, rating, ratingHeader,
						defaultImagePath, selectedImagePath);
				try {
					if (marker != null) {
						marker.setAnnotationInfo(annotationInfo);
					} else {
						marker = this.getTiMarker();
						setAnnotationInfo(annotationInfo);
					}
				} catch (NullPointerException e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			}

		}
		// ==========

		if (hasProperty(MapModule.PROPERTY_DRAGGABLE)) {
			markerOptions.draggable(TiConvert
					.toBoolean(getProperty(MapModule.PROPERTY_DRAGGABLE)));
		}

		// customView, image and pincolor must be defined before adding to
		// mapview. Once added, their values are final.
		if (hasProperty(MapModule.PROPERTY_CUSTOM_VIEW)) {
			handleCustomView(getProperty(MapModule.PROPERTY_CUSTOM_VIEW));
		} else if (hasProperty(TiC.PROPERTY_IMAGE)) {
			handleImage(getProperty(TiC.PROPERTY_IMAGE));
		} else if (hasProperty(TiC.PROPERTY_PINCOLOR)) {
			markerOptions.icon(BitmapDescriptorFactory.defaultMarker(TiConvert
					.toFloat(getProperty(TiC.PROPERTY_PINCOLOR))));
			setIconImageHeight(-1);
		} else {
			setIconImageHeight(-1);
		}
	}

	private void handleCustomView(Object obj) {
		if (obj instanceof TiViewProxy) {
			KrollDict d = ((TiViewProxy) obj).toImage();
			Object imageBlob = d.get(TiC.PROPERTY_MEDIA);
			if (imageBlob instanceof TiBlob) {
				Bitmap image = ((TiBlob) imageBlob).getImage();
				if (image != null) {
					markerOptions.icon(BitmapDescriptorFactory
							.fromBitmap(image));
					setIconImageHeight(image.getHeight());
					return;
				}
			}
		}
		Log.w(TAG, "Unable to get the image from the custom view: " + obj);
		setIconImageHeight(-1);
	}

	private void handleImage(Object image) {
		// image path
		if (image instanceof String) {
			TiDrawableReference imageref = TiDrawableReference.fromUrl(this,
					(String) image);
			Bitmap bitmap = imageref.getBitmap();
			if (bitmap != null) {
				markerOptions.icon(BitmapDescriptorFactory.fromBitmap(bitmap));
				setIconImageHeight(bitmap.getHeight());
				return;
			}
		}
		Log.w(TAG, "Unable to get the image from the path: " + image);
		setIconImageHeight(-1);
	}

	public AnnotationInfo getAnnotationInfo() {
		return annotationInfo;
	}

	public void setAnnotationInfo(AnnotationInfo annotationInfo) {
		this.annotationInfo = annotationInfo;
	}

	public MarkerOptions getMarkerOptions() {
		return markerOptions;
	}

	public void setTiMarker(TiMarker m) {
		marker = m;
	}

	public TiMarker getTiMarker() {
		return marker;
	}

	public void showInfo() {
		if (marker == null) {
			return;
		}
		Marker m = marker.getMarker();
		if (m != null) {
			m.showInfoWindow();
		}
	}

	public void hideInfo() {
		if (marker == null) {
			return;
		}
		Marker m = marker.getMarker();
		if (m != null) {
			m.hideInfoWindow();
		}
	}

	private void setIconImageHeight(int h) {
		if (h >= 0) {
			iconImageHeight = h;
		} else { // default maker icon
			TiDimension dimension = new TiDimension(defaultIconImageHeight,
					TiDimension.TYPE_UNDEFINED);
			// TiDimension needs a view to grab the window manager, so we'll
			// just use the decorview of the current window
			View view = TiApplication.getAppCurrentActivity().getWindow()
					.getDecorView();
			iconImageHeight = dimension.getAsPixels(view);
		}
	}

	public int getIconImageHeight() {
		return iconImageHeight;
	}

	@Override
	public void onPropertyChanged(String name, Object value) {
		super.onPropertyChanged(name, value);

		if (marker == null) {
			return;
		}

		if (name.equals(TiC.PROPERTY_LONGITUDE)) {
			TiMessenger.sendBlockingMainMessage(
					getMainHandler().obtainMessage(MSG_SET_LON),
					TiConvert.toDouble(value));
		} else if (name.equals(TiC.PROPERTY_LATITUDE)) {
			TiMessenger.sendBlockingMainMessage(
					getMainHandler().obtainMessage(MSG_SET_LAT),
					TiConvert.toDouble(value));
		} else if (name.equals(TiC.PROPERTY_TITLE)) {
			String title = TiConvert.toString(value);
			markerOptions.title(title);
			updateInfoWindow();

		} else if (name.equals(TiC.PROPERTY_SUBTITLE)) {
			markerOptions.snippet(TiConvert.toString(value));
			updateInfoWindow();
		} /*
		 * else if (name.equals(TiC.PROPERTY_LEFT_BUTTON)) {
		 * getOrCreateMapInfoWindow().setLeftOrRightPane(value,
		 * TiMapInfoWindow.LEFT_PANE); if (value == null) { Object leftView =
		 * getProperty(TiC.PROPERTY_LEFT_VIEW); if (leftView != null) {
		 * getOrCreateMapInfoWindow().setLeftOrRightPane(leftView,
		 * TiMapInfoWindow.LEFT_PANE); } } updateInfoWindow(); } else if
		 * (name.equals(TiC.PROPERTY_LEFT_VIEW) &&
		 * getProperty(TiC.PROPERTY_LEFT_BUTTON) == null) {
		 * getOrCreateMapInfoWindow().setLeftOrRightPane(value,
		 * TiMapInfoWindow.LEFT_PANE); updateInfoWindow(); } else if
		 * (name.equals(TiC.PROPERTY_RIGHT_BUTTON)) {
		 * getOrCreateMapInfoWindow().setLeftOrRightPane(value,
		 * TiMapInfoWindow.RIGHT_PANE); if (value == null) { Object rightView =
		 * getProperty(TiC.PROPERTY_RIGHT_VIEW); if (rightView != null) {
		 * getOrCreateMapInfoWindow().setLeftOrRightPane(rightView,
		 * TiMapInfoWindow.LEFT_PANE); } } updateInfoWindow(); } else if
		 * (name.equals(TiC.PROPERTY_RIGHT_VIEW) &&
		 * getProperty(TiC.PROPERTY_RIGHT_BUTTON) == null) {
		 * getOrCreateMapInfoWindow().setLeftOrRightPane(value,
		 * TiMapInfoWindow.RIGHT_PANE); updateInfoWindow(); }
		 */else if (name.equals(MapModule.PROPERTY_DRAGGABLE)) {
			TiMessenger.sendBlockingMainMessage(
					getMainHandler().obtainMessage(MSG_SET_DRAGGABLE),
					TiConvert.toBoolean(value));
		}

	}

	private void updateInfoWindow() {
		if (marker == null) {
			return;
		}
		if (TiApplication.isUIThread()) {
			Marker m = marker.getMarker();
			if (m != null && m.isInfoWindowShown()) {
				m.showInfoWindow();
			}
		} else {
			getMainHandler().sendEmptyMessage(MSG_UPDATE_INFO_WINDOW);
		}
	}

	public String getTitle() {
		// TODO Auto-generated method stub
		return title;
	}
}
