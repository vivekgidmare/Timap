package com.vbg.map;

import org.appcelerator.titanium.TiApplication;

import android.content.res.Resources;

public final class PRVResources {
	private static String APP_PACKAGE_NAME = "";
	private static Resources APP_RESOURCE = null;

	public PRVResources()
	{
	APP_PACKAGE_NAME = TiApplication.getAppRootOrCurrentActivity().getPackageName();
	APP_RESOURCE = TiApplication.getAppRootOrCurrentActivity().getResources();
	}

	public int loadResourceId(String name, String type)
	{
	return APP_RESOURCE.getIdentifier(name, type, APP_PACKAGE_NAME);
	}
}
