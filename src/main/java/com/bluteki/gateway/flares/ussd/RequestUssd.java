package com.bluteki.gateway.flares.ussd;

import com.bluteki.gateway.Request;

public class RequestUssd extends Request {
    private String language;
    private String shortCode;

    public RequestUssd() {
    }

    public RequestUssd(String msisdn, String sessionId, String requestType, String message, String language, String shortCode) {
        super(msisdn, sessionId, requestType, message);
        this.language = language;
        this.shortCode = shortCode;
    }

    public String getLanguage() {
        return language;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setLanguage(String language) {
    }

    public void setShortCode(String shortcode) {
    }
}
