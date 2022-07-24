public class Beam {
    public Point a, b;
    public double length;
    public double strength;

    public Beam(Point a, Point b, double strength) {
        this.a = a;
        this.b = b;

        this.strength = strength;
    }

    public void calculateLength() {
        double dx = a.x - b.x;
        double dy = a.y - b.y;

        length = Math.sqrt(dx * dx + dy * dy);
    }
}
