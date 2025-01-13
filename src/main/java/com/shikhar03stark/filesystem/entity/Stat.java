package com.shikhar03stark.filesystem.entity;

import java.time.LocalDateTime;

public class Stat {
    private boolean isTerminal;
    private LocalDateTime createdAt;
    // perms
    private byte[] data; // can be encapsulated

    public Stat() {
        createdAt = LocalDateTime.now();
        data = null;
        isTerminal = false;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public byte[] getData() {
        return data;
    }

    public boolean isTerminal() {
        return isTerminal;
    }

    public void setTerminal(boolean terminal) {
        isTerminal = terminal;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public Stat clone() {
        final Stat clonedSat = new Stat();
        clonedSat.setData(data);
        clonedSat.setTerminal(isTerminal);
        return clonedSat;
    }
}
