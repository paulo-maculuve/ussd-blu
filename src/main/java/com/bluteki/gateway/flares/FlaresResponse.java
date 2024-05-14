package com.bluteki.gateway.flares;

import com.bluteki.gateway.FlowMap;
import com.bluteki.gateway.Handler;
import com.bluteki.gateway.flares.ussd.RequestUssd;
import com.bluteki.gateway.truteq.TruteqResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class FlaresResponse implements Handler<RequestUssd> {
    private static final Logger LOGGER = Logger.getLogger(FlaresResponse.class);

    @Override
    public void response(RequestUssd requestUssd, HttpServletResponse response, FlowMap type, String message) {
        response.setHeader("Freeflow", type.getFlares());
        response.setHeader("Content-Type", "UTF-8");
        response.setCharacterEncoding("UTF-8");

        logResponse(requestUssd, message, type.getFlares());
    }

    private void logResponse(RequestUssd gatewayRequest, String message, String type) {
        LOGGER.info("MSISDN:" + gatewayRequest.getMsisdn() + "::" + "SESSION ID:" + gatewayRequest.getSessionId() + "::" + "Response:" + message + " (Type: " + type + ")");
    }
}
