package com.jgreubel.bowlinggamekata;

import org.springframework.stereotype.Component;

import java.util.List;

import static com.jgreubel.bowlinggamekata.BowlingUtils.*;

@Component
class ScoreCalculator {

    int calc(List<Integer> bowls) {
        int overallScore = 0;

        for (int bowlIndex = 0, frame = 0; bowlIndex < bowls.size() && frame < 10; bowlIndex++, frame++) {
            Integer firstBowl = bowls.get(bowlIndex);
            overallScore += firstBowl;

            if (isLastBowl(bowlIndex, bowls)) {
                break;
            }

            if (isStrike(firstBowl)) {
                if (hasMoreBowls(bowlIndex, bowls, 2)) {
                    overallScore += bowls.get(bowlIndex + 1) + bowls.get(bowlIndex + 2);
                }

                continue;
            }

            bowlIndex++;
            Integer secondBowl = bowls.get(bowlIndex);
            overallScore += secondBowl;

            if (isSpare(firstBowl, secondBowl) && hasMoreBowls(bowlIndex, bowls, 1)) {
                overallScore += bowls.get(bowlIndex + 1);
            }
        }

        return overallScore;
    }

    private boolean isLastBowl(int bowlIndex, List<Integer> bowls) {
        return !hasMoreBowls(bowlIndex, bowls, 1);
    }

    private boolean hasMoreBowls(int bowlIndex, List<Integer> bowls, int num) {
        return bowlIndex + num < bowls.size();
    }

}