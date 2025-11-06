package TODO管理アプリ;

import java.time.LocalDate;

public class Task {
    String task;
    LocalDate dueDate;
    boolean done;
    int priorityLevel; //1:最高優先度, 2:高優先度, 3:中優先度, 4:低優先度

    public void setPriority() {
        // ここに優先度を設定するロジックを実装
        // 基本ルール：期限が今日に近いほど優先度が高い
        // 期限が今日なら最高優先度、1週間以内なら高優先度、1ヶ月以内なら中優先度、それ以降は低優先度

        int daysLeft = dueDate.getDayOfYear() - LocalDate.now().getDayOfYear();
        if (daysLeft < 0) {
            priorityLevel = 0; // 期限切れの場合は優先度を設定しない
            return; // 期限切れの場合は処理を終了
        } 
        
        if (daysLeft == 0) {
            priorityLevel = 1; // 最高優先度
        } else if (daysLeft <= 7) {
            priorityLevel = 2; // 高優先度
        } else if (daysLeft <= 30) {
            priorityLevel = 3; // 中優先度
        } else {
            priorityLevel = 4; // 低優先度
        }
    }

    public void displayTask() {
        System.out.print("タスク: " + task);
        System.out.print("期限: " + dueDate);
        System.out.print("完了: " + (done ? "Yes" : "No"));
    }

    public void setOrder() {
        // ここにタスクの順序を設定するロジックを実装
        // 優先度が高いものから順に並べる
        // 例えば、優先度1が最初、次に優先度2、3、4の順で表示する
    
    }




    
}