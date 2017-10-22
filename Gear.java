import java.util.concurrent.TimeUnit;

public class Gear implements Spinner {

    private volatile float rotations = 0f;
    private int num;


    //Utility functions
    public int getNum() {
        return this.num;
    }
    public float getRotations() {
        return this.rotations;
    }
    //-----------------------

    public Gear(int num, float rotations) {
        this.num = num;
        this.rotations = rotations;
    }

    public void increaseRPM(float r, float dr) {
        new Thread()
        {
            public void run() {
                rotations += dr;
            }
        }.start();
    }
    public void decreaseRPM(float r, float dr) {
        new Thread()
        {
            public void run() {
                if (rotations < 0f) {
                    return;
                }
                if (Math.abs(rotations - 0f) > 0.001f) {
                    rotations -= dr;
                }
            }
        }.start();
    }

    public void disengage_decreaseRPM(Clutch c) {
        new Thread()
        {
            public void run() {
                while (!c.IsEngaged()) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    c.setLast_RPM(c.getGear().rotations);
                }
            }
        }.start();

    }

}
