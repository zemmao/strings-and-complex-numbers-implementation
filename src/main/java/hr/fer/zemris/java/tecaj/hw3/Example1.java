package hr.fer.zemris.java.tecaj.hw3;

public class Example1 {

	public static void main(String[] args) {
		CString a = new CString("ab");
		CString b = new CString("abab");
		CString d = new CString("testing");
//		CString c = new CString("ababab").replaceAll(a,b);
//		System.out.println(c);
		
//		System.out.println(a.length());
//		System.out.println(a.charAt(1));
		
		char[] data = new char[]{'t','e','s','t','i','n','g'};
//		CString c = new CString(data, 2, 3);
//		System.out.println(c);
		
		CString c = new CString(data);
//		System.out.println(c);
		
//		CString c = d.substring(2, 5);
//		System.out.println(c);
		
//		CString e = new CString("ing");
//		System.out.println(c.contains(e));
		
		System.out.println(c.right(4));
	}

}
