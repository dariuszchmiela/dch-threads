public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());

        Thread.ofPlatform()
                .name("transfer-worker")
                .start(() -> System.out.println(Thread.currentThread().getName()));

        Thread.ofPlatform()
                .name("balance-worker")
                .start(() -> System.out.println(Thread.currentThread().getName()));

        Thread worker = Thread.ofPlatform()
                .name("transfer-worker-2")
                .unstarted(() -> System.out.println(Thread.currentThread().getName()));

        worker.start();

        Thread slowWorker = Thread.ofPlatform()
                .name("slow-worker")
                .start(() -> {
                    System.out.println("Start " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e){
                        throw new RuntimeException(e);
                    }
                    System.out.println("End " + Thread.currentThread().getName());
                });

        slowWorker.join();

        System.out.println("Main continues");

        Thread interruptWorker = Thread.ofPlatform()
                .name("interrupt-worker")
                .start(() -> {
                    try {
                        System.out.println("Worker sleeps");
                        Thread.sleep(5000);
                        System.out.println("Worker finished sleeping");
                    } catch (InterruptedException e) {
                        System.out.println("Worker was interrupted!");

                        Thread.currentThread().interrupt();

                        System.out.println("Interrupted flag: " + Thread.currentThread().isInterrupted());
                    }
                });

        Thread.sleep(500);

        interruptWorker.interrupt();
        interruptWorker.join();
    }
}
