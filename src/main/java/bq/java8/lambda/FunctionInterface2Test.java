package bq.java8.lambda;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * customize interface which accept 3 arguments
 * 
 * Note: customize interface without annotation {@link FunctionalInterface}:
 * 
 * "However, the compiler will treat any interface meeting the definition of a
 * functional interface as a functional interface regardless of whether or not a
 * FunctionalInterface annotation is present on the interface declaration."
 * 
 * 
 * @author jonathan.q.bo@gmail.com
 *
 */
public class FunctionInterface2Test {

	@Test
	public void test(){
		int v1 = 1, v2 = 2, v3 = 3;
		
		ThreeArgsFunction<Integer,Integer,Integer,Integer> function = FunctionInterface2Test::function1;
		
		assertEquals(6, function.apply(v1, v2, v3).intValue());
	}
	
	@Test
	public void testBad(){
		int v1 = 1, v2 = 2, v3 = 3;
		
		// No @FunctionalInterface. But it's still good
		ThreeArgsFunctionWithoutAnnotation<Integer,Integer,Integer,Integer> function = FunctionInterface2Test::function1;
		
		assertEquals(6, function.apply(v1, v2, v3).intValue());
	}
	
	private static int function1(int v1, int v2, int v3){
		return v1 + v2 + v3;
	}
	
}

@FunctionalInterface
interface ThreeArgsFunction<T,M,N,R>{
	public R apply(T t, M m, N n);
}

/**
 * No @FunctionalInterface. But it's still good
 *
 * @param <T>
 * @param <M>
 * @param <N>
 * @param <R>
 */
interface ThreeArgsFunctionWithoutAnnotation<T,M,N,R>{
	public R apply(T t, M m, N n);
}