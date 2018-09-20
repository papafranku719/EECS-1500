import java.util.*;
import java.util.regex.*;
// Written by Frank Parker
// Program 1
// Takes in a string and checks it
// versus the pattern of "ddd-ddd-dddd"
// by using a regex library
public class ValidPhoneNumber {
    public static void main(String[]args){
        Scanner stdin = new Scanner(System.in);
        String phone;

        System.out.print("Please enter a phone number: ");
        phone = stdin.next();

        Pattern regex = Pattern.compile("\\d\\d\\d-\\d\\d\\d-\\d\\d\\d\\d");
        Matcher matcher = regex.matcher(phone);

        if (matcher.matches()){
            System.out.println("The phone number entered is valid.");
        } else {
            System.out.println("The phone number entered is invalid.");
        }
    }
}