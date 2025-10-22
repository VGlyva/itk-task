import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        int n = 10;

        ForkJoinPool pool = new ForkJoinPool();
        FactorialTask task = new FactorialTask(n);

        long factorial = pool.invoke(task);
        System.out.println("Факториал " + n + "! = " + factorial);
    }
}