package com.ethan;

public class TestUtil {
    static int getCountFromMe(String myNickname, GameMessage[] myMessages) {
        int actualFromMe = 0;
        for (GameMessage message: myMessages) {
            if (message.getFromNickname().equals(myNickname)) {
                actualFromMe++;
            }
        }
        return actualFromMe;
    }

    static int getCountToMe(String myNickname, GameMessage[] myMessages) {
        int actualToMe = 0;
        for (GameMessage message: myMessages) {
            if (message.getToNickname().equals(myNickname)) {
                actualToMe++;
            }
        }
        return actualToMe;
    }

    static void addRepeatMessages(
            String fromNickname,
            String toNickname,
            String messageText,
            int numberToRepeat) {
        for (int i = 0; i < numberToRepeat; i++) {
            GameMessage gameMessage = new GameMessage(fromNickname, toNickname, messageText + " " + i);
            GameMessages.addMessage(gameMessage);
        }
    }
}
