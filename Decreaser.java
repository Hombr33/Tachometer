public class Decreaser extends Thread {

    private Speedometer s = null;

    public Decreaser(Speedometer s) {
        this.s = s;
    }

    public void run() {
        while (true) {
            s.decreaseSpeed(10);
        }
    }
}
