// import org.apache.commons.csv.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CSVReader {
    public CSVReader() {
    }

    public List<String> make_dictionary() {
        try {           
            List<String> columnData = new ArrayList<>();
                
            BufferedReader br = new BufferedReader(new FileReader("english-dictionary.csv"));

            String line = br.readLine();

            // Skip the first line
            line = br.readLine();
            int counter = 0;
            while (line != null) {
                String[] values = line.split(",");
                values[0] = values[0].toLowerCase(Locale.ROOT);
                // System.out.println(values[0]);
                
                // If the value doesnt unique or it contains another character than letters, skip it
                if (columnData.contains(values[0]) || !values[0].matches("[a-zA-Z]+")) {
                    line = br.readLine();
                    continue;
                }
                columnData.add(values[0]);
                line = br.readLine();
                counter++;
                // print counter every 30000 lines
                if (counter % 30000 == 0) {
                    System.out.println(counter);
                }
            }

            // Print the length
            System.out.println("Length of dictionary: " + columnData.size());

            br.close();

            return columnData;

        } catch(Exception e) {
            e.printStackTrace();
        }

        // return empty list
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        CSVReader csvReader = new CSVReader();
        List<String> dictionary = csvReader.make_dictionary();

        // Generate new txt files that contain the words in the dictionary with this format: {"String", "String", "String", ...}
        try {
            FileWriter myWriter = new FileWriter("dictionary.txt");
            myWriter.write("{" + String.join(", ", dictionary) + "}");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}