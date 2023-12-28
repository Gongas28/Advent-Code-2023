import java.util.ArrayList;
import java.util.List;

class Hailstone {
    int x, y, z, vx, vy, vz;

    public Hailstone(int x, int y, int z, int vx, int vy, int vz) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.vx = vx;
        this.vy = vy;
        this.vz = vz;
    }

    public void move() {
        x += vx;
        y += vy;
        z += vz;
    }
}

public class HailstoneCollision {
    public static void main(String[] args) {
        List<Hailstone> hailstones = new ArrayList<>();
        hailstones.add(new Hailstone(19, 13, 30, -2, 1, -2));
        hailstones.add(new Hailstone(18, 19, 22, -1, -1, -2));
        hailstones.add(new Hailstone(20, 25, 34, -2, -2, -4));
        hailstones.add(new Hailstone(12, 31, 28, -1, -2, -1));
        hailstones.add(new Hailstone(20, 19, 15, 1, -5, -3));

        int minX = 7;
        int maxX = 27;
        int minY = 7;
        int maxY = 27;

        int timeSteps = 1000;

        int intersections = 0;

        for (int t = 0; t < timeSteps; t++) {
            for (int i = 0; i < hailstones.size() - 1; i++) {
                for (int j = i + 1; j < hailstones.size(); j++) {
                    Hailstone hailstoneA = hailstones.get(i);
                    Hailstone hailstoneB = hailstones.get(j);

                    if (willIntersect(hailstoneA, hailstoneB, minX, maxX, minY, maxY)) {
                        intersections++;
                    }
                }
            }

            for (Hailstone hailstone : hailstones) {
                hailstone.move();
            }
        }

        System.out.println("Number of intersections: " + intersections);
    }

    private static boolean willIntersect(Hailstone a, Hailstone b, int minX, int maxX, int minY, int maxY) {
        int ax = a.x;
        int ay = a.y;
        int bx = b.x;
        int by = b.y;

        return ax >= minX && ax <= maxX && ay >= minY && ay <= maxY &&
               bx >= minX && bx <= maxX && by >= minY && by <= maxY;
    }
}
