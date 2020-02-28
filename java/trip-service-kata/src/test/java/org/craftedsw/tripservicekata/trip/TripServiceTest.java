package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TripServiceTest {

    public static final User INVALID_USER = null;
    public static final User GUEST = new User();
    private static final User LOGGED_IN_USER = new User();

    @Test
    void should_throw_exception_when_user_not_logged_in() {
        assertThrows(UserNotLoggedInException.class,()->{
            new TestableTripService(INVALID_USER).getTripsByUser(GUEST);
        });
    }

    @Test
    void should_not_return_trips_when_GUEST_logged_in() {
        List<Trip> trips = new TestableTripService(LOGGED_IN_USER).getTripsByUser(GUEST);

        assertEquals(0,trips.size());
    }

    private class TestableTripService extends TripService {
        User invalidUser;

        private TestableTripService(User invalidUser) {
            this.invalidUser = invalidUser;
        }

        @Override
        protected User getLoggedUser() {
            return invalidUser;
        }
    }
}
