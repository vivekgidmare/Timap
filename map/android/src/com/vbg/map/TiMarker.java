package com.vbg.map;

import com.google.android.gms.maps.model.Marker;

public class TiMarker {
	private Marker marker;
	private AnnotationProxy proxy;
	private AnnotationInfo annotationInfo; 

	public TiMarker(Marker m, AnnotationProxy annotation,AnnotationInfo info) {
		marker = m;
		proxy = annotation;
		annotationInfo=info;
	}

	public void setMarker(Marker m) {
		marker = m;
	}

	public Marker getMarker() {
		return marker;
	}

	public AnnotationProxy getProxy() {
		return proxy;
	}

	public AnnotationInfo getAnnotationInfo() {
		return annotationInfo;
	}

	public void setAnnotationInfo(AnnotationInfo annotationInfo) {
		this.annotationInfo = annotationInfo;
	}
	
	
}
