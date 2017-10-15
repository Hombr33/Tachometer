import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tester {

    public static void main(String[] args) {


        try {
            GlobalScreen.registerNativeHook();
            Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
            logger.setLevel(Level.WARNING);
            logger.setUseParentHandlers(false);
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }

        Speedometer s = new Speedometer();


        GlobalScreen.addNativeKeyListener(s);


        Decreaser decreaser = new Decreaser(s);
        decreaser.run();
    }

}
