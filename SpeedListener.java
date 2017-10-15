import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SpeedListener implements KeyListener {

    private ClassicSpeedometer s = null;
    private ClassicDecreaser d = null;
    private ClassicIncreaser i = null;
    private int afterMSeconds = 0;

    public SpeedListener(ClassicSpeedometer s) {
        this.s = s;
        this.d = new ClassicDecreaser(s);
        this.i = new ClassicIncreaser(s);
        new Thread()
        {
            public void run() {
                d.run();
            }
        }.start();

        new Thread()
        {
            public void run() {
                i.run();
            }
        }.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        s.setwPressed(true);
        this.afterMSeconds = i.getAfterMSeconds();
        i.increaseSpeed(7f); //lower amount as you climb gears
    }

    @Override
    public void keyReleased(KeyEvent e) {
        s.setwPressed(false);
        i.setAfterMSeconds(0);
    }
}
