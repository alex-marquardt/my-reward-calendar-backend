package org.marquardt.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.marquardt.model.DateState;
import org.marquardt.jpa.Date;
import org.marquardt.jpa.DateRepository;
import org.marquardt.jpa.Reward;
import org.marquardt.jpa.RewardRepository;

import java.time.LocalDate;
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
        Date latestDateWithReward = dateRepository.getLatestDateWithReward();
        LocalDate fromDate = latestDateWithReward != null ? latestDateWithReward.getDate() : LocalDate.EPOCH;

        List<Date> dates = dateRepository.getDatesFromSpecificDate(fromDate);
        if (validateDates(dates)) {
            checkForRewards(dates);
        }
    }

    private void checkForRewards(List<Date> dates) {
        List<Date> rewardDates = new ArrayList<>();
        for (Date date : dates) {
            if (date.getState().equals(DateState.SUCCESS)) {
                rewardDates.add(date);
            } else if (date.getState().equals(DateState.FAIL)) {
                saveReward(rewardDates);
                rewardDates.clear();
            }
        }
    }

    private boolean validateDates(List<Date> dates) {
        for (int index = 1; index < dates.size(); index++) {
            Date currentDate = dates.get(index);
            Date previousDate = dates.get(index - 1);

            if (!previousDate.getDate().plusDays(1).isEqual(currentDate.getDate())) {
                return previousDate.getDate().plusDays(1).isEqual(currentDate.getDate());
            }
        }
        return true;
    }

    public void saveReward(List<Date> dates) {
        if (!dates.isEmpty()) {
            rewardRepository.persist(new Reward(dates));
        }
    }
}
