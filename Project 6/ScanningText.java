import java.util.*;
// Written by Frank Parker
// Program 4
// Takes a line of text as a string,
// counts the number of letters and tokens, and prints
// out the number of occurrences per letter
public class ScanningText {
    public static void main(String[]args){
        Scanner stdin = new Scanner(System.in);
        String line;

        System.out.println("Enter a single line of text.");
        line = stdin.nextLine();
        System.out.println("The number of letters is " + charAt(line) + ".");
        System.out.println("The frequency of letters is: ");
        frequency(line);
    }

    public static String frequency(String line){
        String input = line.toLowerCase();
        int[] alphabetArray = new int[26];
        for (int i = 0; i < input.length(); i++){
            char ch = input.charAt(i);
            int value = (int) ch;
            if (value >= 97 && value <= 122){
                alphabetArray[ch-'a']++;
            }
        }
        for (int i = 0; i < alphabetArray.length; i++){
            if(alphabetArray[i] > 0){
                char ch = (char)(i + 97);
                System.out.println(ch + " -- " + alphabetArray[i]);
            }
        }
        return input;
    }

    public static int charAt(String line){
        int charCount;
        charCount = 0;
        char temp;

        for (int i = 0; i < line.length(); i++){
            temp = line.charAt(i);

            if (temp != ' ' && temp != '.' && temp != ','){
                charCount++;
            }
        }
        return charCount;
    }
}
