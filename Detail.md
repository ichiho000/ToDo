# ToDoアプリ 詳細設計書（コンソール版）

---

## 1. クラス構成

### 1.1 Main クラス

#### 役割
- アプリケーションのエントリポイント。
- メニュー表示、ユーザー入力、各機能の呼び出しを行う。
- TaskManager を利用してタスクの追加・削除を行う。

#### フィールド

- `private Scanner scanner;`
  - 標準入力からユーザー入力を受け取るためのオブジェクト。
- `private TaskManager taskManager;`
  - タスクの追加・削除・一覧取得を行う管理クラス。
- `private boolean running;`
  - メインループ継続フラグ。`false` で終了。

#### メソッド一覧

1. `public static void main(String[] args)`
2. `private void run()`
3. `private void showMenu()`
4. `private void handleMenuInput(String input)`
5. `private void addTaskFlow()`
6. `private void deleteTaskFlow()`
7. `private void printTaskList()`
8. `private boolean isValidTaskName(String input)`
9. `private boolean isBlankOrSpaces(String input)`

---

### 1.2 TaskManager クラス

#### 役割
- タスクを内部リストで管理する。
- タスクの追加・削除・取得などの操作を提供する。

#### フィールド

- `private List<String> tasks;`
  - タスクを保持するリスト。

#### メソッド一覧

1. `public TaskManager()`
2. `public void addTask(String task)`
3. `public void removeTask(int index)`
4. `public List<String> getTasks()`
5. `public boolean hasTasks()`
6. `public int size()`

---

## 2. Main クラス 詳細

### 2.1 `public static void main(String[] args)`

- 目的  
  - アプリケーションの開始ポイント。Main インスタンスを生成して `run()` を呼び出す。

- 引数  
  - `String[] args` : コマンドライン引数（未使用）。

- 戻り値  
  - なし。

- 処理手順  
  1. `Main` クラスのインスタンスを生成する。
  2. 生成したインスタンスの `run()` メソッドを呼び出す。

---

### 2.2 `private void run()`

- 目的  
  - 初期化処理とメインループの開始・終了を管理する。

- 引数  
  - なし。

- 戻り値  
  - なし。

- 事前条件  
  - なし。

- 事後条件  
  - `running` フラグが `false` になったタイミングでメソッド終了。

- 処理手順  
  1. `scanner` に `new Scanner(System.in)` を代入。
  2. `taskManager` に `new TaskManager()` を代入。
  3. `running` に `true` を設定。
  4. `while (running)` ループを開始。
     - `showMenu()` を呼び出す。
     - 入力プロンプトを表示する（例: `> `）。
     - `scanner.nextLine()` でメニュー番号の文字列を取得する。
     - 取得した文字列を `handleMenuInput(input)` に渡す。
  5. ループ終了後、終了メッセージ（例:「アプリケーションを終了します。」）を表示。
  6. `scanner.close()` を呼び出してリソースを解放する。

---

### 2.3 `private void showMenu()`

- 目的  
  - 現在のタスク一覧とメニュー項目を表示する。

- 引数  
  - なし。

- 戻り値  
  - なし。

- 処理手順  
  1. タイトル文字列を表示（例: `==== ToDo アプリ ====`)
  2. 「現在のタスク一覧:」と表示。
  3. `printTaskList()` を呼び出し、タスク一覧を表示。
  4. メニュー選択文を表示：
     - `1: タスクを追加`
     - `2: タスクを削除`
     - `0: 終了`

---

### 2.4 `private void handleMenuInput(String input)`

- 目的  
  - ユーザーが入力したメニュー番号に応じて処理を振り分ける。

- 引数  
  - `String input` : メニュー番号として入力された文字列。

- 戻り値  
  - なし。

- 処理手順  
  1. `switch` または `if-else` で `input` の値を判定。
  2. 次のように分岐する：
     - `"1"` → `addTaskFlow()` を呼び出す。
     - `"2"` → `deleteTaskFlow()` を呼び出す。
     - `"0"` → `running = false` とし、「終了します」などのメッセージを表示。
     - 上記以外 → 「不正な入力です」などのエラーメッセージを表示。

---

### 2.5 `private void addTaskFlow()`

- 目的  
  - タスク追加に関する一連のフロー（入力 → バリデーション → 追加）を実行する。

- 引数  
  - なし。

- 戻り値  
  - なし。

- 処理手順  
  1. 「【タスク追加】」と見出しを表示。
  2. 「タスク内容を入力してください（全角/半角/絵文字OK）:」などのプロンプトを表示。
  3. `scanner.nextLine()` で入力文字列 `taskInput` を取得。
  4. `isValidTaskName(taskInput)` を呼び出し、結果を判定。
     - `true` の場合：
       - `taskManager.addTask(taskInput)` を呼び出す。
       - 「タスクを追加しました。」などのメッセージを表示。
       - メソッドを終了。
     - `false` の場合：
       - 「タスク名が不正です。空文字やスペースだけの入力は使用できません。」などのエラーを表示。
       - 再度入力してもらうために、3. へ戻る（ループ構造にする）。

---

### 2.6 `private void deleteTaskFlow()`

- 目的  
  - タスク削除に関する一連のフロー（一覧表示 → 番号入力 → バリデーション → 削除）を実行する。

- 引数  
  - なし。

- 戻り値  
  - なし。

- 処理手順  
  1. 「【タスク削除】」と表示。
  2. `taskManager.hasTasks()` を呼び出し、タスクが存在するか確認。
     - `false` の場合：
       - 「削除できるタスクがありません。」と表示。
       - メソッドを終了。
  3. 「現在のタスク一覧:」と表示。
  4. `printTaskList()` を呼び出して一覧を表示。
  5. 「削除したいタスクの番号を入力してください:」と表示。
  6. `scanner.nextLine()` で入力文字列 `numberInput` を取得。
  7. `numberInput` を整数に変換しようとする：
     - `try { index = Integer.parseInt(numberInput); } catch (NumberFormatException e) { ... }`
     - 変換に失敗した場合：
       - 「数字を入力してください。」などのエラーメッセージを表示。
       - 5. に戻る（ループ）。
  8. 変換に成功した場合、`index` が 1〜`taskManager.size()` の範囲内かチェック。
     - 範囲外の場合：
       - 「不正な番号です。存在するタスク番号を入力してください。」と表示。
       - 5. に戻る（ループ）。
  9. 問題なければ、内部リストは0始まりなので `taskManager.removeTask(index - 1)` を呼び出す。
  10. 「タスクを削除しました。」と表示。
  11. メソッド終了。

---

### 2.7 `private void printTaskList()`

- 目的  
  - 現在のタスク一覧を番号付きで表示する。

- 引数  
  - なし。

- 戻り値  
  - なし。

- 処理手順  
  1. `taskManager.getTasks()` で `List<String>` を取得。
  2. リストが空の場合：
     - 「（タスクはありません）」などのメッセージを表示。
     - メソッド終了。
  3. リストが空でない場合：
     - インデックス0から順にループし、`i + 1` を表示番号として使い、
       - `1: xxx`
       - `2: yyy`
       - のように出力する。

---

### 2.8 `private boolean isValidTaskName(String input)`

- 目的  
  - タスク名として有効かどうかを判定する。

- 引数  
  - `String input` : 入力されたタスク名。

- 戻り値  
  - 有効な場合：`true`  
  - 無効な場合：`false`

- バリデーションルール  
  - `null` の場合 → `false`
  - 文字数 0 の場合 → `false`
  - 全てがスペースのみの場合（半角/全角問わず） → `false`
  - 上記以外 → `true`

- 処理手順  
  1. `input == null` なら `false` を返す。
  2. `input.length() == 0` なら `false` を返す。
  3. `isBlankOrSpaces(input)` を呼び出し、`true` なら `false` を返す。
  4. それ以外の場合は `true` を返す。

---

### 2.9 `private boolean isBlankOrSpaces(String input)`

- 目的  
  - 文字列が空白だけで構成されているか判定する。

- 引数  
  - `String input`

- 戻り値  
  - 空白のみの場合：`true`  
  - 1文字でも非空白文字がある場合：`false`

- 処理手順（例）  
  1. 文字列の各文字に対してループを回す。
  2. 各文字 `ch` に対して、`Character.isWhitespace(ch)` を用いて判定。
  3. もし一つでも `isWhitespace` が `false` な文字があれば、`false` を返す。
  4. 全ての文字が空白文字の場合は `true` を返す。

---

## 3. TaskManager クラス 詳細

### 3.1 `public TaskManager()`

- 目的  
  - `tasks` リストの初期化。

- 引数  
  - なし。

- 戻り値  
  - なし。

- 処理手順  
  1. `tasks = new ArrayList<>();` としてリストを生成する。

---

### 3.2 `public void addTask(String task)`

- 目的  
  - タスクをリストに追加する。

- 引数  
  - `String task` : 追加するタスク文字列。Main 側でバリデーション済み想定。

- 戻り値  
  - なし。

- 処理手順  
  1. `tasks.add(task);` を実行する。

---

### 3.3 `public void removeTask(int index)`

- 目的  
  - 指定インデックスのタスクを削除する。

- 引数  
  - `int index` : 削除対象インデックス（0始まり）。

- 戻り値  
  - なし。

- 事前条件  
  - `0 <= index < tasks.size()` が保証されていること（Main 側でチェック済み）。

- 処理手順  
  1. `tasks.remove(index);` を実行する。

---

### 3.4 `public List<String> getTasks()`

- 目的  
  - タスクの一覧を取得する。

- 引数  
  - なし。

- 戻り値  
  - `List<String>` : タスク一覧。

- 備考  
  - 実装としては、直接 `tasks` を返してもよいが、変更されたくない場合は `Collections.unmodifiableList(tasks)` を返すことも検討できる（今回はシンプルさを優先して直接返す想定でもよい）。

---

### 3.5 `public boolean hasTasks()`

- 目的  
  - タスクが1件以上存在するかどうかを判定する。

- 引数  
  - なし。

- 戻り値  
  - `true` : タスク数が1件以上  
  - `false` : タスク数が0件

- 処理手順  
  1. `return !tasks.isEmpty();` または `return tasks.size() > 0;`

---

### 3.6 `public int size()`

- 目的  
  - タスク数を取得する。

- 引数  
  - なし。

- 戻り値  
  - `int` : タスク数。

- 処理手順  
  1. `return tasks.size();`

---

## 4. 動作フロー

### 4.1 全体フロー（メインループ）

1. アプリ起動
2. Main.main() 実行
3. run() 内で初期化（Scanner, TaskManager, running = true）
4. while (running)
   1. showMenu() で一覧とメニューを表示
   2. メニュー番号を文字列として入力
   3. handleMenuInput(input) で分岐
      - "1" → addTaskFlow()
      - "2" → deleteTaskFlow()
      - "0" → running = false
      - その他 → エラー表示
5. running が false になりループ終了
6. 終了メッセージ表示
7. scanner.close()
8. アプリ終了

---

### 4.2 タスク追加フロー

1. 「【タスク追加】」と表示
2. 「タスク内容を入力してください」プロンプト表示
3. ユーザーが文字列を入力
4. isValidTaskName(input) でチェック
   - 無効（空文字 or スペースのみ）の場合
     - エラーメッセージ表示
     - 2 に戻る（再入力）
   - 有効な場合
     - taskManager.addTask(input) を実行
     - 「タスクを追加しました。」と表示
     - フロー終了（メインループに戻る）

---

### 4.3 タスク削除フロー

1. 「【タスク削除】」と表示
2. taskManager.hasTasks() をチェック
   - false → 「削除できるタスクがありません。」と表示して終了（メインループに戻る）
3. 「現在のタスク一覧:」と表示
4. printTaskList() で一覧表示
5. 「削除したいタスクの番号を入力してください:」と表示
6. 番号を文字列として入力
7. 文字列を整数に変換（try-catch）
   - 例外発生 → エラーメッセージ表示して 5 に戻る
8. 整数 index が 1〜taskManager.size() か確認
   - 範囲外 → エラーメッセージ表示して 5 に戻る
9. 問題なければ taskManager.removeTask(index - 1) を実行
10. 「タスクを削除しました。」と表示
11. フロー終了（メインループに戻る）