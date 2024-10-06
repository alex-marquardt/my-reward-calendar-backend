package org.marquardt.api.model;

import org.marquardt.model.RewardState;
import org.marquardt.model.jpa.Date;
import org.marquardt.model.jpa.Reward;

import java.util.List;

public class RewardResponse {

    private String id;
    private RewardState state;
    private List<DateResponse> dates;

    public RewardResponse(Reward reward) {
        this.id = reward.getId();
        this.state = reward.getState();
        this.dates = getDates(reward.getDates());
    }

    private List<DateResponse> getDates(List<Date> dates) {
        return dates.stream().map(DateResponse::new).toList();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RewardState getState() {
        return state;
    }

    public void setState(RewardState state) {
        this.state = state;
    }

    public List<DateResponse> getDates() {
        return dates;
    }

    public void setDates(List<DateResponse> dates) {
        this.dates = dates;
    }
}
