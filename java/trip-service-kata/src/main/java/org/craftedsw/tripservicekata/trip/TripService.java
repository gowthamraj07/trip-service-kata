package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

import java.util.List;

import static java.util.Collections.emptyList;

public class TripService {

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        User loggedUser = getLoggedUser();
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

    protected User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }

}
