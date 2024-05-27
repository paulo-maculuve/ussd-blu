package com.bluteki.gateway.truteq;

import com.bluteki.gateway.FlowMap;
import com.bluteki.gateway.Handler;
import com.bluteki.gateway.Request;
import com.bluteki.gateway.Ussd;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;

@Component
public class TruteqResponse implements Handler<Request> {
    private static final Logger LOGGER = Logger.getLogger(TruteqResponse.class);

    @Override
    public void response(Request request, HttpServletResponse response, FlowMap type, String message) {
        Ussd ussd = prepareUssdObject(request, message, type.getTruteq());
        response.setContentType("text/xml");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(getXMLBuffer(ussd));
        } catch (JAXBException | IOException e) {
            LOGGER.error("Error writing response", e);
        }
        logResponse(request, message, type.getTruteq());
    }

    private Ussd prepareUssdObject(Request request, String message, String type) {
        return new Ussd(request.getMsisdn(), request.getSessionId(), type, message);
    }

    private String getXMLBuffer(Ussd ussd) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Ussd.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter writer = new StringWriter();
        marshaller.marshal(ussd, writer);
        return writer.toString();
    }

    private void logResponse(Request request, String message, String type) {
        LOGGER.info(String.format("MSISDN: %s :: SESSION ID: %s :: Response: %s (Type: %s)",
                request.getMsisdn(), request.getSessionId(), message, type));
    }
}
