import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;


public class Speedometer implements NativeKeyListener {

    private int speed = 0;
    public volatile boolean wPressed = false;

    public void increaseSpeed(int amount) {
        this.speed += amount;
    }

    public void decreaseSpeed(int amount) {
        if (!this.wPressed && this.speed > 20) {
            this.speed -= amount;
        }
        else if (!this.wPressed && this.speed > 10) {
            this.speed -= 1;
        }
    }

    public void nativeKeyPressed(NativeKeyEvent e) {
        this.wPressed = true;
        increaseSpeed(1);
        System.out.println(speed);

        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                GlobalScreen.unregisterNativeHook();
            }
            catch (NativeHookException ex) { }
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        this.wPressed = false;
    }

    public void nativeKeyTyped(NativeKeyEvent e) {}


}