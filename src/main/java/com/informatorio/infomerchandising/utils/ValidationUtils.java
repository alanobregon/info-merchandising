package com.informatorio.infomerchandising.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ValidationUtils {

	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	public static Boolean positiveNumber(Double number) {
		return number > 0;
	}

	public static Boolean stringLengthValidation(String str, int max) {
		return str.length()<= max && !str.isBlank();
	}

	public static Boolean stringLengthValidation(String str, int min, int max) {
		return str.length() >= min && str.length() <= max && !str.isBlank();
	}

	public static Boolean emailValidation(String str) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(str);
		return matcher.find();
	}
}
