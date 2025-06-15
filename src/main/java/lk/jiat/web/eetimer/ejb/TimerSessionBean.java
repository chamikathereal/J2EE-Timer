package lk.jiat.web.eetimer.ejb;

import jakarta.annotation.Resource;
import jakarta.ejb.*;

import java.util.Collection;

@Stateless
public class TimerSessionBean {

    @Resource
    private TimerService timerService;

    public void doTask() {
        timerService.createTimer(10000,"Clock"); // EJB 3.0/3.1
        //timerService.createIntervalTimer(1000,5000,new TimerConfig()); // EJB new versions

        TimerConfig timerConfig = new TimerConfig();
        timerConfig.setInfo("Info");

        timerService.createSingleActionTimer(10000, timerConfig);

        Collection<Timer> allTimers = timerService.getTimers();
        allTimers.forEach(timer -> {

        });
    }

    @Timeout
    public void timeOutTask(Timer timer) {
        System.out.println("Task timed out." + timer);
    }

    public void cancelTimer() {
    }

//    public void cancelTimer(){
//        if(timer != null){
//            timer.cancel();
//        }
//    }
}
