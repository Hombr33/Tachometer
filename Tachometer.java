import javax.swing.*;
import java.awt.geom.Line2D;
import java.util.concurrent.TimeUnit;

public class Tachometer {
    private Painter p = null;
    private JPanel j = null;
    private Clutch c = null;
    private JLabel jl = null;
    private float RPM;

    public Tachometer(JPanel j, Painter p, Clutch c) {
        this.j = j;
        this.jl = (JLabel)j.getComponent(0);
        this.p = p;
        this.c = c;
        this.p.setBounds(0, 0, 1000, 1000);
        new Thread()
        {
            public void run() {
                while (true) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    updateRPM();
                    updateGear();
                }
            }
        }.start();
    }

    //updates the RPM values on the indicator
    public void updateRPM() {
        this.j.remove(this.p);
        this.RPM = this.c.getCrankshaft().getRotations();
        this.p.l = new Line2D.Double((-500+450*Math.cos((RPM/50)*Math.PI/180))*(-1), (-500+450*Math.sin((RPM/50)*Math.PI/180))*(-1), 500, 500);
        this.j.add(this.p);
        this.j.revalidate();
        this.j.repaint();
    }

    //updates label of current gear
    public void updateGear() {
        this.jl.setText(String.valueOf(this.c.getGear().getNum()));
    }
}
