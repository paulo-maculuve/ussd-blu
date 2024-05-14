package com.bluteki.gateway.truteq.ussd;

import com.bluteki.gateway.Request;

public class RequestUssd extends Request {
    public RequestUssd() {
    }

    public RequestUssd(String msisdn, String sessionId, String requestType, String message) {
        super(msisdn, sessionId, requestType, message);
    }
}
