package com.enjoyhis.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtils {

	/** 控制台日志 */
	public static Logger CONSOLE = LogManager.getLogger();
	/** info日志 */
	public static Logger INFO = LogManager.getLogger("info");
	/** error日志 */
	public static Logger ERROR = LogManager.getLogger("error");
	/** debug日志 */
	public static Logger DEBUG = LogManager.getLogger("debug");
	/** 浏览器请求日志 */
	public static Logger BROWSER_INFO = LogManager.getLogger("browser.info");
	/** 客户端请求日志 */
	public static Logger CLIENT_TRACE = LogManager.getLogger("client.trace");
	/** 客户端请求日志 */
	public static Logger OPERATOR = LogManager.getLogger("operator");

}
