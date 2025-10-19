public class ComplexTask {
    private final int taskId;
    private int result;

    public ComplexTask(int taskId) {
        this.taskId = taskId;
    }

    public void execute() {
        System.out.println(Thread.currentThread().getName() + ": Execution of a task " + taskId);
        try {
            Thread.sleep(400 + (long) (Math.random() * 400));
            result = taskId * 2;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(Thread.currentThread().getName() + ": Execution of a task " + taskId + " with result " + result);
    }

    public int getResult() {
        return result;
    }
}
