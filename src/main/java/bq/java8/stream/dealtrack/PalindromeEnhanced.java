package bq.java8.stream.dealtrack;

import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;
    
/**
 * 
 * Palindrome
 * 
 * @author qibo
 *
 */
public class PalindromeEnhanced {
   
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        
        List<String> inputs = new LinkedList<String>();
        while (scan.hasNext()) {
            String input = scan.nextLine();
            inputs.add(input);
        }
        
        scan.close();
        
        inputs.forEach(v -> {
            boolean result = isPalindrome(v);
            System.out.println(result?"YES":"NO");
        });
        
    }
    
    public static boolean isPalindrome(String input) {
        if (input == null)
            return true;
        
        int head = 0;
        int tail = input.length() - 1;
        
        boolean isPalindrome = true;
        while (head < tail) {
            // skip all ignored chars from head
            while (!isValidChar(input.charAt(head))) {
                head++;
                continue;
            }
            
            if (head >= tail)
                break;
            
            // skip all ignored chars from end
            while(!isValidChar(input.charAt(tail))) {
                tail--;
                continue;
            }
            
            if (head >= tail)
                break;
            
            // check if head == tail
            if (Character.toLowerCase(input.charAt(head)) != Character.toLowerCase(input.charAt(tail))) {
                isPalindrome = false;
                break;
            }
            
            // next round
            head++;
            tail--;
        }
        
        return isPalindrome;
    }
    
    public static boolean isValidChar(char c) {
        if (c >= 'a' && c <= 'z')
            return true;
        
        if (c >= 'A' && c <= 'Z')
            return true;
        
        if (c >='0' && c <= '9')
            return true;
        
        return false;
    }
    
}