
public class GearBox {

    private Spinner gear = null; //Current gear
    private Clutch c = null; //gets a reference to the controller
    private boolean canShiftUP = true;
    private boolean canShiftDOWN = false;
    private boolean isNeutral = false;

    //Util functions
    private void detectNeutral() {
        if (this.gear instanceof Gear) {
            if (((Gear) this.gear).getNum() == 0) {
                this.isNeutral = true;
            }
        }
    }

    public boolean IsNeutral() {
        return this.isNeutral;
    }
    //-------------
    public GearBox(Spinner s, Clutch c) {
        this.gear = s;
        this.c = c;
        this.detectNeutral();
    }

    public void ShiftUP() {
        if (!c.IsEngaged()) {
            Gear g = (Gear)this.gear;
            int num = g.getNum();
            if (canShiftUP) {
                this.canShiftDOWN = true;
                this.gear = new Gear(num + 1, c.getLast_RPM());
                c.setGear((Gear)this.gear);
                this.canShiftDOWN = true;
                if (((Gear) this.gear).getNum() == 6) {
                    this.canShiftUP = false;
                }
            }
        }
        else {
            System.out.println("Cannot change gears if you do not disengage the engine");
        }
    }

    public void ShiftDOWN() {
        if (!c.IsEngaged()) {
            this.canShiftUP = true;
            Gear g = (Gear)this.gear;
            int num = g.getNum();
            if (((Gear) this.gear).getNum() == 1) {
                this.gear = new Gear(0, 0);
                c.setGear((Gear)this.gear);
            }
            if (canShiftDOWN) {
                this.gear = new Gear(num - 1, c.getLast_RPM());
                c.setGear((Gear)this.gear);
                this.canShiftDOWN = true;
                if (((Gear) this.gear).getNum() == 1) {
                    this.canShiftDOWN = false;
                }
            }
        }
        else {
            System.out.println("Cannot change gears if you do not disengage the engine");
        }
    }

}
