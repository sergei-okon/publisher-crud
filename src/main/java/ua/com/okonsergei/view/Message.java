package ua.com.okonsergei.view;

public enum Message {

    LINE("================================================="),
    SECOND_MENU("""
            1 Create
            2 Update
            3 Delete
            4 Show All
            -----------------------------------------------
            5 Exit"""),
    ERROR_INPUT("Invalid number entered"),
    SUCCESSFUL_OPERATION("Successful operation");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
