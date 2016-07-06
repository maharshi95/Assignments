import java.io.BufferedReader;
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
    private Runnable task;
    
    //primary appliances
    private WaterHeater waterHeater;
    private AirConditioner airConditioner;
    private CookingOven cookingOven;
    
    //The Schdule of events to be carried out
    private ArrayList<Event> schedule;
    
    
    public SmartHome() {
        appliances= new ArrayList<Appliance>();
        
        waterHeater = new WaterHeater();
        airConditioner = new AirConditioner();
        cookingOven = new CookingOven();
        
        appliances.add(waterHeater);
        appliances.add(airConditioner);
        appliances.add(cookingOven);
        
        schedule = new ArrayList<Event>();
    }
    
    /**
     * This method reads the schedule decided for the SmartHome from a file and parses it as a List of events
     */
    public void readSchedule(String filename) throws BadScheduleException, ScheduleNotFoundException {
        BufferedReader br = null;
        try {
            String line;
            br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null) {
				Event event = Event.parseEvent(line,this);
//				System.out.println(line + " " + event + " " + event.getAppliance() + " " + getWaterHeater());
				if(event != null) {
				    schedule.add(event);
				    if(event.getAppliance().canAutoSwitchOff() && event.getState() == true) {
				        Event stopEvent = new Event(event.getAppliance(),event.getTime()+event.getAppliance().autoSwitchOffTime(), false);
				        schedule.add(stopEvent);
				    }
				}
				else {
				    throw new BadScheduleException("Bad Event Format: " + line);
				}
			}
        }
        catch(IOException e) {
            throw new ScheduleNotFoundException("Schedule file \"" + filename + "\" not found!");
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
        task = new Runnable(){
            @Override
            public void run() {
                long delay = Appliance.TIME_UNIT*schedule.get(0).getTime();
                
                try {
                    Thread.sleep(delay);
                } catch(InterruptedException e) {}

                for(Event e : schedule) {
                    boolean success = e.getAppliance().changeState(e.getState());
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
        controlThread = new Thread(task);
        controlThread.start();
    }
    
    public WaterHeater getWaterHeater() {
        return waterHeater;
    }
    
    
    public AirConditioner getAirConditioner() {
        return airConditioner;
    }
    
    
    public CookingOven getCookingOven() {
        return cookingOven;
    }
    
    public ArrayList<Appliance> getAppliances() {
        return appliances;
    }
    
    public Runnable getTask() {
        return task;
    }
}