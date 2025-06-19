package lk.jiat.web.eetimer.ejb;

import jakarta.ejb.Schedule;
import jakarta.ejb.Schedules;
import jakarta.ejb.Stateless;

@Stateless
public class AutoTimerSessionBean {
    @Schedules({
            @Schedule(hour = "*", minute = "30", second = "10"),
            @Schedule(hour = "*", minute = "30", second = "10")
    })
    public void autoSchedule() {
        System.out.println("autoSchedule....");
    }
}
