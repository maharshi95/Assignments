public class WaterHeater extends Appliance{
    
    public static final int DEFAULT_AUTO_SWITCHOFF_TIME = 2;
    
    public WaterHeater() {
        super("WaterHeater", ApplianceType.WATER_HEATER);
        setAutoSwitchOffTime(DEFAULT_AUTO_SWITCHOFF_TIME);
    }   
}
