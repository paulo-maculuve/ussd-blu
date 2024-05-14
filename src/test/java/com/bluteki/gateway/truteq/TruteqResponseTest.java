package com.bluteki.gateway.truteq;

import com.bluteki.gateway.FlowMap;
import com.bluteki.gateway.truteq.ussd.RequestUssd;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class TruteqResponseTest {

    @Mock
    private HttpServletResponse response;
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testResponse() throws IOException {
        RequestUssd requestUssd = new RequestUssd("258846568447", "1234323", "2", "Ola");
        TruteqResponse responseHandler = new TruteqResponse();
        String message = "Test message";
        FlowMap type = FlowMap.CONTINUE;

        PrintWriter writer = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(writer);
        responseHandler.response(requestUssd, response, type, message);

        verify(response).setContentType("text/xml");
        verify(response).setCharacterEncoding("UTF-8");
        verify(response.getWriter()).write(anyString());
    }
}
