package br.com.erudio.utils;

public class NumberConverter {
	
	public static Double convertToDouble(String strNumber) {
		if (strNumber == null) return 0d;
		strNumber = strNumber.replaceAll(",", ".");
		if (isNumeric(strNumber)) return Double.parseDouble(strNumber);
		return 0d;
	}

	public static boolean isNumeric(String strNumber) {
		if (strNumber == null) return false;
		strNumber = strNumber.replaceAll(",", ".");
		return strNumber.matches("[-+]?[0-9]*\\.?[0-9]+");
	}
	
}
