package quizpackage.model;

import java.util.List;

public class Chat {

    private Account receiverAccount;
    private Account displayAccount;
    private String displayName;
    private String displayMessage;

    private List<Message> messages;

    public Chat(Account receiver,Account sender, List<Message> messages){
        receiverAccount = receiver;
        displayAccount = sender;
        this.messages = messages;
        displayName = sender.getName() + " " + sender.getSurname();
        displayName = cropString(displayName);
        if(messages.size() > 0){
            displayMessage = messages.get(messages.size()-1).getText();
            displayMessage = cropString(displayMessage);
        } else {
            displayMessage = null;
        }
    }

    public Account getReceiverAccount() {
        return receiverAccount;
    }

    public Account getDisplayAccount() {
        return displayAccount;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    private String cropString(String to_crop){
        to_crop = to_crop.length() > 9 ? to_crop.substring(0,9) + "..." : to_crop;
        return to_crop;
    }
}
