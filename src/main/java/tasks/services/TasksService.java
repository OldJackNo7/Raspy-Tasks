package tasks.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.model.TasksOperations;

import java.util.Date;

public class TasksService {

    private ArrayTaskList tasks;

    public TasksService(ArrayTaskList tasks){
        this.tasks = tasks;
    }


    public ObservableList<Task> getObservableList(){
        return FXCollections.observableArrayList(tasks.getAll());
    }
    public String getIntervalInHours(Task task){
        int seconds = task.getRepeatInterval();
        int minutes = seconds / DateService.SECONDS_IN_MINUTE;
        int hours = minutes / DateService.MINUTES_IN_HOUR;
        minutes = minutes % DateService.MINUTES_IN_HOUR;
        return formTimeUnit(hours) + ":" + formTimeUnit(minutes);//hh:MM
    }
    public String formTimeUnit(int timeUnit){
        StringBuilder sb = new StringBuilder();
        if (timeUnit < 10) sb.append("0");
        if (timeUnit == 0) sb.append("0");
        else {
            sb.append(timeUnit);
        }
        return sb.toString();
    }
    public int parseFromStringToSeconds(String stringTime){//hh:MM
        String[] units = stringTime.split(":");
        int hours = Integer.parseInt(units[0]);
        int minutes = Integer.parseInt(units[1]);
        return (hours * DateService.MINUTES_IN_HOUR + minutes) * DateService.SECONDS_IN_MINUTE;
    }

    public Iterable<Task> filterTasks(Date start, Date end){
        TasksOperations tasksOps = new TasksOperations(getObservableList());
        return tasksOps.incoming(start,end);
    }

    public Task createTask(String newTitle, Date newStartDate, boolean isRepeated, Date newEndDate, int newInterval, boolean isActive) {
        Task result;

        if(newTitle.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null");
        }
        if(newTitle.length() > 20) {
            throw new IllegalArgumentException("Title cannot have more than 20 characters");
        }
        if (newStartDate.getTime() < 0) {
            throw new IllegalArgumentException("Start time cannot be negative");
        }

        if (isRepeated){
            if (newEndDate.getTime() < 0) {
                throw new IllegalArgumentException("End time cannot be negative");
            }
            if (newStartDate.after(newEndDate)) {
                throw new IllegalArgumentException("Start date should be before end");
            }
            if (newInterval < 1 || newInterval > 216000) {
                throw new IllegalArgumentException("Interval must be between 1 minute and 60 hours");
            }
            result = new Task(newTitle, newStartDate, newEndDate, newInterval);
        }
        else {
            result = new Task(newTitle, newStartDate);
        }
        result.setActive(isActive);
        return result;
    }
}
