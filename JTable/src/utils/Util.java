package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

	public static double parseDouble(String str) throws NumberFormatException {
		Double result;

		Pattern pattern = Pattern.compile("([\\d]+)+(\\.[\\d]{0,2})*");
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()) {
			result = Double.parseDouble(str);
		} else {
			throw new NumberFormatException("Expected floating point number rounded to the second digit but encountered: " + str);
		}

		return result;
	}
}