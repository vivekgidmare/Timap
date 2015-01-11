package com.vbg.map;

import android.content.Context;
import android.content.res.Resources;

public final class AppResources {
	//ballon.xml
	public static int resId_bolloon = -1;
	public static int resId_ballon_headerText = -1;
	public static int resId_ballon_name = -1;
	public static int resId_ballon_timing = -1;
	public static int resId_ballon_sundayClosed = -1;
	public static int resId_ballon_rating = -1;
	

	public static int resId_img_review_1=-1;
	public static int resId_img_review_2=-1;
	public static int resId_img_review_3=-1;
	public static int resId_img_review_4=-1;
	public static int resId_img_review_5=-1;
	

	public static void loadResourceIds(Context context) {
		String packageName = context.getPackageName();
		Resources resources = context.getResources();

		resId_bolloon = resources.getIdentifier("balloon", "layout",
				packageName);
		resId_ballon_headerText = resources.getIdentifier("tv_headertext", "id",
				packageName);
		resId_ballon_name= resources.getIdentifier("tv_name", "id",
				packageName);
		resId_ballon_timing = resources.getIdentifier("tv_timing", "id",
				packageName);
		resId_ballon_sundayClosed= resources.getIdentifier("tv_sunday", "id",
				packageName);
		resId_ballon_rating = resources.getIdentifier("tv_rating", "id",
				packageName);
		
		//Review Image Id
		resId_img_review_1 = resources.getIdentifier("img_review_1", "id", packageName);
		resId_img_review_2 = resources.getIdentifier("img_review_2", "id", packageName);
		resId_img_review_3 = resources.getIdentifier("img_review_3", "id", packageName);
		resId_img_review_4= resources.getIdentifier("img_review_4", "id", packageName);
		resId_img_review_5= resources.getIdentifier("img_review_5", "id", packageName);
		
	}
}
