package com.bihe0832.md5;

import android.util.Log;

/**
 *
 * @author bihe0832
 *
 */
public class MD5 {

	private static final int VERSION = 1;
	
	static{
		System.loadLibrary("bihe0832MD5");
		Log.d("bihe0832","bihe0832 MD5 Version :" + VERSION);
	}
	
	// 获取网络请求最终加密获取签名的key
	private static native String getMD5Result(String encryptKey);

	public static String getMD5(String encryptKey){
		return getMD5Result(encryptKey);
	}
}
