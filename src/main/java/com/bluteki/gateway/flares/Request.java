package com.bluteki.gateway.flares;

import com.bluteki.gateway.flares.ussd.RequestUssd;
import jakarta.servlet.http.HttpServletRequest;

public class Request {
    public RequestUssd parseHttpRequest(HttpServletRequest request) {

        RequestUssd appRequest = new RequestUssd();
        if (request != null) {
            if (nullcheck(request.getParameter("msisdn"))) {
                appRequest.setMsisdn(request.getParameter("msisdn").trim());
            }

            if (nullcheck(request.getParameter("language"))) {
                appRequest.setLanguage(request.getParameter("language").trim());
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

            if (nullcheck(request.getParameter("shortcode"))) {
                appRequest.setShortCode(request.getParameter("shortcode").trim());
            }
        }

        return appRequest;
    }
    public boolean nullcheck(String value) {
        return value != null && !value.isEmpty() && value.length() != 0;
    }

}
