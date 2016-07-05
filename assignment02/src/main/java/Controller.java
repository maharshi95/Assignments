import java.io.BufferedWriter;
import java.io.PrintWriter;


public class Controller {
    public static void main(String[] args) {
        SmartHome home = new SmartHome();
        try {
            home.readSchedule();
            home.processSchedule();
        }
        catch(Exception e) {
            System.out.println("Cannot parse schedule file\n" + e.getMessage());
        }
    }
}
