package com.suptracking.webservice;

import com.suptracking.entity.GpsPosition;
import com.suptracking.entity.User;
import com.suptracking.service.UserService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author EPTR
 */
@WebService(serviceName = "UserWebService")
@Stateless
public class UserWebService {
    @EJB
    private UserService userService;

    @WebMethod(operationName = "updateUserPosition")
    @Oneway
    public void updateListContact(@WebParam(name = "latitude") double latitude, @WebParam(name = "longitude") double longitude,
                                  @WebParam(name = "username") String username) {
        User user = userService.findUserById(username);
        if (user.getGpsPos() == null) {
            user.setGpsPos(new GpsPosition(user, latitude, longitude));
        } else {
            user.getGpsPos().setLatitude(latitude);
            user.getGpsPos().setLongitude(longitude);
        }
        user.setUpdateTimestamp();
        userService.updateUser(user);
    }
}
