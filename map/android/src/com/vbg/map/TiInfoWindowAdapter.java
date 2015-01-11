package com.vbg.map;

import java.io.IOException;
import java.util.ArrayList;

import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.TiContext;
import org.appcelerator.titanium.io.TiBaseFile;
import org.appcelerator.titanium.io.TiFileFactory;
import org.appcelerator.titanium.util.TiUIHelper;
import org.appcelerator.titanium.view.TiDrawableReference;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;

public class TiInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

	public static int RESOURCE_REVIEW_IMAGE_1 = -1;
	public static int RESOURCE_REVIEW_IMAGE_2 = -1;
	public static int RESOURCE_REVIEW_IMAGE_3 = -1;
	public static int RESOURCE_REVIEW_IMAGE_4 = -1;
	public static int RESOURCE_REVIEW_IMAGE_5 = -1;

	public static int RESOURCE_REVIEW_IMAGE_GREEN = -1;
	public static int RESOURCE_REVIEW_IMAGE_GRAY = -1;

	public boolean DEBUGABLE = false;
	private TextView tv_header;
	private TextView tv_name;
	private TextView tv_timing;
	private TextView tv_sunday;
	private TextView tv_rating;

	private ImageView reviewImage_1;
	private ImageView reviewImage_2;
	private ImageView reviewImage_3;
	private ImageView reviewImage_4;
	private ImageView reviewImage_5;

	private ImageView[] ratingImages = { reviewImage_1, reviewImage_2,
			reviewImage_3, reviewImage_4, reviewImage_5 };
	public ArrayList<TiMarker> timarkers;
	protected Marker mMarker;
	LayoutInflater li = ((LayoutInflater) TiApplication.getInstance()
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE));

	private final View contents = li.inflate(AppResources.resId_bolloon, null);

	public TiInfoWindowAdapter(ArrayList<TiMarker> timarkers) {
		super();
		this.timarkers = timarkers;

	}

	public TiInfoWindowAdapter(Marker markers) {
		super();
		this.mMarker = markers;
	}

	@Override
	public View getInfoContents(Marker arg0) {
		// TODO Auto-generated method stub
		// TextView tv_title = (TextView) contents
		// .findViewById(AppResources.resId_ballon_headerText);
		// TextView tv_subtitle = (TextView) contents
		// .findViewById(AppResources.resId_ballon_name);
		return contents;
	}

	@Override
	public View getInfoWindow(Marker marker) {
		AnnotationProxy annoProxy = getProxyByMarker(marker);
		AnnotationInfo annotationInfo = annoProxy.getTiMarker()
				.getAnnotationInfo();

		tv_header = (TextView) contents
				.findViewById(AppResources.resId_ballon_headerText);
		tv_name = (TextView) contents
				.findViewById(AppResources.resId_ballon_name);
		tv_timing = (TextView) contents
				.findViewById(AppResources.resId_ballon_timing);
		tv_sunday = (TextView) contents
				.findViewById(AppResources.resId_ballon_sundayClosed);
		tv_rating = (TextView) contents
				.findViewById(AppResources.resId_ballon_rating);

		tv_header.setText(annotationInfo.getHeader());
		tv_name.setText(annotationInfo.getNameText());
		tv_timing.setText(annotationInfo.getTiming());
		tv_sunday.setText(annotationInfo.getIsSundayClosed());
		tv_rating.setText(annotationInfo.getRatigHeader());
		// tv_rating.setText("Rating :" + annotationInfo.getRating());
		reviewImage_1 = (ImageView) contents
				.findViewById(AppResources.resId_img_review_1);
		reviewImage_2 = (ImageView) contents
				.findViewById(AppResources.resId_img_review_2);
		reviewImage_3 = (ImageView) contents
				.findViewById(AppResources.resId_img_review_3);
		reviewImage_4 = (ImageView) contents
				.findViewById(AppResources.resId_img_review_4);
		reviewImage_5 = (ImageView) contents
				.findViewById(AppResources.resId_img_review_5);

		// Switch Case for Different ImageBackground depends on Rating

		for (int i = 0; i < annotationInfo.getRating(); i++) {
			getImageBitmap(ratingImages[i], annotationInfo.getSelectedImage());
		}
		for (int j = annotationInfo.getRating(); j < 5; j++) {
			getBitmap(ratingImages[j], annotationInfo.getDefaultImage());
		}

		return contents;

	}

	private void debug(String msg) {
		if (this.DEBUGABLE) {
			Log.d("TiMapodule", msg);
		}
	}

	private AnnotationProxy getProxyByMarker(Marker m) {
		if (m != null) {
			for (int i = 0; i < timarkers.size(); i++) {
				TiMarker timarker = timarkers.get(i);
				if (m.equals(timarker.getMarker())) {
					return timarker.getProxy();
				}
			}
		}
		return null;
	}

	public String getPathToApplicationAsset(String assetName) {
		// The url for an application asset can be created by resolving the
		// specified
		// path with the proxy context. This locates a resource relative to the
		// application resources folder
		String result = TiContext.getCurrentTiContext().resolveUrl(null,
				assetName);
		return result;
	}

	private void getImageBitmap(ImageView imageView, String imagePath) {

		try {
			System.out.println("Image Path:" + imagePath);
			// Load the image from the application assets
			String url = getPathToApplicationAsset(imagePath);
			TiBaseFile file = TiFileFactory.createTitaniumFile(
					new String[] { url }, false);
			Bitmap bitmap = TiUIHelper.createBitmap(file.getInputStream());
			BitmapDrawable bd = new BitmapDrawable(bitmap);
			imageView.setImageDrawable(bd);
			// The bitmap must be converted to a TiBlob before returning
			// result = TiBlob.blobFromImage(bitmap);
		} catch (IOException e) {
			// Log.e(LCAT,"[ASSETSDEMO] EXCEPTION - IO");
			e.printStackTrace();
		}

	}

	private void getBitmap(ImageView imageView, Object imagePath) {
		// image path
		if (imagePath instanceof String) {
			System.out.println("Image Path:" + imagePath);

			TiDrawableReference imageref = TiDrawableReference.fromUrl(
					TiApplication.getAppCurrentActivity(), (String) imagePath);
			try {

				Bitmap bitmap = imageref.getBitmap();
				if (bitmap != null) {
					System.out.println("Bitmap is not null");
					// imageView.setImageBitmap(bitmap);
					BitmapDrawable bd = new BitmapDrawable(bitmap);
					imageView.setImageDrawable(bd);

				} else {
					System.out.println("Bitmap is null");
				}
			} catch (NullPointerException e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		}
	}
}
