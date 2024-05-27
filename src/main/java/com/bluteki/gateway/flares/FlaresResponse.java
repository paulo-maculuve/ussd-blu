package com.bluteki.gateway.flares;

import com.bluteki.gateway.FlowMap;
import com.bluteki.gateway.Handler;
import com.bluteki.gateway.Request;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FlaresResponse implements Handler<Request> {
    private static final Logger LOGGER = Logger.getLogger(FlaresResponse.class);

    @Override
    public void response(Request request, HttpServletResponse response, FlowMap type, String message) {
        response.setHeader("Freeflow", type.getFlares());
        response.setHeader("Content-Type", "UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(message);
        } catch (IOException e) {
            LOGGER.error("Error writing response", e);
        }
        logResponse(request, message, type.getFlares());
    }

    private void logResponse(Request request, String message, String type) {
        LOGGER.info(String.format("MSISDN: %s :: SESSION ID: %s :: Response: %s (Type: %s)",
                request.getMsisdn(), request.getSessionId(), message, type));
    }
}
