package hr.fer.zemris.java.tecaj.hw3;

import org.junit.Test;
import org.junit.Assert;

/**
 * Class {@link ComplexNumberTest} is used for testing {@link ComplexNumber}. Testing
 * methods are named: methodName_GivenArguments_ExpectedBehaviour. Coverage is 100%.
 * @author Zemunik
 * @version 1.0
 */
public class ComplexNumberTest {

	@Test
	public void fromReal_NumberGiven_ShouldCreateComplexNumber(){
		ComplexNumber received = ComplexNumber.fromReal(1);
		ComplexNumber actual = new ComplexNumber(1, 0);
		Assert.assertTrue(received.equals(actual));
	}
	
	@Test
	public void fromImaginary_NumberGiven_ShouldCreateComplexNumber(){
		ComplexNumber received = ComplexNumber.fromImaginary(-3);
		ComplexNumber actual = new ComplexNumber(0, -3);
		Assert.assertTrue(received.equals(actual));
	}
	
	@Test
	public void parse_StringGiven_ShouldCreateComplexNumber_Test1(){
		ComplexNumber actual = new ComplexNumber(-1.5, 2.9);
		ComplexNumber received = ComplexNumber.parse(" -1.5+2.9i ");
		Assert.assertTrue(received.equals(actual));
	}
	
	@Test
	public void parse_StringGiven_ShouldCreateComplexNumber_Test2(){
		ComplexNumber actual = new ComplexNumber(1.5, -2.9);
		ComplexNumber received = ComplexNumber.parse(" +1.5   -2.9i ");
		Assert.assertTrue(received.equals(actual));
	}
	
	@Test
	public void parse_StringGiven_ShouldCreateComplexNumber_Test3(){
		ComplexNumber actual = new ComplexNumber(1.5, -1);
		ComplexNumber received = ComplexNumber.parse(" +1.5   -i ");
		Assert.assertTrue(received.equals(actual));
	}
	
	@Test
	public void parse_StringGiven_ShouldCreateComplexNumber_Test4(){
		ComplexNumber actual = new ComplexNumber(1.5, 1);
		ComplexNumber received = ComplexNumber.parse(" +1.5   +i ");
		Assert.assertTrue(received.equals(actual));
	}
	
	@Test
	public void parse_StringGiven_ShouldCreateComplexNumber_Test5(){
		ComplexNumber actual = new ComplexNumber(0, 1);
		ComplexNumber received = ComplexNumber.parse(" +i ");
		Assert.assertTrue(received.equals(actual));
	}
	
	@Test (expected = NumberFormatException.class)
	public void parse_StringGiven_ShouldThrowException_Test1(){
		ComplexNumber.parse("1.a-2.9 ");
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void parse_StringGiven_ShouldThrowException_Test2(){
		ComplexNumber.parse("1.a-i asd ");
	}
	
	@Test
	public void parse_StringGivenOnlyOneNumber_ShouldCreateComplexNumber_Test1(){
		ComplexNumber actual = new ComplexNumber(0, 1);
		ComplexNumber received = ComplexNumber.parse("i");
		Assert.assertTrue(received.equals(actual));
	}
	
	@Test
	public void parse_StringGivenOnlyOneNumber_ShouldCreateComplexNumber_Test2(){
		ComplexNumber actual = new ComplexNumber(0, -1);
		ComplexNumber received = ComplexNumber.parse("-i");
		Assert.assertTrue(received.equals(actual));
	}
	
	@Test
	public void parse_StringGivenOnlyOneNumber_ShouldCreateComplexNumber_Test3(){
		ComplexNumber actual = new ComplexNumber(0, 2);
		ComplexNumber received = ComplexNumber.parse("2i");
		Assert.assertTrue(received.equals(actual));
	}
	
	@Test
	public void parse_StringGivenOnlyOneNumber_ShouldCreateComplexNumber_Test4(){
		ComplexNumber actual = new ComplexNumber(-2, 0);
		ComplexNumber received = ComplexNumber.parse("-2");
		Assert.assertTrue(received.equals(actual));
	}
	
	@Test
	public void add_ComplexNumberGiven_ShouldReturnNewComplexNumber(){
		ComplexNumber firstNumber = new ComplexNumber(1.2, -2.3);
		ComplexNumber secondNumber = new ComplexNumber(2.5, 1.2);
		ComplexNumber received = firstNumber.add(secondNumber);
		ComplexNumber actual = new ComplexNumber(3.7, -1.1);
		Assert.assertTrue(received.equals(actual));
	}

	@Test
	public void sub_ComplexNumberGiven_ShouldReturnNewComplexNumber(){
		ComplexNumber firstNumber = new ComplexNumber(1.2, -2.3);
		ComplexNumber secondNumber = new ComplexNumber(2.5, 1.2);
		ComplexNumber received = firstNumber.sub(secondNumber);
		ComplexNumber actual = new ComplexNumber(-1.3, -3.5);
		Assert.assertTrue(received.equals(actual));
	}
	
	@Test
	public void mul_ComplexNumberGiven_ShouldReturnNewComplexNumber(){
		ComplexNumber firstNumber = new ComplexNumber(1.2, -2.3);
		ComplexNumber secondNumber = new ComplexNumber(2.5, 1.2);
		ComplexNumber received = firstNumber.mul(secondNumber);
		ComplexNumber actual = new ComplexNumber(5.76, -4.31);
		Assert.assertTrue(received.equals(actual));
	}
	
	@Test
	public void div_ComplexNumberGiven_ShouldReturnNewComplexNumber_Test1(){
		ComplexNumber firstNumber = new ComplexNumber(3, 5);
		ComplexNumber secondNumber = new ComplexNumber(2, 6);
		ComplexNumber received = firstNumber.div(secondNumber);
		ComplexNumber actual = new ComplexNumber(0.9, -0.2);
		Assert.assertTrue(received.equals(actual));
	}
	
	@Test
	public void div_ComplexNumberGiven_ShouldReturnNewComplexNumber_Test2(){
		ComplexNumber firstNumber = new ComplexNumber(3, 2);
		ComplexNumber secondNumber = new ComplexNumber(0, 1);
		ComplexNumber received = firstNumber.div(secondNumber);
		ComplexNumber actual = new ComplexNumber(2, -3);
		Assert.assertTrue(received.equals(actual));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void div_ComplexNumberGiven_ShouldThrowException(){
		ComplexNumber firstNumber = new ComplexNumber(3, 5);
		ComplexNumber secondNumber = new ComplexNumber(0, 0);
		firstNumber.div(secondNumber);
	}
	
	@Test
	public void power_PositiveNumberGiven_ShouldReturnNewComplexNumber(){
		ComplexNumber number = new ComplexNumber(1, 1);
		ComplexNumber received = number.power(5);
		ComplexNumber actual = new ComplexNumber(-4, -4);
		Assert.assertTrue(received.equals(actual));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void power_NegativeNumberGiven_ShouldThrowException(){
		ComplexNumber number = new ComplexNumber(1, 1);
		number.power(-2);
	}
	
	@Test
	public void root_PositiveNumberGiven_ShouldReturnNewComplexNumber(){
		ComplexNumber number = new ComplexNumber(1, 1);
		ComplexNumber[] received = number.root(4);
		ComplexNumber[] actual = new ComplexNumber[]{
				new ComplexNumber(1.06955, 0.21275), new ComplexNumber(-0.21275, 1.06955),
				new ComplexNumber(-1.06955, -0.21275),new ComplexNumber(0.21275, -1.06955)};
		int arrayIterator = 0;
		for(ComplexNumber currentNumber: received){
			Assert.assertTrue(currentNumber.equals(actual[arrayIterator]));
			arrayIterator++;
		}
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void root_NegativeNumberGiven_ShouldThrowException(){
		ComplexNumber number = new ComplexNumber(1, 1);
		number.root(-2);
	}
	
	@Test
	public void fromMagnitudeAndAngle_TwoDoubleValuesGiven_ShouldReturnComplexNumber(){
		ComplexNumber received = ComplexNumber.fromMagnitudeAndAngle(1, Math.PI);
		ComplexNumber actual = new ComplexNumber(-1, 0);
		Assert.assertTrue(received.equals(actual));
	}
	
	@Test
	public void getAngle_TestingIfBranch_ShouldReturnPositiveAngle(){
		ComplexNumber number = new ComplexNumber(0, -1);
		double received = number.getAngle();
		Assert.assertTrue(Math.abs(received - 3*Math.PI/2)<1E-4);
	}
	
	@Test
	public void toString_ImaginaryNegative_ShouldReturnString(){
		ComplexNumber number = new ComplexNumber(1.2, -2.3);
		String actual = "1,200000-2,300000i";
		String received = number.toString();
		Assert.assertTrue(received.equals(actual));
	}
	
	@Test
	public void toString_ImaginaryPositive_ShouldReturnString(){
		ComplexNumber number = new ComplexNumber(-1.2, 2.3);
		String actual = "-1,200000+2,300000i";
		String received = number.toString();
		Assert.assertTrue(received.equals(actual));
	}
	
	@Test
	public void equals_DiffenertComplexNumbersGiven_ShouldReturnFalse(){
		ComplexNumber firstNumber = new ComplexNumber(1, 2);
		ComplexNumber secondNumber = new ComplexNumber(1, -2);
		Assert.assertFalse(firstNumber.equals(secondNumber));
	}
	
	@Test
	public void equals_DiffenertComplexNumbersGiven_ShouldReturnFalse_Test2(){
		ComplexNumber firstNumber = new ComplexNumber(1, 2);
		ComplexNumber secondNumber = new ComplexNumber(-1, -2);
		Assert.assertFalse(firstNumber.equals(secondNumber));
	}
	
	@Test
	public void equals_StringGiven_ShouldREturnFalse(){
		ComplexNumber number = new ComplexNumber(1, 2);
		Assert.assertFalse(number.equals(new String("hello")));
	}
}
