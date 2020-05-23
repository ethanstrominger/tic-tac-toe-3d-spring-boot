package com.ethan;

public class GamesSession {

    private static String currentNickname;
    private static int guestCount = 0;

    public static void setCurrentNickname(String currentNickname) {
        GamesSession.currentNickname = currentNickname;
    }

    public static String getCurrentNickname() {
        return GamesSession.currentNickname;
    }

    public static int getGuestCount() {
        return GamesSession.guestCount;
    }

    public static void setGuestNickname(String beforeNickname) {
        GamesSession.guestCount = GamesSession.guestCount + 1;
        GamesSession.currentNickname =
                "Guest"+Integer.toString(guestCount) + "-" + beforeNickname;
    }
}
