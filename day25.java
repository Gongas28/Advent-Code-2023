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
