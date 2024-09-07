package org.marquardt.model.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import org.marquardt.model.RewardState;
import org.marquardt.model.RewardType;

import java.util.UUID;

@Entity
public class Reward {
    @Id
    @Column(name = "ID")
    String id;
    @Column(name = "TYPE")
    RewardType type;
    @Column(name = "STATE")
    RewardState state;

    public Reward() {
        this.type = RewardType.SMALL;
        this.state = RewardState.USABLE;
    }

    public Reward(RewardType rewardType) {
        this.type = rewardType;
        this.state = RewardState.USABLE;
    }

    @PrePersist
    public void onPrePersist(){
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RewardType getType() {
        return type;
    }

    public void setType(RewardType type) {
        this.type = type;
    }

    public RewardState getState() {
        return state;
    }

    public void setState(RewardState state) {
        this.state = state;
    }
}
