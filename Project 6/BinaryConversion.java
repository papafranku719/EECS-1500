import java.util.*;
// Written by Frank Parker
// Program 2
// Takes a binary string and
// converts it to a decimal integer
public class BinaryConversion {
    public static void main(String[]args){
        Scanner stdin = new Scanner(System.in);
        String binaryString;

        while (true){
            System.out.print("Please enter a binary number: ");
            binaryString = stdin.next();

            if (binaryString.equals("-1")){
                break;
            }
            if (isBinary(binaryString)){
                System.out.println("Conversion to decimal is: " + binaryToDecimal(binaryString));
            } else {
                System.out.println("Inputted string is not binary.");
            }
        }
        System.out.println("All set!");
    }

    public static int binaryToDecimal(String binaryString){
        int decimal;
        decimal = 0;

        for (int i = 0, j = binaryString.length() - 1; i < binaryString.length(); i++, j--){
            if (binaryString.charAt(j) == '1'){
                decimal += Math.pow(2, i);
            }
        }
        return decimal;
    }

    public static boolean isBinary(String binaryString){
        for (char c : binaryString.toCharArray()){
            if (c != '0' && c != '1'){
                return false;
            }
        }
        return true;
    }
}
