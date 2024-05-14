package com.bluteki.gateway;

import com.bluteki.gateway.truteq.TruteqResponse;
import jakarta.servlet.http.HttpServletResponse;

public interface Handler <T extends Request>{

    public abstract void response(T requestUssd, HttpServletResponse response, FlowMap type, String message);
}
