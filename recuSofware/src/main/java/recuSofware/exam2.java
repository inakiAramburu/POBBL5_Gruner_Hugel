package recuSofware;

import java.util.Arrays;
import java.util.Collection;


//Test the exam function using parameters
//Test the exam function using partial mocks and imagine that "squareCalc" and "getDigit" are under development

//Create a TestSuite to run both tests in the "Mock" branch



//public static Collection<Object[]> numbers() {return Arrays.asList(new Object[][] {});

public class exam2 {

	public static boolean exam(int n){  
		int sum = 0;        
		int square = squareCalc(n);  
		while(square != 0)  
		{  
			int digit = getDigit(square);  
			sum = sum + digit;  
			square = square / 10;  
		}  
		if(n == sum)  
			return true;  
		else  
			return false; 
		}  
	
	public static int squareCalc(int n) {
		return n*n;
	}
	
	public static int getDigit (int s) {
		return s % 10;
	}
	
	


}