import org.apache.commons.lang3.StringUtils;
import java.util.Arrays;
// Written by Frank Parker
// Program 5
// Designs an array and prints out a list of runners and then
// lists the fastest and second-fastest runners and their respective times.
// StringUtils.rightPad usage courtesy of Apache Commons
// https://commons.apache.org/proper/commons-lang/download_lang.cgi
public class FastestRunner{
    public static void main(String[] args){
        Runner[] runners = {
                new Runner("Elena", 341),
                new Runner("Thomas", 273),
                new Runner("Hamilton", 278),
                new Runner("Suzie", 329),
                new Runner("Phil", 445),
                new Runner("Matt", 402),
                new Runner("Alex", 388),
                new Runner("Emma", 275),
                new Runner("John", 243),
                new Runner("James", 334),
                new Runner("Jane", 412),
                new Runner("Emily", 393),
                new Runner("Daniel", 299),
                new Runner("Neda", 343),
                new Runner("Aaron", 317),
                new Runner("Kate", 265),
        };
        System.out.println(Runner.asciiTable(runners));
        Arrays.sort(runners);
        System.out.println(runners[0].toString());
        System.out.println(runners[1].toString());
    }

    private static class Runner
            implements Comparable<Runner>{

        private final String name;

        private String getName(){
            return this.name;
        }
        private final Integer time;

        private Integer getTime(){
            return this.time;
        }
        private Runner(String name, Integer time){
            this.name = name;
            this.time = time;
        }
        private static String asciiTable(Runner[] runners){
            int longestNameLength;
            longestNameLength = 0;
            int longestTimeLength;
            longestTimeLength = 0;

            for (Runner runner : runners){
                int nameLength;
                nameLength = runner.getName().length();
                int timeLength;
                timeLength = runner.getTime().toString().length();

                if (nameLength > longestNameLength)
                    longestNameLength = nameLength;

                if (timeLength > longestTimeLength)
                    longestTimeLength = timeLength;
            }
            String outputTable = "+" + StringUtils.rightPad("", longestNameLength, '-') + "+"
                    + StringUtils.rightPad("", longestTimeLength, '-') + "+\n";
            for (Runner runner : runners){
                outputTable += "|" + StringUtils.rightPad(runner.getName(), longestNameLength) + "|"
                        + StringUtils.rightPad(runner.getTime().toString(), longestTimeLength) + "|\n";
                outputTable += "+" + StringUtils.rightPad("", longestNameLength, '-') + "+"
                        + StringUtils.rightPad("", longestTimeLength, '-') + "+\n";
            }
            return outputTable;
        }
        public String toString(){
            return String.format("%s, %dh %dm", this.getName(),
                    this.getTime() / 60, this.getTime() % 60);
        }
        public int compareTo(Runner o){
            return this.getTime().compareTo(o.getTime());
        }
    }
}
