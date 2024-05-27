package com.bluteki.gateway.ussd;

import com.bluteki.gateway.Handler;
import com.bluteki.gateway.Request;
import jakarta.servlet.http.HttpServletResponse;
import org.mockito.Mock;

public class TesterUssd {
    @Mock
    private Handler<Request> flaresResponseHandler;

    @Mock
    private Handler<Request> truteqResponseHandler;

    @Mock
    private HttpServletResponse httpServletResponse;
}
