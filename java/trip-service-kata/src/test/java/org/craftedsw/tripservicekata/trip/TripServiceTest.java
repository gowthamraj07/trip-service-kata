package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TripServiceTest {

    public static final User INVALID_USER = null;
    public static final User GUEST = new User();

    @Test
    void should_throw_exception_when_user_not_logged_in() {
        assertThrows(UserNotLoggedInException.class,()->{
            new TestableTripService().getTripsByUser(GUEST);
        });
    }

    private class TestableTripService extends TripService {
        @Override
        protected User getLoggedUser() {
            return INVALID_USER;
        }
    }
}
