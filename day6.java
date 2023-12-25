public class WaysToBeatRecord {

    public static int waysToBeatRecord(int[] times, int[] distances) {
        int totalWays = 1;

        for (int i = 0; i < times.length; i++) {
            int time = times[i];
            int recordDistance = distances[i];
            int ways = 0;

            for (int holdTime = 0; holdTime <= time; holdTime++) {
                int remainingTime = time - holdTime;
                int totalDistance = (holdTime * (holdTime + 1)) / 2 + remainingTime;

                if (totalDistance > recordDistance) {
                    ways++;
                }
            }

            totalWays *= ways;
        }

        return totalWays;
    }

    public static void main(String[] args) {
        
        int[] times = {7, 15, 30};
        int[] distances = {9, 40, 200};

        int result = waysToBeatRecord(times, distances);

        System.out.println("Result: " + result);
    }
}
