package ua.com.okonsergei.view;

import java.util.Scanner;

public class MainView {

    private final WriterView writerView = new WriterView();
    private final LabelView labelView = new LabelView();
    private final PostView postView = new PostView();
    private final PostStatusView postStatusView = new PostStatusView();

    private final Scanner scanner = new Scanner(System.in);

    public MainView() {
    }

    public void run() {
        boolean exit = false;
        try {
            String mainMenu = """
                    MAIN MENU
                    Make your choice:
                    1 Writer
                    2 Label
                    3 Post
                    4 PostStatus
                    ____________________________________________
                    5 Exit""";

            System.out.println(Message.LINE.getMessage() + "\n" + mainMenu);

            String choice = scanner.next();

            switch (choice) {
                case "1":
                    writerView.showSecondMenu();
                    break;
                case "2":
                    labelView.showSecondMenu();
                    break;
                case "3":
                    postView.showSecondMenu();
                    break;
                case "4":
//                postStatusView.showMenu();
//                break;
                case "5":
                    exit = true;
                    break;
                default:
                    System.out.println(Message.ERROR_INPUT.getMessage());
                    run();

            }
            if (exit) {
                return;
            }
        } finally {
            scanner.close();
        }
    }
}



