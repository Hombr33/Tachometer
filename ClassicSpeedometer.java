

public class ClassicSpeedometer {

    private volatile float speed = 0;
    private volatile boolean wPressed = false;
    private volatile boolean isChange = true;

    public synchronized void increaseSpeed(float amount) {
        if (this.speed < 160) {
            this.isChange = true;
            this.speed += amount;
        } else {
            this.isChange = false;
        }
    }

    public synchronized void decreaseSpeed(float amount) {
        if (!this.wPressed && this.speed > 20) {
            this.isChange = true;
            this.speed -= amount;
        }
        else if (!this.wPressed && this.speed > 10) {
            this.isChange = true;
            this.speed -= 0.1f;
        } else {
            this.isChange = false;
        }
    }

    public boolean isChange() {
        return isChange;
    }


    public synchronized float getSpeed(){
        return this.speed;
    }

    public synchronized void setwPressed(boolean b) {
        this.wPressed = b;
    }

    public synchronized boolean getwPressed() {
        return this.wPressed;
    }
}
