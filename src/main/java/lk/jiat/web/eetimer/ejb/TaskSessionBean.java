package lk.jiat.web.eetimer.ejb;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.enterprise.concurrent.ManagedExecutorService;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

@Stateless
public class TaskSessionBean {

    @Resource
    ManagedExecutorService executorService;

    public Future<String> doTask() {
        System.out.println("doTask..." + Thread.currentThread().getName());

        return executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {

                System.out.println("Sending message..." + Thread.currentThread().getName());

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("Message sent" + Thread.currentThread().getName() );

                return "Task done.";
            }
        });


    }

}
