package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TripServiceTest {

    public static final User INVALID_USER = null;
    public static final User STRANGER = new User();
    private static final User LOGGED_IN_USER = new User();
    private static final User FRIEND = new User();

    @Test
    void should_throw_exception_when_user_not_logged_in() {
        assertThrows(UserNotLoggedInException.class,()->{
            new TripService().getTripsByUser(STRANGER, INVALID_USER);
        });
    }

    @Test
    void should_not_return_trips_when_GUEST_logged_in() {
        List<Trip> trips = new TripService().getTripsByUser(STRANGER, LOGGED_IN_USER);

        assertEquals(0,trips.size());
    }

    @Test
    void should_return_trips_when_FRIEND_logged_in() {
        List<Trip> expectedTrips = Collections.singletonList(new Trip());
        FRIEND.addFriend(LOGGED_IN_USER);
        TripDAO tripDao = Mockito.mock(TripDAO.class);
        Mockito.when(tripDao.findTripsBy(FRIEND)).thenReturn(expectedTrips);

        List<Trip> actualTrips = new TripService(tripDao).getTripsByUser(FRIEND, LOGGED_IN_USER);

        assertEquals(expectedTrips,actualTrips);
    }

}
