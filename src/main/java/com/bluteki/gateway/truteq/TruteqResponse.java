package com.bluteki.gateway.truteq;

import com.bluteki.gateway.FlowMap;
import com.bluteki.gateway.Handler;
import com.bluteki.gateway.Ussd;
import com.bluteki.gateway.truteq.ussd.RequestUssd;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.StringWriter;

public class TruteqResponse implements Handler<RequestUssd> {

    private static final Logger LOGGER = Logger.getLogger(TruteqResponse.class);
    @Override
    public void response(RequestUssd requestUssd, HttpServletResponse response, FlowMap type, String message) {
        Ussd ussd = prepareUssdObject(requestUssd, message, type.getTruteq());
        response.setContentType("text/xml");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(getXMLBuffer(ussd));
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        logResponse(requestUssd, message, type.getTruteq());

    }

    private Ussd prepareUssdObject(RequestUssd gatewayRequest, String message, String type) {
        Ussd ussd = new Ussd();
        ussd.setMsisdn(gatewayRequest.getMsisdn());
        ussd.setSessionId(gatewayRequest.getSessionId());
        ussd.setMsg(message);
        ussd.setType(type);
        return ussd;
    }

    private String getXMLBuffer(Ussd ussd) throws JAXBException {
        JAXBContext contextObj = JAXBContext.newInstance(Ussd.class);
        Marshaller marshallerObj = contextObj.createMarshaller();

        marshallerObj.setProperty("jaxb.formatted.output", true);
        StringWriter writer = new StringWriter();
        marshallerObj.marshal(ussd, writer);
        return writer.toString();
    }

    private void logResponse(RequestUssd gatewayRequest, String message, String type) {
        LOGGER.info("MSISDN:" + gatewayRequest.getMsisdn() + "::" + "SESSION ID:" + gatewayRequest.getSessionId() + "::" + "Response:" + message + " (Type: " + type + ")");
    }
}
