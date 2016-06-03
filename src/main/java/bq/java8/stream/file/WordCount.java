package bq.java8.stream.file;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.Test;

public class WordCount {
	
	@Test
	public void test() throws IOException{
		File file = new File(getClass().getClassLoader().getResource("streamtest.txt").getFile());
		Path path = file.toPath();
		
		System.out.println("Count by each word:");
		try(Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)){
			WordCount counter = new WordCount();
			counter.countByWord(stream)
					.entrySet()
					.forEach(entry -> System.out.println("\t" + entry.getKey() + ":" + entry.getValue()));
		}
		
		System.out.println("Total word count:");
		
		try(Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)){
			WordCount counter = new WordCount();
			long total = counter.countTotal(stream);
			System.out.println(total);
		}

	}
	
	
	
	public long countTotal(Stream<String> sentences){
		return sentences.flatMap(line -> WordCount.split(line).stream())
				.count();
	}

	public HashMap<String, Integer> countByWord(Stream<String> sentences){
		return sentences.flatMap(line -> WordCount.split(line).stream())
						.collect(HashMap<String, Integer>::new,
								WordCount::accumulator,
								WordCount::combiner);
	}
	
	private static List<String> split(String sentence){
		List<String> list = new ArrayList<>(); 
		list.addAll(Arrays.asList(sentence.split("\\s"))); 
		return list;
	}
	
	private static void accumulator(Map<String, Integer> result, String value){
		if(result.containsKey(value))
			result.put(value, result.get(value)+1);
		else
			result.put(value, 1);
	}
	
	public static void combiner(Map<String ,Integer> result1, Map<String, Integer> result2){
		result2.entrySet().forEach(entry -> {
			String key = entry.getKey();
			Integer curValue = result1.containsKey(key) ? result1.get(key) : 0;
			
			result1.put(key, curValue + entry.getValue());
		});
	}
	
}
