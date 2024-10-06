package org.marquardt.service;

import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.marquardt.model.DateState;
import org.marquardt.model.RewardType;
import org.marquardt.model.jpa.Date;
import org.marquardt.model.jpa.DateRepository;
import org.marquardt.model.jpa.Reward;
import org.marquardt.model.jpa.RewardRepository;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class RewardBuilder {

    @Inject
    DateRepository dateRepository;
    @Inject
    RewardRepository rewardRepository;

    @Transactional
    public void buildRewards() {
        List<Date> dates = dateRepository.list("state", Sort.by("date"), DateState.SUCCESS);

        List<Date> stackedDates = new ArrayList<>();

        for (Date date : dates) {
            stackedDates.add(date);
            Reward reward = new Reward(RewardType.SMALL, stackedDates);
            rewardRepository.persist(reward);
            stackedDates.clear();
        }
    }
}
