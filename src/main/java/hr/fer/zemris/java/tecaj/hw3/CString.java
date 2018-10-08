package hr.fer.zemris.java.tecaj.hw3;

/**
 * Class {@link CString} represents the implementation of the class String. The general idea 
 * of the implementation is for multiple instances of strings to share a single character
 * array and to remember which part of the array belongs to each instance.
 * @author Zemunik
 * @version 1.0
 */
public class CString {

	/*Char array that belongs to string.*/
	private final char[] data;
	/*Integer that represents offset of string in char array data. Index
	 * where string starts in data.*/
	private final int offset;
	/*Integer that represents length of string in char array data.*/
	private final int length;
	
	/**
	 * Private constructor for creating new {@link CString} instance.
	 * @param data char array of string
	 * @param offset starting index in char array
	 * @param length  number of characters in data that belong to string
	 * @param flag true if new char array is needed, false otherwise
	 * @throws IllegalArgumentException if data == null or invalid offset/length given
	 */
	private CString(char[] data, int offset, int length, boolean flag){
		if(data == null){
			throw new IllegalArgumentException("Data array can not be null.");
		}
		if(offset < 0 || offset >= data.length){
			throw new IllegalArgumentException("Invalid offset was provided.");
		}
		if(length < 0 || length > data.length){
			throw new IllegalArgumentException("Invalid length was provided.");
		}
		if((offset + length -1 ) >= data.length){
			throw new IllegalArgumentException("Invalid offset and length were provided.");
		}
		if(flag == true){
			char[] newData =  new char[length];
			System.arraycopy(data, offset, newData, 0, length);
			this.data = newData;
			this.offset = 0;
			this.length = length;
		} else{
			this.data = data;
			this.offset = offset;
			this.length = length;
		}
	}
	
	/**
	 * Constructor that gets all three attributes.
	 * @param data char array of string
	 * @param offset starting index in char array
	 * @param length number of characters in data that belong to string
	 */
	public CString(char[] data, int offset, int length){
		this(data, offset, length, true);
	}
	
	/**
	 * Constructor that gets only data array. Sets offset to 0 and length to length of 
	 * data array.
	 * @param data char array of string
	 */
	public CString(char[] data){
		this(data, 0, data.length, false);
	}
	
	/**
	 * Constructor that gets instance of this class. If original's internal character 
	 * array is larger than needed, new instance must allocate its own character array 
	 * of minimal required size and copy data. Otherwise it must reuse original's 
	 * character array.
	 * @param original {@link CString} instance
	 */
	public CString(CString original){
		this(original.data, original.offset, original.length, 
				original.data.length > original.length);
	}
	
	/**
	 * Constructor that gets Java's string. Constructor copies its data to data array.
	 * @param s string that represents same character data as Java's String
	 * @throws NullPointerException if given string == null
	 */
	public CString (String s){
		this(s.toCharArray(), 0, s.length(), false);
	}
	
	/**
	 * Method returns length variable.
	 * @return length of {@link CString} instance
	 */
	public int length(){
		return length;
	}
	
	/**
	 * Method fetches char at given index of {@link CString} instance. 
	 * @param index given index
	 * @return char at given index
	 * @throws StringIndexOutOfBoundsException if index is out of CString's bounds
	 */
	public char charAt(int index){
		if(index < 0 || index >= length){
			throw new StringIndexOutOfBoundsException("Index is out of string's bounds.");
		}
		return data[offset + index];
	}
	
	/**
	 * Method returns {@link CString} instance as char array.
	 * @return char array
	 */
	public char[] toCharArray(){
		char[] returnData =  new char[length];
		System.arraycopy(data, offset, returnData, 0, length);
		return returnData;
	}
	
	/**
	 * Method returns {@link CString} instance as String.
	 * @return instance as String
	 */
	public String toString(){
		char[] returnData = new char[length];
		System.arraycopy(data, offset, returnData, 0, length);
		String dataString = new String(returnData);
		return dataString;
	}
	
	/**
	 * Method returns index of first occurrence of char or -1 if char doesn't exist in
	 * this {@link CString} instance.
	 * @param c given char
	 * @return index of first occurrence of char or -1
	 */
	public int indexOf(char c){
		for(int iterator = 0; iterator < length; iterator++){
			if(data[offset + iterator] == c){
				return iterator;
			}
		}
		return -1;
	}
	
	/**
	 * Method returns true if this string ends with given string, false otherwise.
	 * @param s given string
	 * @return true if this string ends with given string, false otherwise
	 */
	public boolean startsWith(CString s){
		if(s.length > length){
			return false;
		}
		char[] receivedData = s.data;
		int receivedLength = s.length;
		int receivedOffset = s.offset;
		int numberOfEquals = 0;
		
		for(int charIndex = 0; charIndex < receivedLength; charIndex++){
			if(receivedData[receivedOffset+charIndex] == data[offset+charIndex]){
				numberOfEquals++;
			}
		}
		return numberOfEquals == receivedLength;
	}
	
	/**
	 * Method returns true if this string ends with given string, false otherwise.
	 * @param s given string
	 * @return true if this string ends with given string, false otherwise
	 */
	public boolean endsWith(CString s){
		if(s.length > length){
			return false;
		}
		char[] receivedData = s.data;
		int receivedLength = s.length;
		int receivedOffset = s.offset;
		int numberOfEquals = 0;
		
		for(int iterator = 1; iterator <= receivedLength; iterator++){
			if(receivedData[receivedOffset+receivedLength-iterator] == 
					data[offset+length-iterator]){
				numberOfEquals++;
			}
		}
		return numberOfEquals == receivedLength;
	}
	
	/**
	 * Method returns true if this string contains given string at any position, 
	 * false otherwise.
	 * @param s given string
	 * @return true if this string contains given string at any position, false otherwise
	 */
	public boolean contains(CString s){
		if(s.length > length){
			return false;
		}
		char[] receivedData = s.data;
		int receivedLength = s.length;
		int receivedOffset = s.offset;
		int index = 0;
		
		while(index < length){
			if(data[offset+index] == receivedData[receivedOffset+0]){
				/*Variable index is candidate for matching received string
				 * and substring of this string.*/
				boolean found = findGivenStringInThisInstance(receivedData, receivedLength, 
						receivedOffset,index);
				if(found == true){
					return true;
				} else{
					index++;
				}
			} else{
				index++;
			}
		}
		/*End of this string has been reached.*/
		return false;
	}	
	
	/**
	 * Method checks whether given string has been found in this string (this 
	 * {@link CString} instance). 
	 * @param receivedData data of given string
	 * @param receivedLength length of given string
	 * @param receivedOffset offset of given string
	 * @param currentIndex index of this instance which is candidate for matching
	 * given string and substring of this instance
	 * @return true if given string has been found in this string, false otherwise
	 */
	private boolean findGivenStringInThisInstance(char[] receivedData, int receivedLength,
			int receivedOffset, int currentIndex){
		for(int iterator = 0; iterator < receivedLength; iterator++){
			if(offset+currentIndex+iterator >= length){
				/*End of this string has been reached.*/
				return false;
			}
			if(data[offset+currentIndex+iterator] == 
					receivedData[receivedOffset+iterator]){
				continue;
			} else{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Method returns new {@link CString} which represents a part of original string. 
	 * Position endIndex does not belong to the substring.
	 * @param startIndex starting index of substring
	 * @param endIndex ending index of substring
	 * @return new {@link CString} which represents a part of original string
	 * @throws StringIndexOutOfBoundsException if invalid starting or ending index 
	 * have been provided
	 * @throws IllegalArgumentException if start index greater than end index
	 */
	public CString substring(int startIndex, int endIndex){
		if(startIndex < 0 || startIndex > length){
			throw new StringIndexOutOfBoundsException("Invalid start index was provided.");
		}
		if(endIndex < 0 || endIndex > length){
			throw new StringIndexOutOfBoundsException("Invalid end index was provided.");
		}
		if(endIndex < startIndex){
			throw new IllegalArgumentException("End index has to be greater or "
					+ "equals to start index.");
		}
		int newOffset = startIndex;
		int newLength = endIndex - startIndex;
		return new CString(data, newOffset, newLength,  false);
	}
	
	/**
	 * Method returns new {@link CString} which represents starting part of original 
	 * string and is of length n.
	 * @param n given length of new string
	 * @return new {@link CString} which represents starting part of original string
	 * @throws StringIndexOutOfBoundsException if invalid n has been provided
	 */
	public CString left(int n){
		if(n < 0 || n > length){
			throw new StringIndexOutOfBoundsException("Invalid argument was provided.");
		}
		return new CString(data, offset, n, false);
	}
	
	/**
	 * Method returns new {@link CString} which represents ending part of original 
	 * string and is of length n.
	 * @param n given length of new string
	 * @return new {@link CString} which represents ending part of original string
	 * @throws StringIndexOutOfBoundsException if invalid n has been provided
	 */
	public CString right(int n){
		if(n < 0 || n > length){
			throw new StringIndexOutOfBoundsException("Invalid argument was provided.");
		}
		int newOffset = offset + length - n;
		return new CString(data, newOffset, n, false);
	}
	
	/**
	 * Method creates a new {@link CString} which is concatenation of current and given 
	 * string.
	 * @param s given string
	 * @return new {@link CString} which is concatenation of current and given string
	 */
	public CString add(CString s){
		char[] firstData = new char[length];
		System.arraycopy(data, offset, firstData, 0, length);
		char[] secondData = new char[s.length];
		System.arraycopy(s.data, s.offset, secondData, 0, s.length);
		
		char[] resultData = new char[firstData.length + secondData.length];
		System.arraycopy(firstData, 0, resultData, 0, firstData.length);
		System.arraycopy(secondData, 0, resultData, firstData.length, secondData.length);
		return new CString(resultData, 0, resultData.length, false);
	}
	
	/**
	 * Method creates a new {@link CString} in which each occurrence of old character is
	 * replaced with new character.
	 * @param oldChar old character that will be replaced
	 * @param newChar new character
	 * @return new {@link CString} in which each occurrence of old character is replaced 
	 * with new character
	 */
	public CString replaceAll(char oldChar, char newChar){
		char[] returnData = new char[length];
		System.arraycopy(data, offset, returnData, 0, length);
		for(int iterator = 0; iterator < returnData.length; iterator++){
			if(returnData[iterator] == oldChar){
				returnData[iterator] = newChar;
			}
		}
		return new CString(returnData, 0, returnData.length, false);
	}
	
	/**
	 * Method creates a new {@link CString} in which each occurrence of old substring is 
	 * replaced with the new substring.
	 * @param oldStr old substring that will be replaced
	 * @param newStr new substring
	 * @return new {@link CString} in which each occurrence of old substring is replaced 
	 * with the new substring
	 */
	public CString replaceAll(CString oldStr, CString newStr){
		char[] oldData = new char[oldStr.length];
		System.arraycopy(oldStr.data, oldStr.offset, oldData, 0, oldStr.length);
		char[] newData = new char[newStr.length];
		System.arraycopy(newStr.data, newStr.offset, newData, 0, newStr.length);
		int oldStrLen = oldStr.length, newStrLen = newStr.length;
		int difference = newStrLen - oldStrLen;
		/*Firstly, initialize returnData array with length of current data array*/
		char[] returnData = new char[length];	
		/*dataIndex remembers current index in current data array, returnDataIndex 
		 * remembers current index in new data array with replacements (returnData array)*/
		int dataIndex = 0, returnDataIndex = 0;		
		
		/*Iterate over existing data array and find replacements.*/
		while(dataIndex < length){	
			if(data[offset+dataIndex] == oldData[0]){
				/*Variable index is candidate for matching received string
				 * and substring of this string.*/
				boolean found = findGivenStringInThisInstance(oldData, oldStrLen, 
						0, dataIndex);
				if(found == true){
					/*If replacement is going to happen, returnData needs to get
					 * new dimensions (according to difference variable). */
					char[] tempData = new char[returnData.length + difference];
					if(difference < 0){
						System.arraycopy(returnData, 0, tempData, 0, 
								returnData.length + difference);
					} else{
						System.arraycopy(returnData, 0, tempData, 0, returnData.length);
					}
					returnData = tempData;
					
					for(int iterator = 0; iterator < newStrLen; iterator++){
						returnData[returnDataIndex] = newData[iterator];
						returnDataIndex++;
					}
					dataIndex = dataIndex + oldData.length;
				} else{
					returnData[returnDataIndex] = data[offset+dataIndex];
					dataIndex++;
					returnDataIndex++;
				}
			} else{
				returnData[returnDataIndex] = data[offset+dataIndex];
				dataIndex++;
				returnDataIndex++;
			}
		}
		
		return new CString(returnData, 0, returnData.length, false);
	}
	
	/**
	 * Method equals checks whether two {@link CString} instances have same attributes:
	 * char array data, offset and length.
	 * @param object, given {@link Object}, {@link CString} instance
	 * @return true all attributes match, false otherwise
	 */
	@Override
	public boolean equals(Object object){
		CString cString;
		if(object instanceof CString){
			cString = (CString) object;
		} else{
			return false;
		}
		
		if(offset == cString.offset && length == cString.length){
			for(int iterator = 0; iterator < length; iterator++){
				if(data[iterator] != cString.data[iterator]){
					return false;
				}
			}
			return true;
		}
		return false;
		
	}
	
}
