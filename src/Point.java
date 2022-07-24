public class Point {
    public int x, y;
    public int px, py;

    public boolean isLocked;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;

        this.px = this.x;
        this.py = this.y;
    }
}
