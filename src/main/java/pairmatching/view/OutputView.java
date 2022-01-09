package pairmatching.view;

public class OutputView {
    public static void showErrorMessage(IllegalArgumentException e) {
        System.out.println("[ERROR]" + e.getMessage());
    }
}
