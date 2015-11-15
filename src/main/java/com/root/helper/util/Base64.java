package com.root.helper.util;

import com.myself.common.utils.StringUtil;

public class Base64 {

	public static char[] encode(byte[] data) {
		char[] out = new char[((data.length + 2) / 3) * 4];

		for (int i = 0, index = 0; i < data.length; i += 3, index += 4) {
			boolean quad = false;
			boolean trip = false;
			int val = (0xFF & (int) data[i]);
			val <<= 8;
			if ((i + 1) < data.length) {
				val |= (0xFF & (int) data[i + 1]);
				trip = true;
			}
			val <<= 8;
			if ((i + 2) < data.length) {
				val |= (0xFF & (int) data[i + 2]);
				quad = true;
			}
			out[index + 3] = alphabet[(quad ? (val & 0x3F) : 64)];
			val >>= 6;
			out[index + 2] = alphabet[(trip ? (val & 0x3F) : 64)];
			val >>= 6;
			out[index + 1] = alphabet[val & 0x3F];
			val >>= 6;
			out[index + 0] = alphabet[val & 0x3F];
		}
		return out;
	}

	/**
	 * 将base64编码的数据解码成原始数据
	 */
	public static byte[] decode(char[] data) {
		int len = ((data.length + 3) / 4) * 3;
		if (data.length > 0 && data[data.length - 1] == '=')
			--len;
		if (data.length > 1 && data[data.length - 2] == '=')
			--len;
		byte[] out = new byte[len];
		int shift = 0;
		int accum = 0;
		int index = 0;
		for (int ix = 0; ix < data.length; ix++) {
			int value = codes[data[ix] & 0xFF];
			if (value >= 0) {
				accum <<= 6;
				shift += 6;
				accum |= value;
				if (shift >= 8) {
					shift -= 8;
					out[index++] = (byte) ((accum >> shift) & 0xff);
				}
			}
		}
		if (index != out.length)
			throw new Error("miscalculated data length!");
		return out;
	}

	private static char[] alphabet = "yzA1MN2YstEuZa34567BnovpqCD+FGHIJKLWOTUVXbPQRScdefghijklmrwx0/89"
			.toCharArray();

	private static byte[] codes = new byte[256];
	static {
		for (int i = 0; i < 256; i++)
			codes[i] = -1;
		for (int i = 'A'; i <= 'Z'; i++)
			codes[i] = (byte) (i - 'A');
		for (int i = 'a'; i <= 'z'; i++)
			codes[i] = (byte) (26 + i - 'a');
		for (int i = '0'; i <= '9'; i++)
			codes[i] = (byte) (52 + i - '0');
		codes['+'] = 62;
		codes['/'] = 63;
	}

	public static void main(String[] args) throws Exception {
		// 加密成base64
		/*String strSrc = "林";
		String strOut = new String(Base64.encode(strSrc.getBytes()));
		System.out.println(strOut);

		String strOut2 = new String(Base64.decode("CUnrqh5jCWCWa1FgCvNOZvoOZWyrqBKWah5jZ2MiC2MjqhGWqUMiZBFj3vsgakCKZBnfqUoTCUZlZk5fahzT315fCWTLa2Mh31MjaWTTakZgakNKqUtKqvaTaWtUav5e".toCharArray()));
		System.out.println("strOut2=" + strOut2);*/
		
		//String s = StringUtil.decrypt("D592901FF38D67AFC500C95ED449B3532E9C041786D936AEA4DE88C6159741397830A2CC1D2A39DE24ECF82553440744".getBytes(), "!12avxd@");
		//System.out.println("s=" + s);
		
		byte[] s1 = StringUtil.encrypt(("1234").getBytes("gbk"), "!12avxd@");
		System.out.println("s1=" + (new String(s1)));
		byte[] s2 = StringUtil.decrypt(s1, "!12avxd@");
		System.out.println("s2=" + new String(s2));
		
		/*String s = "87DF89D42478B220";
		String newStr = new String("Hello".getBytes(), "GBK"); 
		System.out.println("newStr=" + newStr);*/
	}
	
	/**
	 * 字符串转换unicode
	 */
	public static String string2Unicode(String string) {
	 
	    StringBuffer unicode = new StringBuffer();
	 
	    for (int i = 0; i < string.length(); i++) {
	 
	        // 取出每一个字符
	        char c = string.charAt(i);
	 
	        // 转换为unicode
	        unicode.append(Integer.toHexString(c));
	    }
	 
	    return unicode.toString();
	}
}
