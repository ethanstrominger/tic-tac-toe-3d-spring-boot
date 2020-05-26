package com.ethan;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SessionTests {

    @Test
    public void testSessionClass() {
        GamesSession session = new GamesSession();
    }

    @Test
    public void testSetSessionUser() {
        String currentNickname="John";
        GamesSession.authenticateNickname(currentNickname);
        assertEquals(currentNickname, GamesSession.getCurrentNickname());
    }

    @Test
    public void testSetGuestUser() {
        String beforeNickname="Fred";
        int guestCountBefore = GamesSession.getGuestCount();
        String expectedNickname = "Guest" +
                (guestCountBefore + 1) +
                "-" + beforeNickname;
        GamesSession.setGuestNickname(beforeNickname);
        assertEquals(expectedNickname, GamesSession.getCurrentNickname());
        assertEquals(guestCountBefore + 1, GamesSession.getGuestCount());
    }
}
