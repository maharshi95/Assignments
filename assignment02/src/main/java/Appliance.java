public class Appliance {
    
    public static final long TIME_UNIT = 1000;
    private static int applianceCount = 0;
    
    private String name;
    private boolean autoSwitchOffMode;
    private boolean switchedOn;
    private int autoSwitchOffTime;
    private ApplianceType type;
    
    public Appliance(String name, ApplianceType type) {
        this.name = name + " AP" + ++applianceCount;
        this.type = type;
        autoSwitchOffMode = false;
        switchedOn = false;
        autoSwitchOffTime = 0;
    }
    
    public boolean canAutoSwitchOff() {
        return autoSwitchOffMode;
    }
    
    public int autoSwitchOffTime() {
        return autoSwitchOffTime;
    }
    
    public boolean isOn() {
        return switchedOn;
    }
    
    public String getName() {
        return name;
    }
    
    public ApplianceType getType() {
        return type;
    }
    
    public void setAutoSwitchOffTime(int time) {
        autoSwitchOffMode = false;
        
        if(time > 0){
            autoSwitchOffMode = true;
            autoSwitchOffTime = time;
        }   
    }
    
    public boolean turnOn() {
        boolean success = false;
        if(!switchedOn) {
            switchedOn = false;
            success = true;
        }
        return success;
    }
    
    public boolean turnOff() {
        boolean success = false;
        if(switchedOn) {
            switchedOn = false;
            success = true;
        }
        return success;
    }
    
    public boolean changeState(boolean state, int time) {
        if(state == true) {
            return turnOn();
        }
        else {
            return turnOff();
        }
    }
}
