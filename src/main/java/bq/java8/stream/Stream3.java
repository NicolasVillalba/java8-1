package bq.java8.stream;

import java.util.Scanner;
import java.util.List;
import java.util.Map;
import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Stream3 {
    
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        
        List<String> inputs = new LinkedList<String>();
        inputs.add("a,10000000000000000000000000000000000000000000");
//        inputs.add("b,10");
//        inputs.add("a,20");
//        while (scan.hasNext()) {
//            String line = scan.nextLine();
//            inputs.add(line);
//        }
        
//        scan.close();
        
        groupSum(inputs);
        
        int a = 10;
        BigInteger.valueOf(a);
    }
    
    public static void groupSum(List<String> inputs) {
        inputs.stream()
            .map(Stream3::parse)
            .collect(Collectors.groupingBy(
            		(Pair p) -> p.key,
            	    LinkedHashMap::new,
            	    Collectors.mapping((Pair p) -> p.value, Collectors.toList())
            	))
            .forEach((key,values) -> {
//                int sum = values.stream().mapToInt(pair -> pair).sum();
            	
            	BigInteger sum = values.stream().map(v -> BigInteger.valueOf(v)).reduce((v1,v2) -> {
            		return v1.add(v2);
            		}).get();
                System.out.println(key + "," + sum.toString());
            });
    } 
    
    private static Pair parse(String s) {
    	
        String[] arr = s.split(",");
        return new Pair(arr[0], Integer.valueOf(arr[1]));
    }
    
}

class Pair {
    public int value;
    public String key;
    
    public Pair(String key, int value) {
        this.key = key;
        this.value = value;
    }
}
