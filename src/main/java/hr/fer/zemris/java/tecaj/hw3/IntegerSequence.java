package hr.fer.zemris.java.tecaj.hw3;

import java.util.Iterator;

/**
 * Class {@link IntegerSequence} represents collection object and implements {@link Iterable}.
 * In Iterator design pattern, this class is ConcreteAggregate.
 * @author Zemunik
 * @version 1.0
 */
public class IntegerSequence implements Iterable<Integer>{

	/*Integer variables that represent first number, last number and step in loop.*/
	private final int first, last, step;
	
	/**
	 * Constructor that sets all attributes: first, last and step number.
	 * @param first given first number of loop
	 * @param last given last number of loop
	 * @param step given step number of loop
	 * @throws IllegalArgumentException if given loop arguments are invalid
	 */
	public IntegerSequence(int first, int last, int step) {
		if((first <= last && step > 0) || (first >= last && step < 0)){
			this.first = first;
			this.last = last;
			this.step = step;
		} else{
			throw new IllegalArgumentException("Illegal loop arguments were provided.");
		}
		
	}
	
	/**
	 * Overridden method which return instance of {@link NumberIterator}. Those instances
	 * know how to iterate over elements of this loop.
	 * @return instance of  {@link NumberIterator} class
	 */
	@Override
	public Iterator<Integer> iterator() {
		return new NumberIterator();
	}

	/**
	 * Class {@link NumberIterator} implements interface {@link Iterator}. Instances of
	 * this class know how to iterate over {@link IntegerSequence} collection objects.
	 * @author Zemunik
	 * @version 1.0
	 */
	private class NumberIterator implements Iterator<Integer>{

		/*Attribute that represents current number in loop.*/
		private int currentNumber;
		
		/**
		 * Constructor sets current number to first integer in loop.
		 */
		public NumberIterator() {
			currentNumber = first;
		}
		
		/**
		 * Overridden {@link Iterator}  method which checks whether there are more elements 
		 * in loop.
		 * @return true if there are more elements in loop, false otherwise
		 */
		@Override
		public boolean hasNext() {
			if(step > 0){
				return currentNumber <= last;
			} else {
				return currentNumber >= last;
			}
			
		}

		/**
		 * Overridden {@link Iterator} method which returns current number of loop and
		 * fetches next one.
		 * @return value of current number
		 * @throws RuntimeException if there are no more elements in loop
		 */
		@Override
		public Integer next() {
			if(step > 0 && currentNumber > last){
				throw new RuntimeException("No more elements.");
			}
			if(step <0 && currentNumber < last){
				throw new RuntimeException("No more elements.");
			}
			int value = currentNumber;
			currentNumber = currentNumber + step;
			return value;
		}
		
	}
}
