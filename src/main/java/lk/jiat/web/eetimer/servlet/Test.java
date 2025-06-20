package lk.jiat.web.eetimer.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.web.eetimer.ejb.TaskSessionBean;
import lk.jiat.web.eetimer.ejb.TimerSessionBean;
import lk.jiat.web.eetimer.timer.Task;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@WebServlet("/test")
public class Test extends HttpServlet {

    @EJB
    TaskSessionBean taskSessionBean;

    @EJB
    TimerSessionBean timerSessionBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Task task = timerSessionBean.doTask(20000);
        System.out.println("Task is scheduled: " + task.getTaskId());
        System.out.println(task.getTaskName());

        request.getSession().setAttribute("task", task);

    }
}
