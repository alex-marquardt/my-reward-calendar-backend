package org.marquardt.jpa;

import jakarta.persistence.*;
import org.marquardt.model.DateState;
import org.marquardt.model.ingoing.DateRequest;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "DATE")
public class Date {

    @Id
    @Column(name = "ID", nullable = false)
    private String id;

    @Column(name = "DATE", nullable = false)
    private LocalDate date;

    @Column(name = "STATE", nullable = false)
    private DateState state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REWARD")
    private Reward reward;

    public Date() {
    }

    public Date(DateRequest dateRequest) {
        this.id = UUID.randomUUID().toString();
        this.date = dateRequest.getDate();
        this.state = dateRequest.getState();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public DateState getState() {
        return state;
    }

    public void setState(DateState state) {
        this.state = state;
    }

    public Reward getReward() {
        return reward;
    }

    public void setReward(Reward reward) {
        this.reward = reward;
    }
}
