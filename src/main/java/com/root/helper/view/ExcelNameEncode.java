/* 
 * Copyright (c) 2016, S.F. Express Inc. All rights reserved.
 */
package com.root.helper.view;

import java.net.URLEncoder;

import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;

/**
 * 描述：
 * 
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2016年1月6日      608279         Create
 * ****************************************************************************
 * </pre>
 * @author 608279
 * @since 1.0
 */
public class ExcelNameEncode {

	/**
	 * 设置下载文件中文件的名称
	 * 
	 * @param filename
	 * @param request
	 * @return
	 */
	public static String encodeFilename(String filename,
			HttpServletRequest request) {
		/**
		 * 获取客户端浏览器和操作系统信息 在IE浏览器中得到的是：User-Agent=Mozilla/4.0 (compatible; MSIE
		 * 6.0; Windows NT 5.1; SV1; Maxthon; Alexa Toolbar)
		 * 在Firefox中得到的是：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1;
		 * zh-CN; rv:1.7.10) Gecko/20050717 Firefox/1.0.6
		 */
		String agent = request.getHeader("USER-AGENT");
		try {
			
			if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {
				return URLEncoder.encode(filename, "UTF-8");
			} else if ((agent != null) && (-1 != agent.indexOf("Mozilla"))) {
				return MimeUtility.encodeText(filename, "UTF-8", "B");
			}
			
			return filename;
		} catch (Exception ex) {
			return filename;
		}
	}
}
