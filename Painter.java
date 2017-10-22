import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class Painter extends JPanel {

    Graphics2D g2 = null;
    Line2D l = new Line2D.Double(200, 500, 300, 500);

    public Painter() { }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(6));
        g2.draw(l);
    }
}