package br.com.erudio.services;

import org.springframework.stereotype.Service;

import br.com.erudio.exceptions.UnsupportedMathOperationException;
import br.com.erudio.utils.NumberConverter;

@Service
public class MathCalculatorService {
	
	public Double sum(String numberOne, String numberTwo) {
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		return NumberConverter.convertToDouble(numberOne) + NumberConverter.convertToDouble(numberTwo);
	}
	
	public Double subtraction(String numberOne, String numberTwo) {
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		return NumberConverter.convertToDouble(numberOne) - NumberConverter.convertToDouble(numberTwo);
	}
	
	public Double multiplication(String numberOne, String numberTwo) {
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		return NumberConverter.convertToDouble(numberOne) * NumberConverter.convertToDouble(numberTwo);
	}
	
	public Double division(String numberOne, String numberTwo) {
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		if (NumberConverter.convertToDouble(numberTwo) == 0) {
			throw new UnsupportedMathOperationException("Please set a valid divider!");
		}
		return NumberConverter.convertToDouble(numberOne) / NumberConverter.convertToDouble(numberTwo);
	}
	
	public Double average(String numberOne, String numberTwo) {
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		return (NumberConverter.convertToDouble(numberOne) + NumberConverter.convertToDouble(numberTwo)) / 2;
	}
	
	public Double squareRoot(String number) {
		if (!NumberConverter.isNumeric(number)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		return Math.sqrt(NumberConverter.convertToDouble(number));
	}
	
}
