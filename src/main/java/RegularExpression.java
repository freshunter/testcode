
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegularExpression
{
	//find integer in string
	static void  findInteger() {
		
		String str = "kkk34kj1kiousdfjk3jas893nnknue8uykjnfm,sw3jh333";
		List<Integer> numbers = new LinkedList<Integer>();
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(str); 
		while (m.find()) {
			numbers.add(Integer.parseInt(m.group()));
		}
		for (Integer integer : numbers)
		{
			System.out.println(integer);
		}
	}
	
	public static void main(String[] args)
	{
		findInteger();
		compilePattern();
		replaceSpace() ;
		testPrime();
	}

	private static void compilePattern()
	{
		Pattern p = Pattern.compile("a*b");
		Matcher matcher = p.matcher("aaaaab");
		assert matcher.matches() == true;
		System.out.println(matcher.matches() );
		
		boolean b = Pattern.matches("a*b", "aaaaab"); //still compiled implicitly
	}
	
	private static void replaceSpace() {
		String line = "  aa bbbbb   ccc     d  ";
		// " aa bbbbb ccc d "
		System.out.println(line.replaceAll("[\\s]+", " "));
	}
	
	public static void testPrime() {
		  // false
		  System.out.println(prime(1));
		  // true
		  System.out.println(prime(2));
		  // true
		  System.out.println(prime(3));
		  // true
		  System.out.println(prime(5));
		  // false
		  System.out.println(prime(8));
		  // true
		  System.out.println(prime(13));
		  // false
		  System.out.println(prime(14));
		  // false
		  System.out.println(prime(15));
		}
		 
		public static boolean prime(int n) {
		  return !new String(new char[n]).matches(".?|(..+?)\\1+");
		}

}


