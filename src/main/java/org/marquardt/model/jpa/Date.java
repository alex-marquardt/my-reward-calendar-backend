package org.marquardt.model.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.marquardt.model.DateState;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Date {
    @Id
    @Column(name = "ID")
    String id;
    @Column(name = "DATE")
    LocalDate date;
    @Column(name = "STATE")
    DateState state;

    public Date() {
        this.id = UUID.randomUUID().toString();
        this.date = LocalDate.now();
        this.state = DateState.UNKNOWN;
    }

    public Date(LocalDate date, DateState state) {
        this.id = UUID.randomUUID().toString();
        this.date = date;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public DateState getState() {
        return state;
    }

    public void setState(DateState state) {
        this.state = state;
    }
}
