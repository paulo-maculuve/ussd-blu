package com.bluteki.gateway.flares;

import com.bluteki.gateway.FlowMap;
import com.bluteki.gateway.flares.ussd.RequestUssd;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

public class FlaresResponseTest {
    @Mock
    private HttpServletResponse response;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testResponse() throws IOException {
        RequestUssd requestUssd = new RequestUssd("258846568447", "1234323", "FC", "Ola", "pt", "*123#");
        FlaresResponse responseHandler = new FlaresResponse();
        String message = "Test message";
        FlowMap type = FlowMap.CONTINUE;

        responseHandler.response(requestUssd, response, type, message);

        verify(response).setHeader("Freeflow", type.getFlares());
        verify(response).setCharacterEncoding("UTF-8");
    }
}
