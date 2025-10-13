import java.util.Stack;

public class SnapShotStringBuilder {
    private StringBuilder stringBuilder;
    private final Stack<Memento> stackHistory;

    private record Memento(String state) {
    }

    public SnapShotStringBuilder() {
        stringBuilder = new StringBuilder();
        stackHistory = new Stack<>();
        saveCurrentSnapShot();
    }


    private void saveCurrentSnapShot() {
        stackHistory.push(new Memento(stringBuilder.toString()));
    }

    public void append(String inputString) {
        saveCurrentSnapShot();
        stringBuilder.append(inputString);
    }

    public void delete(int start, int end) {
        saveCurrentSnapShot();
        stringBuilder.delete(start, end);
    }

    public void undo() {
        if (stackHistory.size() > 1) {
            stackHistory.pop();
            Memento memento = stackHistory.peek();
            stringBuilder = new StringBuilder(memento.state());
        } else {
            System.out.println("Уже используется последнее состояние");
        }
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }

}
