import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * SmartHome is the container for all the Appliances and is responsible for issuing the commands to them.
 */
public class SmartHome {
    
    //list of appliances
    private ArrayList<Appliance> appliances;
    
    private Thread controlThread;
    
    //primary appliances
    private WaterHeater waterHeater;
    private AirConditioner airConditioner;
    private CookingOven cookingOven;
    
    //The Schdule of events to be carried out
    private ArrayList<Event> schedule;
    
    
    public SmartHome() {
        appliances= new ArrayList();
        
        waterHeater = new WaterHeater();
        airConditioner = new AirConditioner();
        cookingOven = new CookingOven();
        
        appliances.add(waterHeater);
        appliances.add(airConditioner);
        appliances.add(cookingOven);
        
        schedule = new ArrayList();
    }
    
    /**
     * This method reads the schedule decided for the SmartHome from a file and parses it as a List of events
     */
    public void readSchedule() throws BadScheduleException{
        BufferedReader br = null;
        try {
            String line;
            br = new BufferedReader(new FileReader("schedule.txt"));
            while ((line = br.readLine()) != null) {
				Event event = Event.parseEvent(line);
				if(event != null) {
				    schedule.add(event);
				    if(event.getAppliance().canAutoSwitchOff() && event.getState() == true) {
				        Event stopEvent = new Event(event.getAppliance(),event.getTime(), false);
				        schedule.add(event);
				    }
				}
				else {
				    throw new BadScheduleException("Bad Event Format: " + line);
				}
			}
        }
        catch(IOException e) {
            System.out.println("Schedule not found");
        }
        finally {
            try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
        }
    }
    
    public void processSchedule() {
        Collections.sort(schedule);
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                long delay = Appliance.TIME_UNIT*schedule.get(0).getTime();
                
                try {
                    Thread.sleep(delay);
                } catch(InterruptedException e) {}

                for(Event e : schedule) {
                    boolean success = e.getAppliance().changeState(e.getState(), e.getTime());
                    String state = (e.getState() ? "on" : "off");
                    if(success) {
                        String msg = e.getTime() + ": " + e.getAppliance().getName() + " turned " + state + " successfully.";
                        System.out.println(msg);
                    }
                    else {
                        String msg = e.getTime() + ": Cannot turn " + state + " " + e.getAppliance().getName() + ". It is already " + state + ".";
                        System.out.println(msg);
                    }
                }
            }
        };
        controlThread = new Thread(runnable);
    }
}

/**
 * An auxillary class used to manage event-Task given to SmartHome by the User
 */
class Event implements Comparable<Event> {
    
    private Appliance appliance;
    private boolean state;
    private int time;
    
    public Event(Appliance app, int time, boolean state) {
        this.appliance = appliance;
        this.state = state;
        this.time = time;
    }
    
    public static Event parseEvent(String str) {
        String[] words = str.split(" ");
        Event event = null;
        if(words.length == 3) {
            
        }
        return event;
    }
    
    public Appliance getAppliance() {
        return appliance;
    }
    
    public boolean getState() {
        return state;
    }
    
    public int getTime() {
        return time;
    }
    
    @Override
    // Compares the event on the basis of its time
    public int compareTo(Event e) {
        if(time < e.time)
            return -1;
        if(time > e.time)
            return 1;
        return appliance.getName().compareTo(e.appliance.getName());
    }
}