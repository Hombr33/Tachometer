import java.util.concurrent.TimeUnit;

public class CrankShaft implements Spinner {

    private volatile float rotations = 0f;
    private volatile boolean maxRPM = false;
    public float getRotations() {
        return this.rotations;
    }
    public boolean isMaxRPM() {
        return maxRPM;
    }

    //Synchronize Crankshaft With Gear to Rev-match
    public void synchronizeWithGear(float last_RPM, int next_gear, int current_gear) {
        new Thread()
        {
            public void run() {
                float newRPM = 0f;
                switch (next_gear){
                    case 1:
                        newRPM = last_RPM/0.336f;
                        break;
                    case 2:
                        newRPM = last_RPM/0.483f;
                        break;
                    case 3:
                        newRPM = last_RPM/0.699f;
                        break;
                    case 4:
                        newRPM = last_RPM/1f;
                        break;
                    case 5:
                        newRPM = last_RPM/1.190f;
                        break;
                    case 6:
                        newRPM = last_RPM/1.785f;
                        break;
                }
                if (newRPM > 8000f) {
                    System.out.println("Money Shifting....");
                    newRPM = 8000f;
                } else if (newRPM < 1000f && current_gear != 0 && next_gear != 0) {
                    System.out.println("Your engine has died");
                    newRPM = 10f;
                }
                if (rotations < newRPM) {
                    while (rotations < newRPM) {
                        if (rotations > newRPM) {
                            return;
                        }
                        try {
                            TimeUnit.MILLISECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        rotations += 20f;
                    }
                } else {
                    while (rotations > newRPM) {
                        if (rotations < newRPM) {
                            return;
                        }
                        try {
                            TimeUnit.MILLISECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        rotations -= 20f;
                    }
                }
            }
        }.start();
    }

    public void increaseRPM(float r, float dr) {
        new Thread()
        {
            public void run() {
                if (rotations > 7999f) {
                    maxRPM = true;
                    return;
                }
                if (Math.abs(rotations - 8000f) > 0.001f) {
                    rotations += dr;
                }
            }
        }.start();
    }

    public void decreaseRPM(float r, float dr) {
        new Thread()
        {
            public void run() {
                maxRPM = false;
                if (rotations < 10f) {
                    return;
                }
                if (Math.abs(rotations - 0f) > 0.001f) {
                    rotations -= dr;
                }
            }
        }.start();
    }


}
