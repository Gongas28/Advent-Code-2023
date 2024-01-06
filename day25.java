import java.util.*;

public class SnowIsland {

    public static void main(String[] args) {
        List<String> wiringDiagram = Arrays.asList(
                "jqt: rhn xhk nvd",
                "rsh: frs pzl lsr",
                "xhk: hfx",
                "cmg: qnr nvd lhk bvb",
                "rhn: xhk bvb hfx",
                "bvb: xhk hfx",
                "pzl: lsr hfx nvd",
                "qnr: nvd",
                "ntq: jqt hfx bvb xhk",
                "nvd: lhk",
                "lsr: lhk",
                "rzs: qnr cmg lsr rsh",
                "frs: qnr lhk lsr"
        );

        Map<String, List<String>> graph = buildGraph(wiringDiagram);

        List<List<String>> disconnectedGroups = findDisconnectedGroups(graph);

        int product = disconnectedGroups.get(0).size() * disconnectedGroups.get(1).size();

        System.out.println("Product of sizes of disconnected groups: " + product);
    }
private static Map<String, List<String>> buildGraph(List<String> wiringDiagram) {
        Map<String, List<String>> graph = new HashMap<>();

        for (String connection : wiringDiagram) {
            String[] components = connection.split(":");
            String source = components[0].trim();
            String[] targets = components[1].trim().split("\\s+");

            graph.putIfAbsent(source, new ArrayList<>());
            graph.get(source).addAll(Arrays.asList(targets));

            for (String target : targets) {
                graph.putIfAbsent(target, new ArrayList<>());
                graph.get(target).add(source);
            }
        }

        return graph;
    }

    private static List<List<String>> findDisconnectedGroups(Map<String, List<String>> graph) {
        Set<String> visited = new HashSet<>();
        List<List<String>> disconnectedGroups = new ArrayList<>();

        for (String component : graph.keySet()) {
            if (!visited.contains(component)) {
                List<String> group = new ArrayList<>();
                dfs(graph, component, visited, group);
                disconnectedGroups.add(group);
            }
        }

        return disconnectedGroups;
    }

    private static void dfs(Map<String, List<String>> graph, String component, Set<String> visited, List<String> group) {
        visited.add(component);
        group.add(component);

        for (String neighbor : graph.get(component)) {
            if (!visited.contains(neighbor)) {
                dfs(graph, neighbor, visited, group);
            }
        }
    }
}
