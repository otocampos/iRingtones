package com.gpaddy.ringtones.iphone;

import java.io.Serializable;

public class S1ObjectMusic implements Serializable {

    private String name;
    private boolean status;

    public S1ObjectMusic(String name) {
        this.name = name;
        status = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
