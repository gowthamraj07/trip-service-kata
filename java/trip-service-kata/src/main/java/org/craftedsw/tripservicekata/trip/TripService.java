package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;

import java.util.List;

import static java.util.Collections.emptyList;

public class TripService {

    public List<Trip> getTripsByUser(User user, User loggedUser) throws UserNotLoggedInException {
        if (loggedUser == null) {
            throw new UserNotLoggedInException();
        }

        if (areTheyStrangers(user, loggedUser)) {
            return emptyList();
        }

        return findTripsByUser(user);

    }

    private boolean areTheyStrangers(User user, User loggedUser) {
        return !user.isFriend(loggedUser);
    }

    protected List<Trip> findTripsByUser(User user) {
        return TripDAO.findTripsByUser(user);
    }

}
