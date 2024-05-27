package com.bluteki.gateway.flares;

import com.bluteki.gateway.Request;
import jakarta.servlet.http.HttpServletRequest;

public class FlaresRequest {
    public Request parseHttpRequest(HttpServletRequest request) {

        Request appRequest = new Request();
        if (request != null) {
            if (nullcheck(request.getParameter("msisdn"))) {
                appRequest.setMsisdn(request.getParameter("msisdn").trim());
            }

            if (nullcheck(request.getParameter("msg"))) {
                appRequest.setMessage(request.getParameter("msg").trim());
            }

            if (nullcheck(request.getParameter("newrequest"))) {
                appRequest.setRequestType(request.getParameter("newrequest").trim());
            }

            if (nullcheck(request.getParameter("sessionid"))) {
                appRequest.setSessionId(request.getParameter("sessionid").trim());
            }

        }

        return appRequest;
    }

    public boolean nullcheck(String value) {
        return value != null && !value.isEmpty() && value.length() != 0;
    }

}
