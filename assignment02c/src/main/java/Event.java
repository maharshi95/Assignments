/**
 * An auxillary class used to manage event-Task given to SmartHome by the User
 */
class Event implements Comparable<Event> {
    
    private Appliance appliance;
    private boolean state;
    private int time;
    
    public Event(Appliance app, int time, boolean state) {
        this.appliance = app;
        this.state = state;
        this.time = time;
    }
    
    public static Event parseEvent(String str,SmartHome home) {
        String[] words = str.split(" ");
        Event event = null;
        if(words.length == 3) {
            boolean error = false;
            Appliance app = null;
            boolean state = false;
            int time = 0;
            
            if(words[0].equals("WATER_HEATER"))
                app = home.getWaterHeater();
            else if(words[0].equals("AIR_CONDITIONER"))
                app = home.getAirConditioner();
            else if(words[0].equals("COOKING_OVEN"))
                app = home.getCookingOven();
            else
                error = true;
            
            if(words[1].equals("ON"))
                state = true;
            else if(words[1].equals("OFF"))
                state = false;
            else
                error = true;
                
            try {
                time = Integer.parseInt(words[2]);
            } catch(Exception e) {
                error = true;
            }
            
            if(!error) {
                event = new Event(app, time, state);
            }
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