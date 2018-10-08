package hr.fer.zemris.java.tecaj.hw3;

import org.junit.Test;
import org.junit.Assert;

/**
 * Class {@link CStringTest} is used for testing {@link CString}. Testing methods are
 * named: methodName_GivenArguments_ExpectedBehaviour. Coverage is 100%.
 * @author Zemunik
 * @version 1.0
 */
public class CStringTest {

	@Test (expected = IllegalArgumentException.class)
	public void firstPublicConstructor_InvalidCharArrayGiven_ShouldThrowException(){
		new CString(null, 0, 2);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void firstPublicConstructor_InvalidOffsetGiven_ShouldThrowException_Test1(){
		char[] data = new char[]{'a','s','d'};
		new CString(data, -1, 2);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void firstPublicConstructor_InvalidOffsetGiven_ShouldThrowException_Test2(){
		char[] data = new char[]{'a','s','d'};
		new CString(data, 6, 2);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void firstPublicConstructor_InvalidLengthGiven_ShouldThrowException_Test1(){
		char[] data = new char[]{'a','s','d'};
		new CString(data, 1, 5);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void firstPublicConstructor_InvalidLengthGiven_ShouldThrowException_Test2(){
		char[] data = new char[]{'a','s','d'};
		new CString(data, 1, -2);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void firstPublicConstructor_InvalidOffsetAndLengthGiven_ShouldThrowException(){
		char[] data = new char[]{'a','s','d'};
		new CString(data, 2, 3);
	}
	
	@Test
	public void secondPublicConstructor_CharArrayGiven_ShouldCreateCString(){
		char[] data = new char[]{'a','s','d'};
		CString received = new CString(data);
		CString actual = new CString(data, 0, data.length);
		Assert.assertTrue(received.equals(actual));
	}
	
	@Test
	public void thirdPublicConstructor_CString_ShouldCreateCString_Test1(){
		char[] originalData = new char[]{'t','e','s','t'};
		CString original = new CString(originalData, 0, 4);
		original = original.substring(1, 3);
		
		CString received = new CString(original);
		char[] actualData = new char[original.length()];
		System.arraycopy(originalData, 1, actualData, 0, 2);
		CString actual = new CString(actualData, 0, 2);
		Assert.assertTrue(received.equals(actual));
	}
	
	@Test
	public void thirdPublicConstructor_CString_ShouldCreateCString_Test2(){
		char[] originalData = new char[]{'t','e','s','t'};
		int originalOffset = 0;
		int originalLength = 4;
		CString original = new CString(originalData, originalOffset, originalLength);
		
		CString received = new CString(original);
		char[] actualData = originalData;
		CString actual = new CString(actualData, 0, originalLength);
		Assert.assertTrue(received.equals(actual));
	}
	
	@Test
	public void thirdPublicConstructor_CString_ShouldCreateCString_Test3(){
		char[] originalData = new char[]{'t','e','s','t'};
		CString original = new CString(originalData, 0, 4);
		original = original.substring(0, 2);
		
		CString received = new CString(original);
		char[] actualData = new char[original.length()];
		System.arraycopy(originalData, 0, actualData, 0, 2);
		CString actual = new CString(actualData, 0, 2);
		Assert.assertTrue(received.equals(actual));
	}
	
	@Test
	public void fourthPublicConstructor_StringGiven_ShouldCreateCString(){
		String givenString = "test";
		CString received = new CString(givenString);
		char[] data = new char[]{'t','e','s','t'};
		CString actual = new CString(data, 0, data.length);
		Assert.assertTrue(received.equals(actual));
	}
	
	@Test
	public void length_ShouldReturnInternalArrayLenght(){
		char[] data = new char[]{'t','e','s','t'};
		CString received = new CString(data);
		Assert.assertTrue(received.length() == data.length);
	}
	
	@Test
	public void charAt_ValidIndexGiven_ShouldReturnChar(){
		String givenString = "test";
		CString cString = new CString(givenString);
		Assert.assertTrue(cString.charAt(1) == 'e');
	}
	
	@Test (expected = StringIndexOutOfBoundsException.class)
	public void charAt_InvalidIndexGiven_ShouldThrowException_Test1(){
		String givenString = "test";
		CString cString = new CString(givenString);
		cString.charAt(-1);
	}
	
	@Test (expected = StringIndexOutOfBoundsException.class)
	public void charAt_InvalidIndexGiven_ShouldThrowException_Test2(){
		String givenString = "test";
		CString cString = new CString(givenString);
		cString.charAt(6);
	}
	
	@Test
	public void toCharArray_ShouldReturnCharArray(){
		String givenString = "test";
		CString currentString = new CString(givenString);
		CString received = currentString.substring(0, 3);
		char[] receivedData = received.toCharArray();
		char[] actualdata = new char[]{'t','e','s'};
		for(int i = 0;i < actualdata.length; i++){
			Assert.assertTrue(actualdata[i] == receivedData[i]);
		}
	}
	
	@Test
	public void toString_ShouldReturnString(){
		char[] data = new char[]{'t','e','s','t'};
		CString currentString = new CString(data);
		String actualString = "test";
		Assert.assertTrue(actualString.equals(currentString.toString()));
	}
	
	@Test
	public void indexOf_CharGiven_ShouldReturnInteger(){
		String givenString = "test";
		CString currentString = new CString(givenString);
		Assert.assertTrue(currentString.indexOf('t') == 0);
	}
	
	@Test
	public void indexOf_NonExistentCharGiven_ShouldReturnInteger(){
		String givenString = "test";
		CString currentString = new CString(givenString);
		Assert.assertTrue(currentString.indexOf('a') == -1);
	}
	
	@Test
	public void startsWith_CStringGivenCorrectly_ShouldReturnTrue(){
		CString currentString = new CString("test");
		CString givenString = new CString("te");
		Assert.assertTrue(currentString.startsWith(givenString));
	}
	
	@Test
	public void startsWith_CStringGivenIncorrectly_ShouldReturnFalse(){
		CString currentString = new CString("test");
		CString givenString = new CString("tt");
		Assert.assertFalse(currentString.startsWith(givenString));
	}
	
	@Test
	public void startsWith_CStringGivenTooLong_ShouldReturnFalse(){
		CString currentString = new CString("test");
		CString givenString = new CString("testest");
		Assert.assertFalse(currentString.startsWith(givenString));
	}
	
	@Test
	public void endsWith_CStringGivenCorrectly_ShouldReturnTrue(){
		CString currentString = new CString("test");
		CString givenString = new CString("st");
		Assert.assertTrue(currentString.endsWith(givenString));
	}
	
	@Test
	public void endsWith_CStringGivenIncorrectly_ShouldReturnFalse(){
		CString currentString = new CString("test");
		CString givenString = new CString("tt");
		Assert.assertFalse(currentString.endsWith(givenString));
	}
	
	@Test
	public void endsWith_CStringGivenTooLong_ShouldReturnFalse(){
		CString currentString = new CString("test");
		CString givenString = new CString("testst");
		Assert.assertFalse(currentString.endsWith(givenString));
	}
	
	@Test
	public void contains_CStringGivenCorrectly_ShouldReturnTrue(){
		CString currentString = new CString("test");
		CString givenString = new CString("es");
		Assert.assertTrue(currentString.contains(givenString));
	}
	
	@Test
	public void contains_CStringGivenIncorrectly_ShouldReturnFalse_Test1(){
		CString currentString = new CString("test");
		CString givenString = new CString("er");
		Assert.assertFalse(currentString.contains(givenString));
	}
	
	@Test
	public void contains_CStringGivenIncorrectly_ShouldReturnFalse_Test2(){
		CString currentString = new CString("test");
		CString givenString = new CString("ta");
		Assert.assertFalse(currentString.contains(givenString));
	}
	
	@Test
	public void contains_CStringGivenTooLong_ShouldReturnFalse(){
		CString currentString = new CString("test");
		CString givenString = new CString("testst");
		Assert.assertFalse(currentString.contains(givenString));
	}
	
	@Test
	public void substring_IndexesGivenCorrectly_ShouldReturnValidSubstring(){
		CString currentString = new CString("test");
		CString received = currentString.substring(1, 3);
		String receivedString = received.toString();
		Assert.assertTrue(receivedString.equals("es"));
	}
	
	@Test (expected = StringIndexOutOfBoundsException.class)
	public void substring_InvalidStartIndexGiven_ShouldThrowException_Test1(){
		CString currentString = new CString("test");
		currentString.substring(-1, 2);
	}
	
	@Test (expected = StringIndexOutOfBoundsException.class)
	public void substring_InvalidStartIndexGiven_ShouldThrowException_Test2(){
		CString currentString = new CString("test");
		currentString.substring(5, 2);
	}
	
	@Test (expected = StringIndexOutOfBoundsException.class)
	public void substring_InvalidEndIndexGiven_ShouldThrowException_Test1(){
		CString currentString = new CString("test");
		currentString.substring(1, 5);
	}
	
	@Test (expected = StringIndexOutOfBoundsException.class)
	public void substring_InvalidEndIndexGiven_ShouldThrowException_Test2(){
		CString currentString = new CString("test");
		currentString.substring(1, -1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void substring_StartIndexGreaterThanEndIndex_ShouldThrowException(){
		CString currentString = new CString("test");
		currentString.substring(3, 1);
	}
	
	@Test
	public void left_ValidNumberGiven_ShouldReturnStartingPartOfString(){
		CString currentString = new CString("test");
		CString received = currentString.left(3);
		String receivedString = received.toString();
		Assert.assertTrue(receivedString.equals("tes"));
	}
	
	@Test (expected = StringIndexOutOfBoundsException.class)
	public void left_InvalidNumberGiven_ShouldThrowException_Test1(){
		CString currentString = new CString("test");
		currentString.left(-1);
	}
	
	@Test (expected = StringIndexOutOfBoundsException.class)
	public void left_InvalidNumberGiven_ShouldThrowException_Test2(){
		CString currentString = new CString("test");
		currentString.left(5);
	}
	
	@Test
	public void right_ValidNumberGiven_ShouldReturnEndingPartOfString(){
		CString currentString = new CString("test");
		CString received = currentString.right(3);
		String receivedString = received.toString();
		Assert.assertTrue(receivedString.equals("est"));
	}
	
	@Test (expected = StringIndexOutOfBoundsException.class)
	public void right_InvalidNumberGiven_ShouldThrowException_Test1(){
		CString currentString = new CString("test");
		currentString.right(-1);
	}
	
	@Test (expected = StringIndexOutOfBoundsException.class)
	public void right_InvalidNumberGiven_ShouldThrowException_Test2(){
		CString currentString = new CString("test");
		currentString.right(5);
	}
	
	@Test
	public void add_CStringGiven_ShouldReturnNewConcatenatedCString(){
		CString currentString = new CString("test");
		CString givenString = new CString("hello");
		CString received = currentString.add(givenString);
		String receivedString = received.toString();
		Assert.assertTrue(receivedString.equals("testhello"));
	}
	
	@Test
	public void replaceAll_TwoCharsGiven_ShouldReturnStringWithReplacement(){
		CString currentString = new CString("test");
		CString received = currentString.replaceAll('t', 'b');
		String receivedString = received.toString();
		Assert.assertTrue(receivedString.equals("besb"));
	}
	
	@Test
	public void replaceAll_TwoStringsGiven_ShouldReturnStringWithReplacement_Test1(){
		CString currentString = new CString("ababab");
		CString oldStr = new CString("ab");
		CString newStr = new CString("abab");
		CString received = currentString.replaceAll(oldStr, newStr);
		String receivedString = received.toString();
		Assert.assertTrue(receivedString.equals("abababababab"));
	}
	
	@Test
	public void replaceAll_TwoStringsGiven_ShouldReturnStringWithReplacement_Test2(){
		CString currentString = new CString("ababab");
		CString oldStr = new CString("ab");
		CString newStr = new CString("a");
		CString received = currentString.replaceAll(oldStr, newStr);
		String receivedString = received.toString();
		Assert.assertTrue(receivedString.equals("aaa"));
	}
	
	@Test
	public void replaceAll_TwoStringsGiven_ShouldReturnStringWithReplacement_Test3(){
		CString currentString = new CString("ababab");
		CString oldStr = new CString("ab");
		CString newStr = new CString("ba");
		CString received = currentString.replaceAll(oldStr, newStr);
		String receivedString = received.toString();
		Assert.assertTrue(receivedString.equals("bababa"));
	}
	
	@Test
	public void replaceAll_TwoStringsGiven_ShouldReturnStringWithReplacement_Test4(){
		CString currentString = new CString("ababab");
		CString oldStr = new CString("c");
		CString newStr = new CString("ba");
		CString received = currentString.replaceAll(oldStr, newStr);
		String receivedString = received.toString();
		Assert.assertTrue(receivedString.equals("ababab"));
	}
	
	@Test
	public void replaceAll_TwoStringsGiven_ShouldReturnStringWithReplacement_Test5(){
		CString currentString = new CString("acbabab");
		CString oldStr = new CString("ac");
		CString newStr = new CString("d");
		CString received = currentString.replaceAll(oldStr, newStr);
		String receivedString = received.toString();
		Assert.assertTrue(receivedString.equals("dbabab"));
	}
	
	@Test
	public void equals_IncorrectObjectTypeGiven_ShouldReturnFalse(){
		CString currentString = new CString("test");
		Assert.assertFalse(currentString.equals("hello"));
	}
	
	@Test
	public void equals_IncorrectDataGiven_ShouldReturnFalse(){
		char[] data = new char[]{'t','e','s','t'};
		CString currentString = new CString(data, 0, data.length);
		char[] givenData = new char[]{'b','e','s','t'};
		CString givenString = new CString(givenData, 0, givenData.length);
		Assert.assertFalse(currentString.equals(givenString));
	}
	
	@Test
	public void equals_IncorrectOffsetGiven_ShouldReturnFalse(){
		char[] data = new char[]{'t','e','s','t'};
		CString currentString = new CString(data, 0, data.length);
		CString givenString = currentString.substring(1, currentString.length());
		Assert.assertFalse(currentString.equals(givenString));
	}
	
	@Test
	public void equals_IncorrectLengthGiven_ShouldReturnFalse(){
		char[] data = new char[]{'t','e','s','t'};
		CString currentString = new CString(data, 0, data.length);
		CString givenString = new CString(data, 0, (data.length-1));
		Assert.assertFalse(currentString.equals(givenString));
	}
	
}

