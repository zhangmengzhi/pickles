package org.zhangmz.pickles.modules.constants;

import java.nio.charset.Charset;

/**
 * JDK7可直接使用java.nio.charset.StandardCharsets.
 * 
 * @author calvin
 *
 * 修改人：张孟志
 * 修改日期：2016-01-09
 * 修改说明：添加字符集的常量字符串表示
 */
public class Charsets {

	public static final Charset UTF8 = Charset.forName("UTF-8");
	public static final String UTF_8 ="UTF-8";

}
