package bq.java8.stream.dealtrack;

import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import java.math.BigInteger;

/**
 * stream groupby with same order of appearance
 * 
 * the default groupby implement by java8 is not order guaranteed because it's using HashMap which is no-order.
 * 
 * @author qibo
 *
 */
public class OrderedGroupby {
    
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        
        List<String> inputs = new LinkedList<String>();
        while (scan.hasNext()) {
            String line = scan.nextLine();
            inputs.add(line);
        }
        
        scan.close();
        
        groupSum(inputs);
    }
    
    public static void groupSum(List<String> inputs) {
        inputs.stream()
            .map(OrderedGroupby::parse)
            .filter(p -> p!=null)
            // NOTE: make it order guaranteed by using LinkedHashMap
            .collect(Collectors.groupingBy(
            		(Pair p) -> p.key,
            	    LinkedHashMap::new,
            	    Collectors.mapping((Pair p) -> p.value, Collectors.toList())
            	))
            .forEach((key,values) -> {
                BigInteger sum = values.stream()
                    .reduce((v1,v2) -> { return v1.add(v2);})
                    .get();
                
                System.out.println(key + "," + sum);
            });
    } 
    
    private static Pair parse(String s) {
        if(s == null)
            return null;
        
        String[] arr = s.split(",");
        if(arr.length != 2)
            return null;
        
        try {
            return new Pair(arr[0].trim(), new BigInteger(arr[1].trim()));
        } catch (NumberFormatException e) {
            return null;
        }
        
    }
    
}

class Pair {
    public BigInteger value;
    public String key;
    
    public Pair(String key, BigInteger value) {
        this.key = key;
        this.value = value;
    }
}