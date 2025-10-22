import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Long> {
    private final int start;
    private final int end;
    private static final int THRESHOLD = 5;

    public FactorialTask(int n) {
        this(1, n);
    }

    private FactorialTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD) {
            long result = 1;
            for (int i = start; i <= end; i++) {
                result *= i;
            }
            return result;
        } else {
            List<FactorialTask> tasks = new ArrayList<>();
            for (int i = start; i <= end; i += THRESHOLD + 1) {
                int subEnd = Math.min(i + THRESHOLD, end);
                tasks.add(new FactorialTask(i, subEnd));
            }

            for (FactorialTask task : tasks) {
                task.fork();
            }

            long result = 1;
            for (FactorialTask task : tasks) {
                result *= task.join();
            }
            return result;
        }
    }
}
