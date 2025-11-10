package TODO管理アプリ;

import java.time.LocalDate;

public class Task {
    static String title;
    static LocalDate dueDate;
    static int priority=3;
    static boolean done=false;


    public void Task(String title,LocalDate dueDate,int priority) {

    }
    public void markDone() {
        done=true;
    }
    public boolean isDone() {
        return done;
    }
    public String getTitle() {
        return title;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }
    public int getPriority() {
        return priority;
    }
    public String toString() {
        return "Title: " + title + ", Due Date: " + dueDate + ", Priority: " + priority + ", Done: " + done;
    }
    public int compareTo(Task other) {
        return this.dueDate.compareTo(other.dueDate);
    }
}