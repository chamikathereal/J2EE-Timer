package lk.jiat.web.eetimer.timer;

import java.io.Serializable;

public class Task implements Serializable {
    private final String taskId;
    private final String taskName;

    public Task(String taskId, String taskName) {
        this.taskId = taskId;
        this.taskName = taskName;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }
}
