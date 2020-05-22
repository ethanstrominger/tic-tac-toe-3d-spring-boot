package com.ethan;

public class GamesSession {

    private static String currentNickName;
    private static int guestCount = 0;

    public static void setCurrentNickName(String currentNickName) {
        GamesSession.currentNickName = currentNickName;
    }

    public static String getCurrentNickName() {
        return GamesSession.currentNickName;
    }

    public static int getGuestCount() {
        return GamesSession.guestCount;
    }

    public static void setGuestNickName(String beforeNickName) {
        GamesSession.guestCount = GamesSession.guestCount + 1;
        GamesSession.currentNickName =
                "Guest"+Integer.toString(guestCount) + "-" + beforeNickName;
    }
}
