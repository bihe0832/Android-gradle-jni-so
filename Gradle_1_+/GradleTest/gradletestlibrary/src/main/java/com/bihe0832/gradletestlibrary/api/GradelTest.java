package com.bihe0832.gradletestlibrary.api;


import com.bihe0832.md5.MD5;

public final class GradelTest {

	private static volatile GradelTest instance;
	public static GradelTest getInstance() {
		if (instance == null) {
			synchronized (GradelTest.class) {
				if (instance == null) {
					instance = new GradelTest();
				}
			}
		}
		return instance;
	}

	private GradelTest(){}

	public String getLowerMD5(String key){
		String result = MD5.getMD5(key);
		if(null != result){
			return result.toLowerCase();
		}else{
			return "";
		}
	}

	public String getUpperMD5(String key){
		String result = MD5.getMD5(key);
		if(null != result){
			return result.toUpperCase();
		}else{
			return "";
		}
	}
}
