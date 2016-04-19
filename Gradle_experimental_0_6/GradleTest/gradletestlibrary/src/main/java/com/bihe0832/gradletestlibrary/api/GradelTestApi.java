package com.bihe0832.gradletestlibrary.api;


public final class GradelTestApi {

	public static String getLowerMD5(String key){
		return GradelTest.getInstance().getLowerMD5(key);
	}

	public static String getUpperMD5(String key){
		return GradelTest.getInstance().getUpperMD5(key);
	}
}
