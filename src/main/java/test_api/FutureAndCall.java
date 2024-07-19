package test_api;

import java.util.concurrent.*;

/**
 * @ClassName FutureAndCall
 * @Description TODO
 * @Author @hzp
 * @Date 2024/7/11 20:32
 * @Version 1.0
 */
public class FutureAndCall {
    public static void main(String[] args) {

    }
    //不用future如何获取Callable 任务的执行结果

    public void testFutureAndCall() {
        //1.直接调用 Callable 的 call 方法
        //这种方式是最直接的，但是它会在调用线程中同步执行任务，这意味着你不能享受异步执行的好处
        Callable<Integer> task = () -> {
            // 模拟耗时任务
            Thread.sleep(2000);
            return 42;
        };

        try {
            Integer result = task.call(); // 同步执行任务并获取结果
            System.out.println("Task completed! Result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //2.回调机制的作用是在任务完成后自动执行预先定义的操作（这里是打印结果）。
        // 这使得任务完成后可以立即处理结果，而无需主线程等待任务完成
        MyCallable task1 = new MyCallable(result -> {
            System.out.println("Task completed! Result: " + result);
        });

        new Thread(() -> {
            try {
                task1.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        // 主线程可以继续做其他事情
        System.out.println("Task is submitted.");
    }
    public interface Callback<T> {
        void onComplete(T result);
    }

    public  class MyCallable implements Callable<Integer> {
        private final Callback<Integer> callback;

        public MyCallable(Callback<Integer> callback) {
            this.callback = callback;
        }

        @Override
        public Integer call() throws Exception {
            // 模拟耗时任务
            Thread.sleep(2000);
            Integer result = 42;
            callback.onComplete(result);
            return result;
        }
    }
    /**
     * 1.Callable 是一个任务接口，表示需要执行的异步任务。它的 call 方法可以返回一个结果，并且可以抛出异常。
     * callable可以抛出受检异常，runnable不能
             * 特性	受检异常 (Checked Exception)	非受检异常 (Unchecked Exception)
             * 继承关系	继承自 java.lang.Exception	继承自 java.lang.RuntimeException
             * 编译器检查	是，在编译时强制检查	否，编译器不会强制检查
             * 是否强制处理	是，必须捕获或声明抛出	否，可以选择处理或不处理
             * 典型代表	IOException, SQLException	NullPointerException, ArrayIndexOutOfBoundsException
             * 处理方式	try-catch 或 throws	可选的 try-catch 或无需处理
     * 2.Future 是一个结果接口，表示异步任务的结果。它提供了方法来取消任务、查询任务状态和获取任务结果。
     * 通常，Callable 与 Future 结合使用。[通过 ExecutorService 提交 Callable 任务，返回一个 Future 对象]。
     * 这个 【Future 对象可以用于获取 Callable 任务的执行结果】
     *
     */
    /**
     * 用于表示异步计算的结果。它提供了一种方法，可以在异步任务完成后获取其结果。
     * Future 的主要目标是解决如何获取异步任务的结果、如何取消任务以及如何检测任务是否完成等问题。
     */
    public void testFuture() {

        // 创建一个 ExecutorService
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // 提交一个 Callable 任务并返回一个 Future
        Future<Integer> future = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                // 模拟耗时任务
                Thread.sleep(2000);
                return 42;
            }
        });

        // 做其他事情
        System.out.println("Task is submitted.");

        // 检查任务是否完成
        while (!future.isDone()) {
            System.out.println("Task is not completed yet...");
            try {
                Thread.sleep(500); // 休眠一段时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 获取任务结果
        try {
            Integer result = future.get();
            System.out.println("Task completed! Result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    /**
     * Future 是一个接口，它的主要实现类是 FutureTask。FutureTask 提供了基本的异步计算功能，
     * 并实现了 Runnable 接口，可以被线程执行
     */
    public void testFutureTask() {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                // 模拟耗时任务
                Thread.sleep(2000);
                return 42;
            }
        };

        // 创建 FutureTask
        FutureTask<Integer> futureTask = new FutureTask<>(callable);

        // 使用 ExecutorService 执行 FutureTask
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(futureTask);

        // 检查任务是否完成
        while (!futureTask.isDone()) {
            System.out.println("Task is not completed yet...");
            try {
                Thread.sleep(500); // 休眠一段时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 获取任务结果
        try {
            Integer result = futureTask.get();
            System.out.println("Task completed! Result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    /**
     * ，它不仅实现了 Future 接口，还实现了 CompletionStage 接口。它允许你以非阻塞的方式编写并发代码，
     * 并提供了丰富的 API 用于异步编程。CompletableFuture 使得在 Java 中进行复杂的异步编程变得更加简洁和直观。
     *
     * 主要功能
     * 异步执行：通过非阻塞方式执行任务。
     * 组合多个异步操作：支持链式调用和组合多个异步任务。
     * 处理结果和异常：提供多种方法来处理任务的结果或异常。
     * 并行处理：支持并行地处理多个任务。
     */
    public void testCompletableFuture() {
        //1.创建 CompletableFuture
        // CompletableFuture 提供了多种静态方法来创建实例：
        //
        //supplyAsync：以异步方式执行一个返回结果的任务。
        //runAsync：以异步方式执行一个不返回结果的任务。
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            // 模拟耗时计算
            return 42;
        });

        CompletableFuture<Void> futureRun = CompletableFuture.runAsync(() -> {
            // 模拟耗时任务
        });

        //2.组合多个异步操作
        //CompletableFuture 提供了丰富的 API 来组合多个异步操作，例如：
        //
        //thenApply：在当前任务完成后，执行一个函数并返回其结果。
        //thenAccept：在当前任务完成后，执行一个消费者操作（不返回结果）。
        //thenRun：在当前任务完成后，执行一个 Runnable 操作（不返回结果）。
        //thenCombine：将两个独立的 CompletableFuture 的结果进行组合。
        //thenCompose：在当前任务完成后，执行另一个返回 CompletableFuture 的函数，并返回其结果。
        //3.并行处理
        //你可以并行地执行多个异步任务，并在所有任务完成后处理它们的结果：
        //
        //allOf：等待所有给定的 CompletableFuture 完成。
        //anyOf：在任意一个给定的 CompletableFuture 完成时完成
        // 4.自定义线程池
        //默认情况下，CompletableFuture 使用 ForkJoinPool.commonPool() 作为默认的线程池，但你也可以使用自定义的线程池来执行异步任务：

        // 原理：CompletableFuture 的异步任务可以通过两种方式执行：
        //
        //默认线程池：ForkJoinPool.commonPool() 是 Java 的通用线程池，CompletableFuture 默认使用这个线程池来执行异步任务。
        //自定义线程池：可以通过传递 Executor 参数给异步方法来使用自定义的线程池。
        //任务提交
        //当你调用 supplyAsync、runAsync 等方法时，CompletableFuture 会将任务提交到线程池中执行。
        //任务会被包装成 ForkJoinTask 或 Runnable 提交到 ForkJoinPool 或自定义的 Executor 中执行。

        ExecutorService executor = Executors.newFixedThreadPool(3);

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            // 模拟耗时任务
            sleep(2000);
            return 10;
        }, executor);

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            // 模拟耗时任务
            sleep(1000);
            return 20;
        }, executor);

        CompletableFuture<Integer> future3 = future1.thenCombine(future2, Integer::sum);

        CompletableFuture<Void> finalFuture = future3.thenAccept(result ->
                System.out.println("Combined Result: " + result)
        );

        try {
            finalFuture.get();  // 阻塞等待所有任务完成
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }
    private  void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void testForkJoinPool() {
        //工作窃取算法：
        //
        //每个线程都有一个双端队列（deque），用于存储要执行的任务。
        //线程优先处理自己队列中的任务，当自己的队列空时，从其他线程的队列尾部窃取任务执行。
        //这种机制可以最大限度地利用处理器资源，减少线程的空闲时间，提高并行计算效率。
        //ForkJoinTask：
        //
        //ForkJoinPool 执行的任务必须是 ForkJoinTask 的子类，常用的子类包括 RecursiveTask（有返回值）和 RecursiveAction（无返回值）。
        //任务可以通过 fork 方法进行拆分，通过 join 方法合并结果。
    }
}
