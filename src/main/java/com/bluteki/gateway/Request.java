package com.bluteki.gateway;

public class Request {
    private String msisdn;
    private String sessionId;
    private String requestType;
    private String message;

    public Request(){}
    public Request(String msisdn, String sessionId, String requestType, String message) {
        this.msisdn = msisdn;
        this.sessionId = sessionId;
        this.requestType = requestType;
        this.message = message;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
