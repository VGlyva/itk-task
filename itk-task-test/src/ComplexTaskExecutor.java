import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ComplexTaskExecutor {
    private final ExecutorService executorService;
    private final CyclicBarrier barrier;
    private final List<ComplexTask> tasks;

    public ComplexTaskExecutor(int numberOfTasks) {
        this.tasks = new ArrayList<>(numberOfTasks);

        this.barrier = new CyclicBarrier(numberOfTasks, () -> {
            System.out.println("All tasks have reached the barrier. Combining results...");
            int combinedResult = 0;
            for (ComplexTask task : tasks) {
                combinedResult += task.getResult();
            }
            System.out.println("Combined result of all tasks: " + combinedResult);
        });

        this.executorService = Executors.newFixedThreadPool(numberOfTasks);
    }

    public void executeTasks(int numberOfTasks) {
        tasks.clear();

        for (int i = 1; i <= numberOfTasks; i++) {
            ComplexTask task = new ComplexTask(i);
            tasks.add(task);

            executorService.submit(() -> {
                task.execute();
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (BrokenBarrierException e) {
                    System.out.println("Barrier broken: " + e.getMessage());
                }
            });
        }
    }

    public void shutdown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException ex) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
