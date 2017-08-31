/**
 *Copyright: Copyright (c) 2016
 */
package com.enjoyhis.util;

import java.math.BigDecimal;

/**
 * @author wenghongbo
 *
 * 2016年6月8日下午4:11:29
 */
public class MyMath {

	public static BigDecimal add(BigDecimal n1, BigDecimal n2) {
		if (n1 == null)
			return n2;
		if (n2 == null)
			return n1;
		return n1.add(n2);
	}

	public static BigDecimal subtract(BigDecimal b1, BigDecimal b2) {
		return b1.subtract(b2);
	}

	public static BigDecimal multiply(BigDecimal b1, BigDecimal b2, int sc) {
		BigDecimal n = b1.multiply(b2);
		n = n.setScale(sc, BigDecimal.ROUND_HALF_UP);
		return n;
		// return b1.multiply(b2);
	}
}
