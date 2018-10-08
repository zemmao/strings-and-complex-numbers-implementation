package hr.fer.zemris.java.tecaj.hw3;

public class Example2 {
	public static void main(String[] args) {
		IntegerSequence range = new IntegerSequence(1,1, 2);
		for (int i : range) {
			for (int j : range) {
				System.out.println("i=" + i + ", j=" + j);
			}
		}
	}
}
