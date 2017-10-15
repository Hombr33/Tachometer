
public class ClassicDecreaser extends Thread {
    private ClassicSpeedometer s = null;
    private float angle_speed = 0f;

    public ClassicDecreaser(ClassicSpeedometer s) {
        this.s = s;
    }

    public void run() {
        while (true) {
            while (!s.getwPressed()) {
                angle_speed = s.getSpeed();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e){
                }
                s.decreaseSpeed(2f);
            }
        }
    }
}
