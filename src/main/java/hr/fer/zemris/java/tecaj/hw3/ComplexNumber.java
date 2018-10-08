package hr.fer.zemris.java.tecaj.hw3;
/**
 * Class ComplexNumber represents an unmodifiable complex number.
 * @author Zemunik
 * @version 1.0
 */
public class ComplexNumber {

	/*Real and imaginary part of complex number.*/
	private final double real, imaginary;
	
	/**
	 * Constructor which sets real and imaginary part of number.
	 * @param real real part of complex number
	 * @param imaginary imaginary part of complex number
	 */
	public ComplexNumber(double real, double imaginary){
		this.real = real;
		this.imaginary = imaginary;
	}
	
	/**
	 * Method returns new {@link ComplexNumber} with given real number and imaginary
	 * number set to zero.
	 * @param real real part of complex number 
	 * @return new instance of ComplexNumber
	 */
	public static ComplexNumber fromReal(double real){
		return new ComplexNumber(real, 0);
	}
	
	/**
	 * Method returns new {@link ComplexNumber} with given imaginary number and real
	 * number set to zero.
	 * @param imaginary imaginary part of complex number
	 * @return new instance of ComplexNumber
	 */
	public static ComplexNumber fromImaginary(double imaginary){
		return new ComplexNumber(0, imaginary);
	}
	
	/**
	 * Method returns new {@link ComplexNumber} with given magnitude and angle value.
	 * @param magnitude magnitude of complex number
	 * @param angle angle of complex number
	 * @return new instance of ComplexNumber
	 */
	public static ComplexNumber fromMagnitudeAndAngle(double magnitude, double angle){
		double real = Math.cos(angle) * magnitude;
		double imaginary = Math.sin(angle) * magnitude;
		return new ComplexNumber(real, imaginary);
	}
	
	/**
	 * Method returns new {@link ComplexNumber} parsed from given string.
	 * String should look this way: (realPart)(sign)(ImaginaryPart)i. Real part can have 
	 * sign, also. Between real and imaginary part can be zero or more empty spaces.
	 * Example of valid string: "1.13 -4.5i".
	 * @param s given string that contains complex number
	 * @return new instance of ComplexNumber
	 * @throws IndexOutOfBoundsException if invalid string was provided
	 * @throws NumberFormatException if invalid string was provided
	 */
	public static ComplexNumber parse(String s){
		s = s.trim();
		int charIndex = 0;
		String firstNumber = null;
		StringBuilder builder = new StringBuilder();
		if(s.charAt(0) == '+' || s.charAt(0) == '-'){
			builder.append(s.charAt(0));
			s = s.substring(1);
		}
		
		String number = getNumberFromString(s, charIndex);
		builder.append(number);
		firstNumber = builder.toString();
		builder.setLength(0);
		charIndex = charIndex + firstNumber.length();
		
		String secondNumber = null;
		if(charIndex < s.length()){
			while(s.charAt(charIndex) == ' '){
				charIndex++;
			}
			builder.append(s.charAt(charIndex));
			charIndex++;
			number = getNumberFromString(s, charIndex);
			builder.append(number);
			secondNumber = builder.toString();
			charIndex = charIndex + secondNumber.length();
		}
		
		if(charIndex < s.length()){
			throw new IndexOutOfBoundsException("Invalid input string.");
		}
		
		double real = 0, imaginary = 0;
		if(secondNumber == null){
			/*If secondNumber == null, firstNumber can be imaginary.*/
			if(firstNumber.contains("i")){
				if(firstNumber.equals("i")){
					imaginary = 1;
				} else if(firstNumber.equals("+i")) {
					imaginary = 1;
				} else if(firstNumber.equals("-i")){
					imaginary = -1;
				} else{
					firstNumber = firstNumber.replaceFirst("i", "");
					imaginary = parseNumberFromString(firstNumber);
				}
			} else{
				real = parseNumberFromString(firstNumber);
			}
		} else{
			/*If both first and second number exist, then second one is imaginary.*/
			if(secondNumber.equals("+i")){
				imaginary = 1;
			} else if(secondNumber.equals("-i")){
				imaginary = -1;
			} else{
				secondNumber = secondNumber.replaceFirst("i", "");
				imaginary = parseNumberFromString(secondNumber);
			}
			real = parseNumberFromString(firstNumber);
		}
		
		return new ComplexNumber(real, imaginary);
		
	}
	
	/**
	 * Method returns real part of complex number.
	 * @return real part of complex number
	 */
	public double getReal(){
		return real;
	}
	
	/**
	 * Method returns imaginary part of complex number.
	 * @return imaginary part of complex number
	 */
	public double getImaginary(){
		return imaginary;
	}
	
	/**
	 * Method calculates and returns magnitude of complex number.
	 * @return magnitude of complex number
	 */
	public double getMagnitude(){
		return Math.sqrt(Math.pow(real, 2) + Math.pow(imaginary, 2));
	}
	
	/**
	 * Method calculates and returns angle of complex number.
	 * @return angle of complex number (in radians, from 0 to 2 Pi)
	 */
	public double getAngle(){
		double angle = Math.atan2(imaginary, real);
		if(angle < 0){
			/*Angle from 0 to 2 Pi, ata2 return from -Pi to Pi*/
			angle = angle + 2*Math.PI;
		}
		return angle;
	}
	
	/**
	 * Method adds given complex number to complex number of this instance.
	 * @param c given complex number
	 * @return new complex number which is result of addition
	 */
	public ComplexNumber add(ComplexNumber c){
		double real = c.getReal() + this.getReal();
		double imaginary = c.getImaginary() + this.getImaginary();
		return new ComplexNumber(real, imaginary);
	}
	
	/**
	 * Method subtracts given complex number of complex number of this instance.
	 * @param c given complex number
	 * @return new complex number which is result of subtraction
	 */
	public ComplexNumber sub(ComplexNumber c){
		double realNew = real - c.getReal();
		double imaginaryNew = imaginary - c.getImaginary();
		return new ComplexNumber(realNew, imaginaryNew);
	}
	
	/**
	 * Method multiplies given complex number and complex number of this instance.
	 * @param c given complex number
	 * @return new complex number which is result of multiplication
	 */
	public ComplexNumber mul(ComplexNumber c){
		double realNew, imaginaryNew;
		realNew = real*c.getReal() - imaginary*c.getImaginary();
		imaginaryNew = real*c.getImaginary() + imaginary*c.getReal();
		return new ComplexNumber(realNew, imaginaryNew);
	}
	
	/**
	 * Method divides complex number of this instance by given complex number.
	 * @param c given complex number
	 * @return new complex number which is result of division
	 * @throws IllegalArgumentException if complex number has both real and imaginary
	 * number equal to zero
	 */
	public ComplexNumber div(ComplexNumber c){
		if(c.real == 0 && c.imaginary == 0){
			throw new IllegalArgumentException("Can not divide by zero.");
		}
		double cReal = c.getReal(), cImaginary = c.getImaginary();
		ComplexNumber conjugateC = new ComplexNumber(cReal,-cImaginary);
		double divisor = cReal*cReal + cImaginary*cImaginary;
		ComplexNumber dividend = this.mul(conjugateC);
		return new ComplexNumber(dividend.getReal()/divisor, 
				dividend.getImaginary()/divisor);
	}
	
	/**
	 * Method calculates and returns value of complex number raised to the power of 
	 * given integer.
	 * @param n given integer
	 * @return value of complex number raised to the power of given integer
	 * @throws IllegalArgumentException if given number is less than zero
	 */
	public ComplexNumber power(int n){
		if(n<0){
			throw new IllegalArgumentException("In method power, n has to be greater or"
					+ "equals to zero.");
		}
		double magnitudePower, anglePower, realNew, imaginaryNew;
		
		magnitudePower = Math.pow(this.getMagnitude(), n);
		anglePower = this.getAngle() * n;
		realNew = magnitudePower * Math.cos(anglePower);
		imaginaryNew = magnitudePower * Math.sin(anglePower);
		return new ComplexNumber(realNew, imaginaryNew);
	}
	
	/**
	 * Method computes and returns all requested roots of complex number.
	 * @param n root number
	 * @return all requested roots of complex number
	 * @throws IllegalArgumentException if root number is less or equal to zero
	 */
	public ComplexNumber[] root(int n){
		if(n <= 0){
			throw new IllegalArgumentException("In method root, n has to be greater "
					+ "than zero.");
		}
		
		ComplexNumber[] roots = new ComplexNumber[n];
		double basicMagnitude, basicAngle, magnitudeRoot;
		basicMagnitude = this.getMagnitude();
		basicAngle = this.getAngle();
		magnitudeRoot = Math.pow(basicMagnitude, (1.0/n));
		
		for(int angleIterator=0; angleIterator<n; angleIterator++){
			
			double angleVariation, realPartRoot, imaginaryPartRoot;
			angleVariation = (basicAngle + 2*Math.PI*angleIterator)/n;
			realPartRoot = magnitudeRoot*Math.cos(angleVariation);
			imaginaryPartRoot = magnitudeRoot*Math.sin(angleVariation);
			roots[angleIterator] = new ComplexNumber(realPartRoot, imaginaryPartRoot);
		}
		return roots;
	}
	
	/**
	 * Method returns complex number in string format.
	 */
	public String toString(){
		if(imaginary >= 0){
			return String.format("%.6f", real) + "+" + String.format("%.6f", imaginary) 
					+ "i";
		}
		return String.format("%.6f", real) + String.format("%.6f", imaginary) + "i";
	}
	
	/**
	 * Method parses number from given string if string is valid.
	 * @param currentString given string
	 * @return number parsed from string
	 */
	private static double parseNumberFromString(String currentString) 
			throws NumberFormatException{
		double number;
		number = Double.parseDouble(currentString);

		return number;
	}
	
	/**
	 * Method receives given string and fetches part of it with real or imaginary
	 * part of complex number.
	 * @param currentString given string
	 * @param currentIndex index where number starts in given string
	 * @return string that contains real or imaginary part of complex number
	 */
	private static String getNumberFromString(String currentString, int currentIndex) {
		char currentChar;
		String number = "";
		while(currentIndex < currentString.length()){
			currentChar = currentString.charAt(currentIndex);
			if(currentChar == ' ' || currentChar == '+' || currentChar == '-'){
				return number;
			} else{
				number = number + currentChar;
				currentIndex++;
			}
		}
		
		/*End of the string has been reached.*/
		return number;
	}

	/**
	 * Method equals checks whether two complex numbers have same real parts and imaginary
	 * parts.
	 * @param object {@link Object}, {@link ComplexNumber} instance
	 * @return true if real and imaginary parts are same, false otherwise
	 */ 
	@Override
	public boolean equals(Object object){
		ComplexNumber complexNumber;
		if(object instanceof ComplexNumber){
			complexNumber = (ComplexNumber) object;
		} else{
			return false;
		}
		
		if(Math.abs(complexNumber.getReal()-real)<1E-4 && 
				Math.abs(complexNumber.getImaginary()-imaginary)<1E-4) {
			return true;
		} else{
			return false;
		}
	}
	
}
