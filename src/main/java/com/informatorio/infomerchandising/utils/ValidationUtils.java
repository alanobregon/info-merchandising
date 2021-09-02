package com.informatorio.infomerchandising.utils;

public class ValidationUtils {

	public static Boolean positiveNumber(Double number) {
		return number > 0;
	}

	public static Boolean stringLengthValidation(String str, int max) {
		return str.length()<= max && !str.isBlank();
	}

	public static Boolean stringLengthValidation(String str, int min, int max) {
		return str.length() >= min && str.length() <= max && !str.isBlank();
	}
}
