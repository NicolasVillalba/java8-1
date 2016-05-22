package bq.java8.stream;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * Count string occurrence in a file 
 * and compare the performance with/without parallel mapping
 * 
 * result of total 228096 out of 456896:
 * Time cost without parallel: 439 ms
 * Time cost in parallel: 152 ms
 * 
 * @author qibo
 *
 */
public class FileStreamPerformance {

	/**
	 * find how many occurrence of String in a File
	 */
	@Test
	public void testFindCount(){
		long from = System.currentTimeMillis();
		
		File file = new File(getClass().getClassLoader().getResource("streamtest_perf.txt").getFile());
		
		Path path = file.toPath();
		
		String target = "path";
		
		// java 7 feature: try resource, don't need release in finally clause
		try(Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)){
			int count = stream.mapToInt(value->countOccur(value, target)).sum();
			
			System.out.println(target + " occurs " + count + " times");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Time cost without parallel: " + (System.currentTimeMillis() - from) + " ms");
		
	}
	
	/**
	 * find how many occurrence of String in a File
	 */
	@Test
	public void testFindCountInParallel(){
		long from = System.currentTimeMillis();
		
		File file = new File(getClass().getClassLoader().getResource("streamtest_perf.txt").getFile());
		
		Path path = file.toPath();
		
		String target = "path";
		
		// java 7 feature: try resource, don't need release in finally clause
		try(Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)){
			int count = stream.parallel().mapToInt(value->countOccur(value, target)).sum();
			
			System.out.println(target + " occurs " + count + " times");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Time cost in parallel: " + (System.currentTimeMillis() - from) + " ms");
		
	}

	/**
	 * Can be replace with Apache StringUtils:
	 * 
	 * int count = StringUtils.countMatches("engineering", "e");
	 * 
	 * @param value
	 * @param target
	 * @return
	 */
	private int countOccur(String value, String target) {
		Pattern p = Pattern.compile(target);
		Matcher matcher = p.matcher(value);
		
		int count = 0;
		while(matcher.find())
			count++;
		
		return count;
	}
	
}
