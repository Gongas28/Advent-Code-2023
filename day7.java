import java.util.*;

public class PokerGame {
    
    public static void main(String[] args) {
        List<String[]> handsAndBids = new ArrayList<>();
        handsAndBids.add(new String[]{"32T3K", "765"});
        handsAndBids.add(new String[]{"T55J5", "684"});
        handsAndBids.add(new String[]{"KK677", "28"});
        handsAndBids.add(new String[]{"KTJJT", "220"});
        handsAndBids.add(new String[]{"QQQJA", "483"});

        int totalWinnings = calculateWinnings(handsAndBids);
        System.out.println("Total Winnings: " + totalWinnings);

        for (int i = 0; i < handsAndBids.size(); i++) {
            String[] handAndBid = handsAndBids.get(i);
            String hand = handAndBid[0];
            int bid = Integer.parseInt(handAndBid[1]);
            int rank = getRank(hand);
            System.out.println("Hand " + hand + " has a rank of " + rank);
        }
    }

    public static int getRank(String hand) {
        String labels = "AKQJT98765432A";
        String[] handTypeOrder = {"Five of a kind", "Four of a kind", "Full house", "Three of a kind", "Two pair", "One pair", "High card"};
        
        String handType = null;
        char[] sortedHand = hand.chars().mapToObj(e -> (char)e).sorted((a, b) -> labels.indexOf(b) - labels.indexOf(a))
                .map(String::valueOf).collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString().toCharArray();
        Map<Character, Integer> counts = new HashMap<>();
        for (char label : sortedHand) {
            counts.put(label, counts.getOrDefault(label, 0) + 1);
        }

        if (counts.containsValue(5)) {
            handType = "Five of a kind";
        } else if (counts.containsValue(4)) {
            handType = "Four of a kind";
        } else if (Arrays.equals(counts.values().stream().sorted().toArray(), new Integer[]{2, 3})) {
            handType = "Full house";
        } else if (counts.containsValue(3)) {
            handType = "Three of a kind";
        } else if (Arrays.equals(counts.values().stream().sorted().toArray(), new Integer[]{1, 2, 2})) {
            handType = "Two pair";
        } else if (counts.containsValue(2)) {
            handType = "One pair";
        } else {
            handType = "High card";
        }

        return Arrays.asList(handTypeOrder).indexOf(handType) + 1;
    }

    public static int calculateWinnings(List<String[]> handsAndBids) {
        int totalWinnings = 0;
        handsAndBids.sort(Comparator.comparingInt(a -> getRank(a[0])).reversed());

        for (int i = 0; i < handsAndBids.size(); i++) {
            String[] handAndBid = handsAndBids.get(i);
            int bid = Integer.parseInt(handAndBid[1]);
            totalWinnings += bid * (i + 1);
        }

        return totalWinnings;
    }
}
