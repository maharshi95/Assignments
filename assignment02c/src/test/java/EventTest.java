import org.junit.Test;
import static org.junit.Assert.*;

public class EventTest {
    @Test
    public void EventConstructionTest1() {
        Appliance ac = new AirConditioner();
        Event e = new Event(ac, 2, true);
        assertEquals(e.getAppliance(), ac);
        assertEquals(e.getState(), true);
        assertEquals(e.getTime(),2);
    }
    
    @Test
    public void EventConstructionTest2() {
        Appliance wh = new WaterHeater();
        Event e = new Event(wh, 3, false);
        assertEquals(e.getAppliance(), wh);
        assertEquals(e.getState(), false);
        assertEquals(e.getTime(),3);
    }
    
    @Test
    public void EventCompareTest() {
        Appliance ac = new AirConditioner();
        Event e1 = new Event(ac, 2, false);
        Event e2 = new Event(ac, 3, false);
        Event e3 = new Event(ac, 2, true);
        assertEquals(e1.compareTo(e2), -1);
        assertEquals(e2.compareTo(e1), 1);
        assertEquals(e1.compareTo(e3), 0);
    }
    
    @Test
    public void parseEventSuccessTest1() {
        SmartHome home = new SmartHome();
        String str1 = "WATER_HEATER ON 2";
        String str2 = "WATER_HEATER OFF 3";
        Event e1 = Event.parseEvent(str1, home);
        Event e2 = Event.parseEvent(str2, home);
        assertNotEquals(e1, null);
        assertNotEquals(e2, null);
        assertEquals(e1.getAppliance(),home.getWaterHeater());
        assertEquals(e1.getState(), true);
        assertEquals(e2.getState(), false);
        assertEquals(e1.getTime(),2);
        assertEquals(e2.getTime(),3);
    }
    
    @Test
    public void parseEventSuccessTest2() {
        SmartHome home = new SmartHome();
        String str1 = "COOKING_OVEN ON 2";
        String str2 = "COOKING_OVEN OFF 3";
        Event e1 = Event.parseEvent(str1, home);
        Event e2 = Event.parseEvent(str2, home);
        assertNotEquals(e1, null);
        assertNotEquals(e2, null);
        assertEquals(e1.getAppliance(),home.getCookingOven());
        assertEquals(e1.getState(), true);
        assertEquals(e2.getState(), false);
        assertEquals(e1.getTime(),2);
        assertEquals(e2.getTime(),3);
    }
    
    @Test
    public void parseEventSuccessTest3() {
        SmartHome home = new SmartHome();
        String str1 = "AIR_CONDITIONER ON 2";
        String str2 = "AIR_CONDITIONER OFF 3";
        Event e1 = Event.parseEvent(str1, home);
        Event e2 = Event.parseEvent(str2, home);
        assertNotEquals(e1, null);
        assertNotEquals(e2, null);
        assertEquals(e1.getAppliance(),home.getAirConditioner());
        assertEquals(e1.getState(), true);
        assertEquals(e2.getState(), false);
        assertEquals(e1.getTime(),2);
        assertEquals(e2.getTime(),3);
    }
    
    @Test
    public void parseEventFailureTest1() {
        SmartHome home = new SmartHome();
        String str1 = "COOKING ON 2";
        String str2 = "COOKING_OVEN ONN 3";
        String str3 = "AIR_CONDITIONER ON 2a";
        Event e1 = Event.parseEvent(str1, home);
        Event e2 = Event.parseEvent(str2, home);
        Event e3 = Event.parseEvent(str3, home);
        assertEquals(e1, null);
        assertEquals(e2, null);
        assertEquals(e3, null);
    }
}
