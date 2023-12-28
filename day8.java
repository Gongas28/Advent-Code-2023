import java.util.HashMap;
import java.util.Map;

public class DesertIslandNavigation {

    private static Map<String, String[]> network = new HashMap<>();

    public static void main(String[] args) {
        initializeNetwork();
        String instructions = "LLR"; // Replace this with your actual input
        String startNode = "AAA";
        String destinationNode = "ZZZ";

        int steps = navigateToDestination(startNode, destinationNode, instructions);
        System.out.println("Steps required to reach " + destinationNode + ": " + steps);
    }

    private static void initializeNetwork() {
        network.put("AAA", new String[]{"BBB", "CCC"});
        network.put("BBB", new String[]{"AAA", "ZZZ"});
        network.put("CCC", new String[]{"ZZZ", "GGG"});
        network.put("DDD", new String[]{"DDD", "DDD"});
        network.put("EEE", new String[]{"EEE", "EEE"});
        network.put("GGG", new String[]{"GGG", "GGG"});
        network.put("ZZZ", new String[]{"ZZZ", "ZZZ"});
    }

    private static int navigateToDestination(String start, String destination, String instructions) {
        int steps = 0;
        String current = start;

        for (char instruction : instructions.toCharArray()) {
            if (instruction == 'L') {
                current = network.get(current)[0];
            } else if (instruction == 'R') {
                current = network.get(current)[1];
            }

            steps++;
            if (current.equals(destination)) {
                return steps;
            }
        }

        return steps; // If the destination is not reached in the initial set of instructions
    }
}
