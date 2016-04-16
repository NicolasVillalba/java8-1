package bq.java8.stream;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * Steam usage in file
 * 
 * @author qibo
 *
 */
public class FileStream {

	@Test
	public void test(){
		File file = new File(getClass().getClassLoader().getResource("streamtest.txt").getFile());
		
		Path path = file.toPath();
		
		// java 7 feature: try resource, don't need release in finally clause
		try(Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)){
			stream.onClose(()->System.out.println("Done Read!!"))
				.forEach(System.out::println);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
