package com.healthslife.health;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class AirQuality {
	/*
	 * 从指定的URL中获取数组
	 */
	public static String readParse(String urlPath) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[1024];
		int len = 0;
		URL url = new URL(urlPath);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(10000);
		conn.setReadTimeout(5000);
		conn.connect();
		int code = conn.getResponseCode();
		if (200 == code) {
			InputStream inStream = conn.getInputStream();
			while ((len = inStream.read(data)) != -1) {
				outStream.write(data, 0, len);
			}
			inStream.close();
			String jsonData = new String(outStream.toByteArray());
			return jsonData;
		}

		return null;// 通过out.Stream.toByteArray获取到写的数据
	}

	/*
	 * JSON数据的解析
	 */
	private static ArrayList<HashMap<String, Object>> Analysis(String jsonStr)
			throws JSONException {
		JSONArray jsonArray = null;
		// initialize list array object
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		jsonArray = new JSONArray(jsonStr);
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			// initialize map array object
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("aqi", jsonObject.getString("aqi"));
			map.put("city", jsonObject.getString("area"));
			list.add(map);
			return list;
		}
		return null;

	}
	
	public static String getAqi(String url){
		ArrayList<HashMap<String, Object>>	list = null;
		try {
			list = Analysis(readParse(url));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(list != null) {
			return list.get(0).get("aqi").toString();
		}
		return null;
		
	}
	
}
