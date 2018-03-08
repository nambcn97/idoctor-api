package com.fpt.idoctor.common.util;

import java.text.DecimalFormat;

public class MathUtil {
	private static DecimalFormat decimalFormat = new DecimalFormat("#.##########");
	
	public static Double roundDecimalRate(Double rate) {
		return Double.parseDouble(decimalFormat.format(rate));
	}
}
