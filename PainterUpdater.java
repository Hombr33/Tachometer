import javax.swing.*;
import java.awt.geom.Line2D;

public class PainterUpdater extends Thread {

    private Painter p = null;
    private ClassicSpeedometer s = null;
    private JFrame f = null;
    private float speed_angle;

    public PainterUpdater(ClassicSpeedometer s, Painter p, JFrame f) {
        this.s = s;
        this.p = p;
        this.f = f;
    }

    public void run() {
        while (true) {
            while (s.isChange()) {
                this.f.remove(this.p);
                speed_angle = this.s.getSpeed();
                this.p.l = new Line2D.Double((-500+450*Math.cos(speed_angle*Math.PI/180))*(-1), (-500+450*Math.sin(speed_angle*Math.PI/180))*(-1), 500, 500);
                this.f.add(this.p);
                this.f.revalidate();
                this.f.repaint();
            }
        }
    }


}
