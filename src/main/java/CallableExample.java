import java.util.concurrent.Callable;

public class CallableExample {
    public static void main(String[] args) throws Exception {

        Callable<String> transferTask =
                () -> "TRANSFER_APPROVED";

        String result = transferTask.call();

        System.out.println(result);
    }
}