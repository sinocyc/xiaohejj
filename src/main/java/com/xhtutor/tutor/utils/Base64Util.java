package com.xhtutor.tutor.utils;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Decoder;

public class Base64Util {
	public static String base64ToImage(String code) {
		Decoder decoder = Base64.getDecoder();
		byte[] decode = decoder.decode(code);
		BufferedOutputStream bos = null;;
		try {
			bos = new BufferedOutputStream(new FileOutputStream("C:\\Users\\Ethan\\Desktop\\test.jpg"));
			bos.write(decode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}
