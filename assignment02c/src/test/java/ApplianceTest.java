import org.junit.Test;
import static org.junit.Assert.*;

public class ApplianceTest {
    
    @Test
    public void ConstructionTest1() {
        int count = Appliance.getApplianceCount();
        Appliance a = new Appliance("AC", ApplianceType.AIR_CONDITIONER);
        assertEquals(a.getName(), "AC AP" + (count+1));
        assertEquals(a.getType(), ApplianceType.AIR_CONDITIONER);
        assertEquals(a.isOn(), false);
    }
    
    @Test
    public void ConstructionTest2() {
        int count = Appliance.getApplianceCount();
        Appliance a = new Appliance("oven", ApplianceType.COOKING_OVEN);
        assertEquals(a.getName(), "oven AP" + (count+1));
        assertEquals(a.getType(), ApplianceType.COOKING_OVEN);
    }
    
    @Test
    public void AutoSwitchOffTest() {
        int count = Appliance.getApplianceCount();
        Appliance a = new Appliance("oven", ApplianceType.COOKING_OVEN);
        assertEquals(a.getName(), "oven AP" + (count+1));
        assertEquals(a.getType(), ApplianceType.COOKING_OVEN);
        a.setAutoSwitchOffTime(3);
        assertEquals(a.autoSwitchOffTime(), 3);
        assertEquals(a.canAutoSwitchOff(),true);
    }
    
    @Test
    public void WaterHeaterTest() {
        int count = Appliance.getApplianceCount();
        Appliance a = new WaterHeater();
        assertEquals(a.getName(), "WATER HEATER AP" + (count+1));
        assertEquals(a.getType(), ApplianceType.WATER_HEATER);
        assertEquals(a.autoSwitchOffTime(),WaterHeater.DEFAULT_AUTO_SWITCHOFF_TIME);
        assertEquals(a.canAutoSwitchOff(),true);
    }
    
    @Test
    public void ApplianceTurnOnTest() {
        Appliance a = new Appliance("oven", ApplianceType.COOKING_OVEN);
        boolean success = a.turnOff();
        assertEquals(success, false);
        success = a.turnOn();
        assertEquals(success, true);
    }
    
    @Test
    public void ApplianceTurnOffTest() {
        Appliance a = new Appliance("oven", ApplianceType.COOKING_OVEN);
        boolean success = a.turnOn();
        assertEquals(success, true);
        success = a.turnOn();
        assertEquals(success, false);
        success = a.turnOff();
        assertEquals(success, true);
    }
    
    @Test
    public void ApplianceStateChangeTest() {
        Appliance a = new Appliance("oven", ApplianceType.COOKING_OVEN);
        boolean success = a.changeState(false);
        assertEquals(success, false);
        success = a.changeState(true);
        assertEquals(success, true);
    }
    
    @Test
    public void ApplianceTypeTest() {
        ApplianceType type = ApplianceType.valueOf("AIR_CONDITIONER");
        assertNotEquals(type,null);
        ApplianceType[] types = ApplianceType.values();
        assertEquals(types.length > 0,true);
    }
    
}
