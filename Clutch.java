import java.util.concurrent.TimeUnit;

public class Clutch {

    private Spinner crankshaft;
    private Spinner gear;
    private volatile boolean isEngaged = true;
    private volatile boolean isAccelerating = false;
    private volatile float last_RPM;
    private int last_gear;
    private Thread actionListener;

    //Util functions--------------------
    public float getLast_RPM() {
        return last_RPM;
    }
    public void setLast_RPM(float f) {
        this.last_RPM = f;
    }
    //----------------------------------


    public Clutch(Spinner crankshaft, Spinner gear) {
        this.crankshaft = crankshaft;
        this.gear = gear;
        actionListener = new Thread() {
            @Override
            public void run() {
                float multiplier = 2.3f; //time acceleration multiplier
                // (it exists only for speeding the process up, not increasing the RPM)
                while (true) {
                    //Case 1 -> Accelerating and Engaged
                    if (isEngaged && isAccelerating) {
                        try {
                            TimeUnit.MILLISECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Gear g = getGear();
                        switch (g.getNum()) {
                            case 1:
                                crankshaft.increaseRPM(0f, multiplier*1f);
                                if (!((CrankShaft)crankshaft).isMaxRPM()) {
                                    g.increaseRPM(0f, multiplier*0.336f);
                                }
                                break;
                            case 2:
                                crankshaft.increaseRPM(0f, multiplier*1f);
                                if (!((CrankShaft)crankshaft).isMaxRPM()) {
                                    g.increaseRPM(0f, multiplier*0.483f);
                                }
                                break;
                            case 3:
                                crankshaft.increaseRPM(0f, multiplier*1f);
                                if (!((CrankShaft)crankshaft).isMaxRPM()) {
                                    g.increaseRPM(0f, multiplier*0.699f);
                                }
                                break;
                            case 4:
                                crankshaft.increaseRPM(0f, multiplier*1f);
                                if (!((CrankShaft)crankshaft).isMaxRPM()) {
                                    g.increaseRPM(0f, multiplier*1f);
                                }
                                break;
                            case 5:
                                crankshaft.increaseRPM(0f, multiplier*1f);
                                if (!((CrankShaft)crankshaft).isMaxRPM()) {
                                    g.increaseRPM(0f, multiplier*1.190f);
                                }
                                break;
                            case 6:
                                crankshaft.increaseRPM(0f, multiplier*1f);
                                if (!((CrankShaft)crankshaft).isMaxRPM()) {
                                    g.increaseRPM(0f, multiplier*1.785f);
                                }
                                break;
                        }
                    //Case 2 -> Not accelerating and engaged
                    } else if (isEngaged && !isAccelerating) {
                        try {
                            TimeUnit.MILLISECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Gear g = getGear();
                        switch (g.getNum()) {
                            case 1:
                                crankshaft.decreaseRPM(0f, multiplier*0.25f);
                                g.decreaseRPM(0f, multiplier*0.084f);
                                break;
                            case 2:
                                crankshaft.decreaseRPM(0f, multiplier*0.25f);
                                g.decreaseRPM(0f, multiplier*0.1205f);
                                break;
                            case 3:
                                crankshaft.decreaseRPM(0f, multiplier*0.25f);
                                g.decreaseRPM(0f, multiplier*0.1725f);
                                break;
                            case 4:
                                crankshaft.decreaseRPM(0f, multiplier*0.25f);
                                g.decreaseRPM(0f, multiplier*0.25f);
                                break;
                            case 5:
                                crankshaft.decreaseRPM(0f, multiplier*0.25f);
                                g.decreaseRPM(0f, multiplier*0.2975f);
                                break;
                            case 6:
                                crankshaft.decreaseRPM(0f, multiplier*0.25f);
                                g.decreaseRPM(0f, multiplier*0.446f);
                                break;
                        }

                    //Case 3 -> Accelerating and not engaged
                    } else if (!isEngaged && isAccelerating) {
                        try {
                            TimeUnit.MILLISECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Gear g = getGear();
                        switch (g.getNum()) {
                            case 1:
                                g.decreaseRPM(0f, multiplier*0.168f);
                                break;
                            case 2:
                                g.decreaseRPM(0f, multiplier*0.241f);
                                break;
                            case 3:
                                g.decreaseRPM(0f, multiplier*0.345f);
                                break;
                            case 4:
                                g.decreaseRPM(0f, multiplier*0.5f);
                                break;
                            case 5:
                                g.decreaseRPM(0f, multiplier*0.595f);
                                break;
                            case 6:
                                g.decreaseRPM(0f, multiplier*0.892f);
                                break;
                        }
                        crankshaft.increaseRPM(0f, 15f);

                    //Case 4 -> not accelerating and not engaged
                    } else if (!isEngaged && !isAccelerating) {
                        try {
                            TimeUnit.MILLISECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        crankshaft.decreaseRPM(0f, 10f);
                        Gear g = getGear();
                        switch (g.getNum()) {
                            case 1:
                                g.decreaseRPM(0f, multiplier*0.1f);
                                break;
                            case 2:
                                g.decreaseRPM(0f, multiplier*0.2f);
                                break;
                            case 3:
                                g.decreaseRPM(0f, multiplier*0.3f);
                                break;
                            case 4:
                                g.decreaseRPM(0f, multiplier*0.4f);
                                break;
                            case 5:
                                g.decreaseRPM(0f, multiplier*0.5f);
                                break;
                            case 6:
                                g.decreaseRPM(0f, multiplier*0.6f);
                                break;
                        }
                    }
                }
            }
        };
        actionListener.start();
    }

    //Util functions
    public boolean IsEngaged() {
        return this.isEngaged;
    }
    public Gear getGear() {
        return (Gear)this.gear;
    }
    public CrankShaft getCrankshaft() { return (CrankShaft)this.crankshaft; }
    public void setGear(Gear g) {
        this.gear = g;
    }
    //-------------------


    public void GiveGas() {
        this.isAccelerating = true;
    }
    public void ReleaseGas() {
        this.isAccelerating = false;
    }
    public void Disengage() {
        this.last_RPM = ((Gear)this.gear).getRotations();
        this.last_gear = ((Gear)this.gear).getNum();
        this.isEngaged = false;
        ((Gear)this.gear).disengage_decreaseRPM(this);
    } //Disengage the clutch
    public void Engage() {
        this.isEngaged = true;
        ((CrankShaft)this.crankshaft).synchronizeWithGear(this.last_RPM, this.getGear().getNum(), this.last_gear);
    } //Engage the clutch
}
