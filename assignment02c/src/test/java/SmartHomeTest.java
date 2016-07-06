import org.junit.Test;

import static org.junit.Assert.*;

public class SmartHomeTest {
    
    @Test
    public void constructionTest1() {
        SmartHome home = new SmartHome();
        AirConditioner ac = home.getAirConditioner();
        WaterHeater wh = home.getWaterHeater();
        CookingOven co = home.getCookingOven();
        assertNotEquals(ac, null);
        assertNotEquals(wh, null);
        assertNotEquals(co, null);
        assertEquals(home.getAppliances().size(), 3);
    }
    
    @Test
    public void readScheduleSuccessTest() {
        SmartHome home = new SmartHome();
        try {
            home.readSchedule("schedule.txt");
            home.processSchedule();
            Controller.main(null);
            home.getTask().run();
        }
        catch(Exception e) {
            assertEquals(true, false);
        }
    }
    
    @Test
    public void readScheduleFailureTest1() {
        SmartHome home = new SmartHome();
        boolean successCheck = false;
        try {
            home.readSchedule("test_schedule.txt");
        }
        catch(Exception e) {
            successCheck = (e instanceof ScheduleNotFoundException);
        }
        assertEquals(successCheck,true);
    }
    
    @Test
    public void readScheduleSuccessTest2() {
        SmartHome home = new SmartHome();
        boolean successCheck = false;
        try {
            home.readSchedule("test_schedule1.txt");
        }
        catch(Exception e) {
            successCheck = (e instanceof BadScheduleException);
        }
        assertEquals(successCheck,true);
    }
    
    
    
}
