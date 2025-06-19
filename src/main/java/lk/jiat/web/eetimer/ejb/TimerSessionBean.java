package lk.jiat.web.eetimer.ejb;

import jakarta.annotation.Resource;
import jakarta.ejb.*;
import lk.jiat.web.eetimer.timer.Task;

import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

@Stateless
public class TimerSessionBean {

    @Resource
    private TimerService timerService;

    public Task doTask(long time) {
        //timerService.createTimer(10000,"Clock"); // EJB 3.0/3.1
        //timerService.createIntervalTimer(1000,5000,new TimerConfig()); // EJB new versions

        TimerConfig timerConfig = new TimerConfig();
        String taskId = UUID.randomUUID().toString();
        Task task = new Task(taskId, "Test Task");
        timerConfig.setInfo(task);
        timerConfig.setPersistent(false);

        ScheduleExpression se = new ScheduleExpression();

        se.hour("*");
        se.minute("*");
        se.second("*/10");


        timerService.createCalendarTimer(se, timerConfig);

        return task;
    }

    @Timeout
    public void timeOutTask1(Timer timer) {

        Serializable info = timer.getInfo();
        if (info instanceof Task) {
            Task task = (Task) info;
            System.out.println(task.getTaskName() + ": " + task.getTaskId() + " Task1 is done. ");
        }

        //System.out.println("Task timed out." + timer);
    }

    @Timeout
    public void timeOutTask2(Timer timer) {

        Serializable info = timer.getInfo();
        if (info instanceof Task) {
            Task task = (Task) info;
            System.out.println(task.getTaskName() + ": " + task.getTaskId() + " Task2 is done. ");
        }

        //System.out.println("Task timed out." + timer);
    }


    public void cancelTimer(String taskId) {

        for (Timer timer : timerService.getTimers()) {
            if (timer.getInfo() instanceof Task && ((Task) timer.getInfo()).getTaskId().equals(taskId)) {
                timer.cancel();
                System.out.println("Timer cancelled: " + timer);
                break;
            }
        }
    }
}
