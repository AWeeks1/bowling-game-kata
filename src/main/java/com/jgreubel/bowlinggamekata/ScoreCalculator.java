package com.jgreubel.bowlinggamekata;

import org.springframework.stereotype.Component;

import java.util.List;

import static com.jgreubel.bowlinggamekata.BowlingUtils.*;

@Component
class ScoreCalculator {

    int calc(List<Integer> bowls) {
        int overallScore = 0;

        for (int bowlingIndex = 0; bowlingIndex < bowls.size(); bowlingIndex++) {
            Integer firstBowl = bowls.get(bowlingIndex);
            overallScore += firstBowl;

            if (isLastBowl(bowlingIndex, bowls)) {
                break;
            }

            if (isStrike(firstBowl)) {
                if (hasMoreBowls(bowlingIndex, bowls, 2)) {
                    overallScore += bowls.get(bowlingIndex + 1) + bowls.get(bowlingIndex + 2);
                }

                continue;
            }

            bowlingIndex++;
            Integer secondBowl = bowls.get(bowlingIndex);
            overallScore += secondBowl;

            if (isSpare(firstBowl, secondBowl) && hasMoreBowls(bowlingIndex, bowls, 1)) {
                overallScore += bowls.get(bowlingIndex + 1);
            }
        }

        return overallScore;
    }

    private boolean isLastBowl(int bowlingIndex, List<Integer> bowls) {
        return !hasMoreBowls(bowlingIndex, bowls, 1);
    }

    private boolean hasMoreBowls(int bowlingIndex, List<Integer> bowls, int num) {
        return bowlingIndex + num < bowls.size();
    }

}