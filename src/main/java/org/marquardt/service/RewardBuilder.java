package org.marquardt.service;

import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.marquardt.model.DateState;
import org.marquardt.model.RewardType;
import org.marquardt.model.jpa.Date;
import org.marquardt.model.jpa.DateRepository;
import org.marquardt.model.jpa.Reward;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class RewardBuilder {

    @ConfigProperty(name = "big.reward.value")
    Integer bigRewardValue;
    @ConfigProperty(name = "medium.reward.value")
    Integer mediumRewardValue;
    @ConfigProperty(name = "small.reward.value")
    Integer smallRewardValue;

    @Inject
    DateRepository dateRepository;

    public List<Reward> buildRewards() {
        List<Date> dates = dateRepository.list("state", Sort.by("date"), DateState.SUCCESS);

        List<Reward> rewards = new ArrayList<>();
        List<Date> datesInStrike = new ArrayList<>();
        Date current = null;

        for (Date date : dates) {
            // set start date
            if (current == null) {
                datesInStrike.add(current);
                current = date;
                continue;
            }

            // check if strike is broken
            if (!current.getDate().plusDays(1).isEqual(date.getDate())) {
                checkForReward(rewards, datesInStrike);
                datesInStrike.clear();
            }

            // add date to strike and set new current date
            datesInStrike.add(current);
            current = date;

            // check for max reward or
            if (datesInStrike.size() == bigRewardValue || date.getDate().isEqual(dates.getLast().getDate())) {
                checkForReward(rewards, datesInStrike);
                datesInStrike.clear();
            }
        }
        return rewards;
    }

    private void checkForReward(List<Reward> rewards, List<Date> stackedDates) {
        if (stackedDates.size() == bigRewardValue) {
            rewards.add(new Reward(RewardType.BIG));
        } else if (stackedDates.size() == mediumRewardValue) {
            rewards.add(new Reward(RewardType.MEDIUM));
        } else if (stackedDates.size() == smallRewardValue) {
            rewards.add(new Reward(RewardType.SMALL));
        }
    }
}
