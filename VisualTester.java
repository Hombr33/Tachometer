import javax.swing.*;
import java.awt.*;

public class VisualTester {

    public static void main(String[] args) {
        ClassicSpeedometer s = new ClassicSpeedometer();
        JFrame frame = new JFrame("Speedometer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);
        frame.setVisible(true);
        Painter p = new Painter();
        frame.add(p);
        PainterUpdater pu = new PainterUpdater(s, p, frame);
        frame.addKeyListener(new SpeedListener(s));
        pu.run();


    }
}
