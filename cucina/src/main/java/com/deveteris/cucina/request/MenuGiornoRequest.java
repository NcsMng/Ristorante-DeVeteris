package com.deveteris.cucina.request;

import java.util.Set;


public class MenuGiornoRequest {
    private Set<String> pietanze;

    public MenuGiornoRequest(Set<String> pietanze) {
        this.pietanze = pietanze;
    }

    public MenuGiornoRequest() {
    }

    public Set<String> getPietanze() {
        return pietanze;
    }

    public void setPietanze(Set<String> pietanze) {
        this.pietanze = pietanze;
    }
}
