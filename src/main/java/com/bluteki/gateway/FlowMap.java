package com.bluteki.gateway;

public enum FlowMap {
    CONTINUE("2", "FC"),
    BREAK("3", "FB");
    private final String truteq;
    private final String flares;

    FlowMap(String truteq, String flares) {
        this.truteq = truteq;
        this.flares = flares;
    }
    public String getFlares() {
        return flares;
    }

    public String getTruteq() {
        return truteq;
    }
}
