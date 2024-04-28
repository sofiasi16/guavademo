import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;


public class GraphReader {

    public static void main(String[] args) {
        String filename = "wiki-Vote.txt";
        Set<String> nodes = new HashSet<>();
        Map<String, Set<String>> adjacencyList = new HashMap<>();
        int edges = 0;

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.startsWith("#")) {
                    continue;
                }

                String[] tokens = line.trim().split("\\s+");

                if (tokens.length >= 2) {
                    String sourceNode = tokens[0];
                    String targetNode = tokens[1];
                    nodes.add(sourceNode);
                    nodes.add(targetNode);

                    adjacencyList.computeIfAbsent(sourceNode, k -> new HashSet<>()).add(targetNode);
                    edges++;
                } else {
                    System.err.println("Line has unexpected format: " + line);
                }
            }

            scanner.close();
            int numNodes = nodes.size(); 
            System.out.println("Number of nodes: " + numNodes);
            System.out.println("Number of edges: " + edges);

            //System.out.println("Adjacency List:");
            for (Map.Entry<String, Set<String>> entry : adjacencyList.entrySet()) {
                //System.out.println(entry.getKey() + ": " + entry.getValue());

            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
            e.printStackTrace();
        }
    }
}
