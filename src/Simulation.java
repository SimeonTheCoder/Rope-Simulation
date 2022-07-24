import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulation {
    public List<Point> points;
    public List<Beam> beams;

    public Simulation() {
        points = new ArrayList<>();
        beams = new ArrayList<>();
    }

    public void simulate(int iterations, double gravity, double wind) {
        Random random = new Random();

        for (int i = 0; i < iterations; i++) {
            for (Point point : points) {
                if (!point.isLocked) {
                    int px = point.x;
                    int py = point.y;

                    double dx = point.x - point.px;
                    double dy = point.y - point.py;

                    point.x += dx + wind + random.nextInt((int) (wind / 2.) + 1);
                    point.y += dy + gravity;

                    point.px = px;
                    point.py = py;
                }
            }

            List<Integer> iterated = new ArrayList<>();

            for (int j = 0; j < beams.size(); j++) {
                Beam beam = beams.get(j);

                int cx = (beam.a.x + beam.b.x) / 2;
                int cy = (beam.a.y + beam.b.y) / 2;

                double dx = beam.a.x - beam.b.x;
                double dy = beam.a.y - beam.b.y;

                double l = Math.sqrt(dx * dx + dy * dy);

                dx /= l;
                dy /= l;

                if (!beam.a.isLocked) {
                    beam.a.x = (int) (cx + dx * beam.length / 2);
                    beam.a.y = (int) (cy + dy * beam.length / 2);
                }

                if (!beam.b.isLocked) {
                    beam.b.x = (int) (cx - dx * beam.length / 2);
                    beam.b.y = (int) (cy - dy * beam.length / 2);
                }

                if (random.nextInt((int) (50000 - wind * 1000)) < 8 && wind != 0) {
                    beams.remove(beam);
                }
            }
        }
    }
}
