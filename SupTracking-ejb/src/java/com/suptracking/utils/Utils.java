package com.suptracking.utils;

import com.suptracking.entity.User;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author EPTR
 */
public class Utils {
    public static final String ATT_SESSION = "user";

    public static HttpSession getSession() {
        return (HttpSession)
                FacesContext.
                        getCurrentInstance().
                        getExternalContext().
                        getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.
                getCurrentInstance().
                getExternalContext().getRequest();
    }

    public static User getUserInSession() {
        HttpSession session = getSession();
        if (session != null) {
            return (User) session.getAttribute(ATT_SESSION);
        } else {
            return null;
        }
    }

    public static void setUserInSession(User user) {
        HttpSession session = getSession();
        if (session != null) {
            session.setAttribute(ATT_SESSION, user);
        }
    }

    public static void invalidateSession() {
        getSession().invalidate();
    }

    public static String getDateOnlyFromTimestamp(Long timestamp) {
        Calendar mydate = Calendar.getInstance();
        mydate.setTimeInMillis((long) timestamp * 1000);
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy");
        return format.format(mydate.getTime());
    }

    public static String getDateTimeFromTimestamp(Long timestamp) {
        Calendar mydate = Calendar.getInstance();
        mydate.setTimeInMillis((long) timestamp * 1000);
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH:mm");
        return format.format(mydate.getTime());
    }

    public static Long getCurrentTimestamp() {
        return (Long) (System.currentTimeMillis() / 1000L);
    }

    public static Long convertStringToLong(String s) {
        Long l = null;
        if (s != null) {
            try {
                l = Long.valueOf(s);
            } catch (NumberFormatException e) {
                System.out.println("Error while converting String to Long" + e);
            }
        }
        return l;
    }
}
