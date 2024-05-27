package com.bluteki.gateway.flares;

import com.bluteki.gateway.FlowMap;
import com.bluteki.gateway.Request;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class FlaresResponseTest {
    private static final Logger LOGGER = Logger.getLogger(FlaresResponseTest.class);
    @Mock
    private HttpServletResponse httpServletResponse;


    private FlaresResponse flaresResponse;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        flaresResponse = new FlaresResponse();
    }

    @Test
    public void testResponse() throws Exception {
        Request request = new Request("12345", "session123", "1", "Test message");
        FlowMap type = FlowMap.CONTINUE;
        String message = "Test message";

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        when(httpServletResponse.getWriter()).thenReturn(printWriter);

        flaresResponse.response(request, httpServletResponse, type, message);

        verify(httpServletResponse).setHeader("Freeflow", type.getFlares());
        verify(httpServletResponse).setHeader("Content-Type", "UTF-8");
        verify(httpServletResponse).setCharacterEncoding("UTF-8");

        printWriter.flush();
        String responseString = stringWriter.toString();

        assertTrue(responseString.contains(message));

        LOGGER.info("Response: " + responseString);

    }
}
