public class RunnableExample {
    public static void main(String[] args) {
        Runnable transferTask =
                () -> System.out.println("Processing transfer");
        Thread.ofPlatform()
                .name("transfer-worker")
                .start(transferTask);
    }
}