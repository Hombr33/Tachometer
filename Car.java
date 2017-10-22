import javax.swing.*;

public class Car {
    public Gear neutral_gear;
    public CrankShaft crankShaft;
    public Clutch clutch;
    public GearBox gearBox;
    public Tachometer tachometer;


    public Car(JPanel j) {
        neutral_gear = new Gear(0, 0);
        crankShaft = new CrankShaft();
        clutch = new Clutch(crankShaft, neutral_gear);
        gearBox = new GearBox(neutral_gear, clutch);
        tachometer = new Tachometer(j, new Painter(), clutch);
    }
}
