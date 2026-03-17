import java.util.List;
import java.util.Scanner;

private Scanner scanner;
private TaskManager taskManager;
private boolean running;

public static void main(String[] args) {
    Main app = new Main();
    app.run();
}

public void run(){
    scanner = new Scanner(System.in);
    taskManager = new TaskManager();
    running = true;

    while (running){
        showMenu();
        String input = scanner.nextLine();
        handleMenuInput(input);
    }
    System.out.println("「アプリケーションを終了します。」");
    scanner.close();
}

public void showMenu(){
    System.out.println("=== タスク管理アプリ ===");
    System.out.println("現在のタスク一覧:");
    taskManager.printTasks();
    System.out.println("\nメニュー:");
    System.out.println("1. タスクを追加");
    System.out.println("2. タスクを削除");
    System.out.println("3. 終了");
}

public void handleMenuInput(String input){
    switch (input) {
        case "1":
            addTaskFlow();
            break;
        case "2":
            deleteTaskFlow();
            break;
        case "3":
            running = false;
            System.out.println("終了します");
            break;
        default:
            System.out.println("無効な入力です。");
    }
}

public void addTaskFlow(){
    System.out.println("【タスク追加】");
    System.out.print("タスク内容を入力してください（全角/半角/絵文字OK）: ");
    while (running) {
        String taskName = scanner.nextLine();
    boolean added = isValidTaskName(taskName);
    if(added == true){
        taskManager.addTask(taskName);
        System.out.println("タスクを追加しました。");
        break;
    } else {
        System.out.println("無効なタスク名です。空白やスペースのみのタスクは追加できません。");
        break;
    }
        
    }
}

private void deleteTaskFlow(){
    System.out.println("【タスク削除】");
    boolean hasTasks = taskManager.hasTasks();
    if(hasTasks == true){
        System.out.println("現在のタスク一覧:");
        taskManager.printTasks();
        while(running){
            System.out.print("削除するタスクの番号を入力してください: ");
            String numberInput = scanner.nextInt();
            try { int index = Integer.parseInt(numberInput);
                
                
            } catch (NumberFormatException e) {
                System.out.println("数字を入力してください。");
                return;
            }
        }
    }
}


private void printTasksList(){
    List<String> get = taskManager.getTasks();
    if(get.isEmpty()){
        System.out.println("（タスクはありません）");
        return;
    }else{
        for(int i=0;i<=get.size();i++){
            System.out.println(i+1+":"+get.get(i));
        }
    }
}

private boolean isValidTaskName(String input){
    if()
}

private boolean isBlankOrSpaces(String input){
    for(int i=0;i<input.length();i++){
        Character.isWhitespace(str.charAt(0));
        if(ch == false){
            return false;
        }else{
            return true;
        }
        
    }
}
