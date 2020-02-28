package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;

import java.util.List;

import static java.util.Collections.emptyList;

public class TripService {

    private final TripDAO tripDAO;

    public TripService() {
        tripDAO = new TripDAO();
    }

    public TripService(TripDAO tripDAO) {
        this.tripDAO = tripDAO;
    }

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
        return tripDAO.findTripsBy(user);
    }

}
