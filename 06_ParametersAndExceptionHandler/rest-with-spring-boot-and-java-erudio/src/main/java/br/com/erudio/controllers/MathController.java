package br.com.erudio.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.services.MathCalculatorService;

@RestController
@RequestMapping("/math")
public class MathController {
	
	private MathCalculatorService mathCalculatorService;
	private final AtomicLong counter = new AtomicLong();
	
	public MathController(MathCalculatorService mathCalculatorService) {
		this.mathCalculatorService = mathCalculatorService;
	}
	
	@GetMapping("/sum/{numberOne}/{numberTwo}")
	public Double sum(@PathVariable(name = "numberOne") String numberOne,
					  @PathVariable(name = "numberTwo") String numberTwo) throws Exception {
		return mathCalculatorService.sum(numberOne, numberTwo);
	}
	
	@GetMapping("/subtraction/{numberOne}/{numberTwo}")
	public Double subtraction(@PathVariable(name = "numberOne") String numberOne,
							  @PathVariable(name = "numberTwo") String numberTwo) {
		return mathCalculatorService.subtraction(numberOne, numberTwo);
	}
	
	@GetMapping("/multiplication/{numberOne}/{numberTwo}")
	public Double multiplication(@PathVariable(name = "numberOne") String numberOne,
						         @PathVariable(name = "numberTwo") String numberTwo) {
		return mathCalculatorService.multiplication(numberOne, numberTwo);
	}
	
	@GetMapping("/division/{numberOne}/{numberTwo}")
	public Double division(@PathVariable(name = "numberOne") String numberOne,
						   @PathVariable(name = "numberTwo") String numberTwo) {
		return mathCalculatorService.division(numberOne, numberTwo);
	}
	
	@GetMapping("/average/{numberOne}/{numberTwo}")
	public Double average(@PathVariable(name = "numberOne") String numberOne,
		   				  @PathVariable(name = "numberTwo") String numberTwo) {
		return mathCalculatorService.average(numberOne, numberTwo);
	}
	
	@GetMapping("/squareroot/{number}")
	public Double squareRoot(@PathVariable(name = "number") String number) {
		return mathCalculatorService.squareRoot(number);
	}
 	
}
