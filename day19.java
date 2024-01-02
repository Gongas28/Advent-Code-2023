import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WorkflowSimulator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, String[]> workflows = new HashMap<>();
        String line;
        while (!(line = scanner.nextLine()).isEmpty()) {
            String[] parts = line.split("\\{|\\}");
            workflows.put(parts[0], parts[1].split(","));
        }

        while (scanner.hasNextLine()) {
            String inputLine = scanner.nextLine();
            if (inputLine.isEmpty()) {
                continue;
            }

            String[] parts = inputLine.split("[{,=}]");
            Map<String, Integer> ratings = new HashMap<>();
            for (int i = 1; i < parts.length; i += 2) {
                ratings.put(parts[i], Integer.parseInt(parts[i + 1]));
            }

            int totalRating = simulateWorkflow(workflows, ratings, "in");
            System.out.println("Total Rating: " + totalRating);
        }
    }

    private static int simulateWorkflow(Map<String, String[]> workflows, Map<String, Integer> ratings, String workflow) {
        String[] rules = workflows.get(workflow);

        for (String rule : rules) {
            String[] conditions = rule.split(":");
            String[] checks = conditions[0].split("[<>]");
            String category = checks[0];
            int threshold = Integer.parseInt(checks[1]);
            char comparison = conditions[0].charAt(category.length());

            boolean conditionMet = false;
            switch (comparison) {
                case '>':
                    conditionMet = ratings.get(category) > threshold;
                    break;
                case '<':
                    conditionMet = ratings.get(category) < threshold;
                    break;
            }

            if (conditionMet) {
                if (conditions.length == 2) {
                    return simulateWorkflow(workflows, ratings, conditions[1]);
                } else {
                    if (conditions[1].equals("A")) {
                        return ratings.values().stream().mapToInt(Integer::intValue).sum();
                    } else {
                        return 0;
                    }
                }
            }
        }

        return simulateWorkflow(workflows, ratings, workflows.get(workflow)[workflows.get(workflow).length - 1]);
    }
}
