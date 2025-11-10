package TODO管理アプリ;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Taskmanager {
    static List<Task> tasks=new ArrayList<>();
    static fileHandler fileHandler=new fileHandler("tasks.json");

    public void addTask(String title,LocalDate dueDate,int priority) {
        Task task=new Task(title,dueDate,priority);
        tasks.add(task);
        fileHandler.saveTasks(tasks);
    }
    public void listTasks() {
        for (int i=0;i<tasks.size();i++) {
            System.out.println(i + ": " + tasks.get(i).toString());
        }
    }
    public boolean markTaskAsDone(int index) {
        if (index < 0 || index >= tasks.size()) {
            return false;
        }
        Task task=tasks.get(index);
        task.markDone();
        fileHandler.saveTasks(tasks);
        return true;
    }
    public boolean removeTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            return false;
        }
        tasks.remove(index);
        fileHandler.saveTasks(tasks);
        return true;
    }
    public void sortByDueDate() {
        Collections.sort(tasks, Comparator.comparing(Task::getDueDate));
    }
    public void sortByPriority() {
        Collections.sort(tasks, Comparator.comparingInt(Task::getPriority));
    }
    public List<Task> searTasks(String keyword){ 
        //タイトルにキーワードを含むタスクを抽出
    }
    public void saveTasks(String path){

    }
    public void loadTasks(String path){

    }
}
