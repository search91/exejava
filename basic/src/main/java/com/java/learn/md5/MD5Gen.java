package com.java.learn.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Gen {
	private MessageDigest md;
	private static final String[] hexDigits = new String[] { "0", "1", "2",
			"3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	public MD5Gen() throws GarbageException {
		try {
			this.md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException arg1) {
			throw new GarbageException(arg1);
		}
	}

	private static String byteArrayToString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();

		for (int i = 0; i < b.length; ++i) {
			resultSb.append(byteToHexString(b[i]));
		}

		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (b < 0) {
			n = 256 + b;
		}

		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public String getMD5(byte[] b, int length) {
		assert length <= b.length;

		this.md.reset();
		this.md.update(b, 0, length);
		String md5 = byteArrayToString(this.md.digest());
		return md5;
	}

	public String getMD5(String str) {
		byte[] bh = str.getBytes();
		return this.getMD5(bh, bh.length);
	}

	public static void main(String[] args) {
		String h = "target=\"_blank\">【网易自营|30天无忧退货】Adidas制造商直供男袜/女袜，限时仅5.6元/双&gt;&gt;</a></span></div><p><br/></p></span>";
		byte[] bh = h.getBytes();
		MD5Gen md = null;

		try {
			md = new MD5Gen();
		} catch (GarbageException arg4) {
			arg4.printStackTrace();
		}

		String md5 = md.getMD5(bh, bh.length);
		System.out.println("md5 is " + md5);
	}
}