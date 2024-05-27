package com.bluteki.gateway.truteq;
import com.bluteki.gateway.Request;
import jakarta.servlet.http.HttpServletRequest;

public class TruteqRequest {

    public com.bluteki.gateway.Request httpResques(HttpServletRequest request) {
        Request appRequest = new Request();
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
