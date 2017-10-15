public class ClassicIncreaser extends Thread {

    private ClassicSpeedometer s = null;
    private volatile int afterMSeconds = 0;


    public ClassicIncreaser(ClassicSpeedometer s) {
        this.s = s;
    }


    public void run() {
        while (true) {
            while (s.getwPressed()) {
                try {
                    Thread.sleep(1000);
                    this.afterMSeconds += 1;
                } catch (InterruptedException e) { }
            }
        }
    }

    public synchronized void increaseSpeed(float amount) {
        s.increaseSpeed(amount);
    }

    public synchronized void setAfterMSeconds(int s) {
        this.afterMSeconds = s;
    }

    public synchronized int getAfterMSeconds() {
        return afterMSeconds;
    }


}
