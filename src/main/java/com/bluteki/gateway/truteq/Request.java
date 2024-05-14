package com.bluteki.gateway.truteq;

import com.bluteki.gateway.truteq.ussd.RequestUssd;
import jakarta.servlet.http.HttpServletRequest;

public class Request {

    public RequestUssd httpResques(HttpServletRequest request) {
        RequestUssd appRequest = new RequestUssd();
        if (request != null) {
            if (nullcheck(request.getParameter("msisdn"))) {
                appRequest.setMsisdn(request.getParameter("msisdn").trim());
            }

            if (nullcheck(request.getParameter("sessionid"))) {
                appRequest.setSessionId(request.getParameter("sessionid").trim());
            }

            if (nullcheck(request.getParameter("type"))) {
                appRequest.setRequestType(request.getParameter("type").trim());
            }

            if (nullcheck(request.getParameter("msg"))) {
                appRequest.setMessage(request.getParameter("msg").trim());
            }
        }

        return appRequest;
    }

    public boolean nullcheck(String value) {
        return value != null && !value.isEmpty() && value.length() != 0;
    }

}
