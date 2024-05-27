package com.bluteki.gateway.truteq;

import com.bluteki.gateway.FlowMap;
import com.bluteki.gateway.Request;
import com.bluteki.gateway.Ussd;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class TruteqResponseTest {
    private static final Logger LOGGER = Logger.getLogger(TruteqResponseTest.class);

    @Mock
    private HttpServletResponse httpServletResponse;

    @Mock
    private PrintWriter printWriter;

    private TruteqResponse truteqResponse;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        truteqResponse = new TruteqResponse();
    }

    @Test
    public void testResponse() throws Exception {
        Request request = new Request("12345", "session123", "1", "Test message");
        FlowMap type = FlowMap.CONTINUE;
        String message = "Test message";

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        when(httpServletResponse.getWriter()).thenReturn(printWriter);

        truteqResponse.response(request, httpServletResponse, type, message);

        verify(httpServletResponse).setContentType("text/xml");
        verify(httpServletResponse).setCharacterEncoding("UTF-8");

        printWriter.flush();
        String responseString = stringWriter.toString();

        assertTrue(responseString.contains(message));

        // Registra o response no log
        LOGGER.info("Response: " + responseString);
    }
}