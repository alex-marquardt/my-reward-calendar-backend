package org.marquardt.api.model;

import org.marquardt.model.RewardState;
import org.marquardt.model.RewardType;
import org.marquardt.model.jpa.Reward;

public class RewardResponse {

    private String id;
    private RewardType type;
    private RewardState state;

    public RewardResponse(Reward reward) {
        this.id = reward.getId();
        this.type = reward.getType();
        this.state = reward.getState();
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
