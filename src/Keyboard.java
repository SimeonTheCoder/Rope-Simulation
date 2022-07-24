import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case '[' -> Window.WIND_STRENGTH -= 1;
            case ']' -> Window.WIND_STRENGTH += 1;
            case 't' -> Window.WIND = !Window.WIND;
            case 'o' -> Window.SELECTED_BEAM --;
            case 'p' -> Window.SELECTED_BEAM ++;
            case 'b' -> Window.beams.remove(Window.SELECTED_BEAM);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
