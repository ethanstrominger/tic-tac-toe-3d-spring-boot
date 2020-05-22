package com.ethan;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SessionTests {

    @Test
    public void testSetSessionUser() {
        String currentNickName="John";
        GamesSession.setCurrentNickName(currentNickName);
        assertEquals(currentNickName, GamesSession.getCurrentNickName());
    }

    @Test
    public void testSetGuestUser() {
        String beforeNickName="Fred";
        int guestCountBefore = GamesSession.getGuestCount();
        String expectedNickName = "Guest" +
                Integer.toString(guestCountBefore + 1) +
                "-" + beforeNickName;
        GamesSession.setGuestNickName(beforeNickName);
        assertEquals(expectedNickName, GamesSession.getCurrentNickName());
        assertEquals(guestCountBefore + 1, GamesSession.getGuestCount());
    }
}
