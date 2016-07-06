public class Controller {
    public static void main(String[] args) {
        SmartHome home = new SmartHome();
        String filename = "schedule.txt";
        try {
            home.readSchedule(filename);
            home.processSchedule();
        }
        catch(BadScheduleException e) {
            System.out.println("Cannot parse schedule file\n" + e.getMessage());
        }
        catch(ScheduleNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
