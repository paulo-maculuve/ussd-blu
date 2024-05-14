package com.bluteki.gateway;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SessionHandler {
    public static final Map<String, Request> sessionInfoMap = Collections
            .synchronizedMap(new HashMap<String, Request>());

    public SessionHandler() {
    }

    public static Request getSessionInfo(String msisdn) {
        return sessionInfoMap.get(msisdn);
    }

    public static boolean isMsisdnExists(String msisdn) {
        return sessionInfoMap.containsKey(msisdn);
    }

    public static void putSessionInfo(String msisdn, Request request) {
        synchronized (sessionInfoMap) {
            sessionInfoMap.put(msisdn, request);
        }
    }

    public static Request removeSessionInfo(String msisdn) {
        Request request = null;
        synchronized (sessionInfoMap) {
            request = sessionInfoMap.remove(msisdn);
            return request;
        }
    }
}
