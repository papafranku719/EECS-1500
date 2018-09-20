import java.util.*;
// Written by Frank Parker
// Program 3
// Creates an array out of 8 integers, prints
// out the reverse, and finds the average of its sum
public class Reverse {
    public static void main(String[]args){
        Scanner stdin = new Scanner(System.in);
        int[] integer = new int[8];

        System.out.println("Please enter 8 positive integers: ");
        integerInput(integer);
        reverse(integer);
        average(integer);
    }

    public static int integerInput (int[] integer){
        Scanner stdin = new Scanner(System.in);
        int i;

        for (i = 0; i < 8; i++){
            integer[i] = stdin.nextInt();
        }
        return i;
    }

    public static void reverse(int[] integer){
        System.out.println("The values in reverse order are:");
        System.out.println(integer[7] + " " + integer[6] + " " + integer[5]
                + " " + integer[4] + " " + integer[3] + " " + integer[2] +
                " " + integer[1] + " " + integer[0]);
    }

    public static double average(int [] integer){
        double sum;
        double average;

        sum = integer[0] + integer[1] + integer[2] + integer[3] + integer[4]
                + integer[5] + integer[6] + integer[7];
        average = (sum/8);

        System.out.println("The average is " + sum + "/8 = " + average);
        return sum;
    }
}
