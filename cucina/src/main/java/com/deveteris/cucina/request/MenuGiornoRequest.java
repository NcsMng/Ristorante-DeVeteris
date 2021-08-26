package com.deveteris.cucina.request;

public class MenuGiornoRequest {
    private String pietanza;

    public MenuGiornoRequest(String pietanze) {
        this.pietanza = pietanze;
    }

    public MenuGiornoRequest() {
    }

    public String getPietanza() {
        return pietanza;
    }

    public void setPietanza(String pietanza) {
        this.pietanza = pietanza;
    }
}
