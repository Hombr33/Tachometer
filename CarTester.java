import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;

public class CarTester implements KeyListener{

    private static Car car1;
    private static void Procedure() {
        JFrame frame = new JFrame();
        frame.setLayout(null);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        frame.setContentPane(panel);
        JLabel gear_label = new JLabel();
        JLabel RPM1000 = new JLabel();
        RPM1000.setText("1000");
        RPM1000.setBounds(84, 277, 100, 100);
        panel.add(RPM1000);
        JLabel RPM2000 = new JLabel();
        RPM2000.setText("2000");
        RPM2000.setBounds(180, 133, 100, 100);
        panel.add(RPM2000);
        JLabel RPM3000 = new JLabel();
        RPM3000.setText("3000");
        RPM3000.setBounds(325, 35, 100, 100);
        panel.add(RPM3000);
        JLabel RPM4000 = new JLabel();
        RPM4000.setText("4000");
        RPM4000.setBounds(497, 0, 100, 100);
        panel.add(RPM4000);
        JLabel RPM5000 = new JLabel();
        RPM5000.setText("5000");
        RPM5000.setBounds(668, 32, 100, 100);
        panel.add(RPM5000);
        JLabel RPM6000 = new JLabel();
        RPM6000.setText("6000");
        RPM6000.setBounds(815, 128, 100, 100);
        panel.add(RPM6000);
        JLabel RPM7000 = new JLabel();
        RPM7000.setText("7000");
        RPM7000.setBounds(913, 271, 100, 100);
        panel.add(RPM7000);
        JLabel RPM8000 = new JLabel();
        RPM8000.setText("8000");
        RPM8000.setBounds(949, 420, 100, 100);
        panel.add(RPM8000);
        gear_label.setBounds(500, 500, 50, 50);
        gear_label.setText("0");
        panel.add(gear_label);
        panel.setComponentZOrder(gear_label, 0);
        panel.setComponentZOrder(RPM1000, 1);
        panel.setComponentZOrder(RPM2000, 2);
        panel.setComponentZOrder(RPM3000, 3);
        panel.setComponentZOrder(RPM4000, 4);
        panel.setComponentZOrder(RPM5000, 5);
        panel.setComponentZOrder(RPM6000, 6);
        panel.setComponentZOrder(RPM7000, 7);
        panel.setComponentZOrder(RPM8000, 8);
        frame.setSize(1000, 1000);
        frame.addKeyListener(new CarTester());
        frame.setVisible(true);
        car1 = new Car(panel);
        //Uncomment for Debugging--------------
//        try {
//            while (true) {
//                TimeUnit.MILLISECONDS.sleep(1);
//                System.out.println(car1.clutch.getGear().getRotations() + " / " + car1.crankShaft.getRotations());
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //---------------------
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == 81) {
            car1.clutch.Disengage();
        } else if (e.getKeyCode() == 87) {
            car1.clutch.GiveGas();
        } else if (e.getKeyCode() == 80) {
            car1.gearBox.ShiftUP();
        } else if (e.getKeyCode() == 76) {
            car1.gearBox.ShiftDOWN();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 81) {

            car1.clutch.Engage();
        } else if (e.getKeyCode() == 87) {
            car1.clutch.ReleaseGas();
        }
    }

    public static void main(String[] args){
        Procedure(); //Sets out the GUI and creates the Car Object

    }

}
