package com.healthslife.map;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

public class MapApplication extends Application{
	@Override
	public void onCreate() {
		super.onCreate();
		SDKInitializer.initialize(this);
	}
}
