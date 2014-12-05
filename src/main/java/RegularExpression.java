
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
//		findInteger();
//		compilePattern();
//		replaceSpace() ;
//		testPrime();
//		backreferences();
	    testNetconf();
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
		
		
		public static void backreferences() {
		    String regex = "(test)kk\\1";
		    Pattern p = Pattern.compile(regex);
		        Matcher m = p.matcher("mmtestkktestkkk");
		        while (m.find()) {
		            System.out.println(m.group());
		        }
		        System.out.println(m.matches() );
		    
		}
		
		public static void testNetconf() {
		    String netconf = " <error-tag>data-missing</error-tag>"
		            + "<error-severity>error</error-severity>"
		            + "<nc:error-path xmlns:base=\"http://www.calix.com/ns/exa/base\" >"
		            +"\n"
		            +"/nc:rpc/nc:edit-config/nc:config/base:config/base:profile/base:match-list[base:name='test_matchList']"
		            
		            +"\n"
		            + " </nc:error-path>" + "\n" +
		            "<error-info>" +"\n" 
		            + "<bad-element>match-list</bad-element>"
		            +"\n"
		            + "</error-info>"
		            +"\n"
		            + "</rpc-error>";
		    
		    String regex = "<error-tag>(?<tag>.*)</error-tag>|<error-app-tag>(?<apptag>.*)</error-app-tag>|<nc:error-path.*?>(?<path>.*)</nc:error-path>|<error-message.*?>(?<message>.*)</error-message>|<error-info.*?>(?<info>.*)</error-info>";

	            Pattern p = Pattern.compile(regex, Pattern.DOTALL);
	                Matcher m = p.matcher(netconf);
	                int index = 1;
	                while (m.find()) {
	                    System.out.println(m.group());
	                        System.out.println(m.group(1));
	                        System.out.println(m.group(2));
	                        System.out.println(m.group(3));
	                        System.out.println("===========");
	                        System.out.println(m.group("path"));
	                            System.out.println(m.group("tag"));
	                            System.out.println(m.group("info"));

	                            System.out.println("===========");
	                            System.out.println("===========");
	                }
		}

}


