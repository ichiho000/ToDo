import java.util.List;

public class Category {
    static String name;
    static List<Task> tasks=new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }
    public boolean removeTask(Task task) {
        return tasks.remove(task);
    }
    public List<Task> getTasks() {
        return tasks;
    }
    public void sortByDueDate() {
        Collections.sort(tasks, Comparator.comparing(Task::getDueDate));
    }
    public void sortByPriority() {
        Collections.sort(tasks, Comparator.comparingInt(Task::getPriority));
    }
}
