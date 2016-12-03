package bq.java8.lambda;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


/**
 * show how to return a lambda
 * 
 * @author qibo
 *
 */

public class InterfaceLambda {

	@Test
	public void test() {
		MyMath ob = new MyMath();
		
		assertTrue(MyMath.checker(ob.is_odd(), 3));
		assertTrue(MyMath.checker(ob.is_prime(), 23));
		assertTrue(MyMath.checker(ob.is_palindrome(), 88688));
		
		assertFalse(MyMath.checker(ob.is_odd(), 2));
		assertFalse(MyMath.checker(ob.is_prime(), 21));
		assertFalse(MyMath.checker(ob.is_palindrome(), 886));
	}
	
}

interface PerformOperation {
	boolean check(int a);
}

class MyMath {
	public static boolean checker(PerformOperation p, int num) {
		return p.check(num);
	}

	// return a lambda
	public PerformOperation is_odd() {
		return (int a) -> {
			return a % 2 == 0 ? false : true;
		};
	}

	// return a lambda
	public PerformOperation is_prime() {
		return (int a) -> {
			if( a==1 || a==2)
				return true;
			
			if (a % 2 == 0)
				return false;

			for (int i = 3; 2 * i < a; i++) {
				if (a % i == 0)
					return false;
			}

			return true;
		};
	}

	// return a lambda
	public PerformOperation is_palindrome() {
		return (int a) -> {
			String s = String.valueOf(a);
			int header = 0;
			int tail = s.length() - 1;
			while (header < tail) {
				if (s.charAt(header) != s.charAt(tail))
					return false;

				header++;
				tail--;
			}

			return true;
		};
	}

}