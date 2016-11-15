package controller;

import java.io.IOException;
import java.util.Random;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class Kryption {
	Random gen = new Random();
	public String encrypt(String str) {
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(genSalt())
				+ encoder.encode(str.getBytes());
	}

	public String decrypt(String encstr) {
		if (encstr.length() > 12) {
			String cipher = encstr.substring(12);
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				return new String(decoder.decodeBuffer(cipher));
			} catch (IOException e) {
			}
		}
		return null;
	}
	
	public byte[] genSalt() {
		byte[] salt = new byte[8];
		gen.nextBytes(salt);
		return salt;
	}
}
