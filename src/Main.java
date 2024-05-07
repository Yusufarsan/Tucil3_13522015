import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) {

        // Create string list called dictionary
        try {
            // String content = new String(Files.readAllBytes(Paths.get("../test/dictionaryn.txt")));
            // List<String> dictionary = new ArrayList<>(Arrays.asList(content.split(", ")));

            List<String> dictionary = Files.readAllLines(Paths.get("../test/dictAsisten.txt"));


            // Save the length of longest word in the dictionary
            int maxLength = 0;
            for (String word : dictionary) {
                if (word.length() > maxLength) {
                    maxLength = word.length();
                }
            }

            String startWord;
            String goalWord;
            Scanner scanner = new Scanner(System.in);

            // validate start and goal word
            while (true) {
                // Take input from terminal start word and goal word
                System.out.print("Enter start word: ");
                startWord = scanner.nextLine();
                System.out.print("Enter goal word: ");
                goalWord = scanner.nextLine();
                System.out.println();
                if (dictionary.contains(startWord) && dictionary.contains(goalWord)) {
                    break;
                }
                else if (startWord.length() != goalWord.length()) {
                    System.out.println("Start word and goal word must have the same length.");
                }
                else if (startWord.length() > maxLength || goalWord.length() > maxLength) {
                    System.out.println("Word must less than or equal to " + maxLength + " characters.");
                } else if (!dictionary.contains(startWord)) {
                    System.out.println("Start word is not in the dictionary.");
                } else {
                    System.out.println("Goal word is not in the dictionary.");
                }
            }
    
            int algorithm = 0;
            while (algorithm < 1 || algorithm > 3) {
                // Choose the algorithm between UCS, Greedy best-first search, and A* search
                System.out.println("Choose the algorithm:");
                System.out.println("1. UCS");
                System.out.println("2. Greedy best-first search");
                System.out.println("3. A* search");
                System.out.print("Enter the number of the algorithm: ");
                algorithm = scanner.nextInt();
                System.out.println();
            }
    
            scanner.close();

            long startTime = System.currentTimeMillis();

    
            List<String> path = null;
            // Maap print depth nya masih salah (hanya utk kepentingan debugging)
            if (algorithm == 1) {
                Ucs ucs = new Ucs();
                path = ucs.ucs(startWord, goalWord, dictionary);
            } else if (algorithm == 2) {
                GreedyBFS greedy = new GreedyBFS();
                path = greedy.greedyBFS(startWord, goalWord, dictionary);
            } else if (algorithm == 3) {
                Asearch aStarSearch = new Asearch();
                path = aStarSearch.aStarSearch(startWord, goalWord, dictionary);
            } 

            
            
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            
            if (path.isEmpty()) {
                System.out.println("No path found.");
            } else {
                System.out.println("Path: " + path);
            }
            System.out.println("Execution time: " + executionTime + " ms");
            
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
