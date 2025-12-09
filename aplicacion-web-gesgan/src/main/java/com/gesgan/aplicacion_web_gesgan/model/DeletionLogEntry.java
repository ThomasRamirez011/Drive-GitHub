package com.gesgan.aplicacion_web_gesgan.model;

import java.time.LocalDateTime;

public class DeletionLogEntry {
    private String din;
    private LocalDateTime deletionTime;

    public DeletionLogEntry() {}

    public DeletionLogEntry(String din, LocalDateTime deletionTime) {
        this.din = din;
        this.deletionTime = deletionTime;
    }

    public String getDin() {
        return din;
    }

    public void setDin(String din) {
        this.din = din;
    }

    public LocalDateTime getDeletionTime() {
        return deletionTime;
    }

    public void setDeletionTime(LocalDateTime deletionTime) {
        this.deletionTime = deletionTime;
    }
}
