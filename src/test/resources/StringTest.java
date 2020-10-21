import java.util.*;
public class StringTest {

	public static void main(String[] args) {
		String s = "Th!s !s @n !nterview";		
	for(int i=(s.length()-1) ; i>=0; i--) {
		if(s.charAt(i)> 65) && s.charAt(i)< 90 || s.charAt(97) && s.charAt(122)){
			System.out.println(s.charAt(i));
		    
		}
	}
		
		

	}

}
