package com.udesc.model;

import java.time.LocalDateTime;

import com.udesc.io.Viewable;

public class Absence implements Viewable {
    private String        reason;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;

    public Absence(String reason, LocalDateTime startedAt, LocalDateTime finishedAt) {
        this.reason     = reason;
        this.startedAt  = startedAt;
        this.finishedAt = finishedAt;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getStartedAt() {
        return this.startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getFinishedAt() {
        return this.finishedAt;
    }

    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }

    @Override
    public String briefView() {
        return String.format(
            "%s (de %s a %s)",
            reason,
            startedAt, finishedAt);
    }

    @Override
    public String view() {
        return String.format(
            "Motivo      | %s \n" +
            "Data início | %s \n" +
            "Data fim    | %s \n",
            reason,
            startedAt, finishedAt);
    }
}
