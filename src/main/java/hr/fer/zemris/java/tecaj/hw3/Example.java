package hr.fer.zemris.java.tecaj.hw3;

public class Example {
	public static void main(String[] args) {
		ComplexNumber c1 = new ComplexNumber(2, 3);
		ComplexNumber c2 = ComplexNumber.parse("2.5-3i");
		ComplexNumber[] c3 = c1.add(ComplexNumber.fromMagnitudeAndAngle(2, 1.57))
		.div(c2).power(3).root(2);
		System.out.println(c3[0]);
		System.out.println(c3[1]);
		
//		ComplexNumber c1 = new ComplexNumber(1, 1);
//		ComplexNumber c2 = new ComplexNumber(1, 3.9);
//		ComplexNumber[] c3 = c1.root(5);
//		System.out.println(c3[0]);
//		System.out.println(c3[1]);
//		System.out.println(c3[2]);
//		System.out.println(c3[3]);
//		System.out.println(c3[4]);
	}
}
