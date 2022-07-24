import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Window extends JPanel {
    public static boolean WIND = true;
    public static double WIND_STRENGTH;
    public static int SELECTED_BEAM = 0;

    private JFrame frame;

    private Simulation simulation;

    public static List<Point> points;
    public static List<Beam> beams;

    private void init() {
        points = new ArrayList<>();
        beams = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Point point = new Point(i * 30 + 300, 10 + i * 30);

            if (i == 0 || i == 19 || i == 10) {
                point.isLocked = true;
            }

            points.add(point);
        }

        for (int i = 0; i < 19; i++) {
//            if(i != 5) {
                Beam beam = new Beam(points.get(i), points.get(i + 1), 2);

                beam.calculateLength();

                beams.add(beam);
//            }
        }

        simulation = new Simulation();

        simulation.points = points;
        simulation.beams = beams;
    }

    public Window() {
        init();

        frame = new JFrame();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.add(this);

        frame.setSize(1920, 1080);
        frame.setTitle("Rope Simulation");

        frame.setVisible(true);

        frame.addKeyListener(new Keyboard());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (Point point : points) {
            g.setColor(Color.RED);

            g.fillRect(point.x - 2, point.y - 2, 5, 5);

            g.drawLine(point.x, point.y, point.px, point.py);
        }

        for (int i=0; i<beams.size(); i++) {
            Beam beam = beams.get(i);

            if(i != SELECTED_BEAM) {
                g.setColor(Color.BLACK);
            }else{
                g.setColor(Color.GREEN);
            }

            g.drawLine(beam.a.x, beam.a.y, beam.b.x, beam.b.y);
        }

        try {
            TimeUnit.MILLISECONDS.sleep(16);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (WIND) {
            simulation.simulate(1, 9.8, WIND_STRENGTH);
        } else {
            simulation.simulate(1, 9.8, 0);
        }

        g.drawString(String.format("Velocity: %.2f", WIND_STRENGTH * 3.6), 50, 50);

        repaint();
    }
}