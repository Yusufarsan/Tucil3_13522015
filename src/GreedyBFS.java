import java.util.*;


public class GreedyBFS {
    public List<String> greedyBFS(String start, String goal, List<String> dictionary) {
        HashMap<String, String> cameFrom = new HashMap<>();     // To build path
        Set<String> explored = new HashSet<>();         // To store the explored nodes  
        String wordOfDepth = start;
        PriorityQueue<String> frontier = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(Util.heuristic(o1, goal), Util.heuristic(o2, goal));
            }
        });
        Set<String> inFrontier = new HashSet<>();               // Prevent backtracking

        // Calculate the used memory before
        long usedMemoryBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        
        frontier.add(start);
        cameFrom.put(start, null);
        explored.add(frontier.element());

        int currentDepth = 0;

        while (!frontier.isEmpty()) {
            String current = frontier.poll();
            // System.out.println("Current: " + current);

            // Just to check if the start word == goal word
            if (current.equals(goal)) {
                // Build and return the path when the goal is found
                List<String> path = new ArrayList<>();
                for (String node = goal; node != null; node = cameFrom.get(node)) {
                    path.add(0, node);
                }
                System.out.println("\nNode visited: " + explored.size() + " nodes");
                // Calculate the used memory after
                long usedMemoryAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                System.out.println("Memory usage: " + (usedMemoryAfter - usedMemoryBefore) + " bytes\n");
                return path;
            }
            
            // print current depth
            // if (current.equals(wordOfDepth)) {
            //     System.out.println("Searching depth: " + ++currentDepth);
            // }
            
            for (String word : dictionary) {
                if (word.length() == start.length() && !explored.contains(word) && !inFrontier.contains(word) && Util.isOneLetterDifferent(current, word)) {
                    frontier.add(word);
                    cameFrom.put(word, current);
                    if (word.equals(goal)) {
                        // Build and return the path when the goal is found
                        List<String> path = new ArrayList<>();
                        for (String node = goal; node != null; node = cameFrom.get(node)) {
                            path.add(0, node);
                        }
                        System.out.println("\nNode visited: " + explored.size() + " nodes");
                        // Calculate the used memory after
                        long usedMemoryAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                        System.out.println("Memory usage: " + (usedMemoryAfter - usedMemoryBefore) + " bytes\n");
                        return path;
                    }
                    explored.add(word);
                    inFrontier.add(word);           // Prevent backtracking
                    // if (current.equals(wordOfDepth)) {
                    //     wordOfDepth = word;
                    // }
                }
            }

        }
        System.out.println("\nNode visited: " + explored.size() + " nodes");
        // Calculate the used memory after
        long usedMemoryAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Memory usage: " + (usedMemoryAfter - usedMemoryBefore) + " bytes\n");
        return new ArrayList<>();
    }
}
