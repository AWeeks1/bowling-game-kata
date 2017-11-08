package com.jgreubel.bowlinggamekata;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.ArrayList;
import java.util.List;

@ShellComponent
public class BowlingGameCLI {

    private List<Integer> bowls = new ArrayList<>();

    @ShellMethod("Enter the number of pins you knock down per bowl")
    public String bowl(int bowl) {
        if (bowl < 0 || bowl > 10 || isSecondBowlOfFrame(bowls) && getFirstBowlOfFrame(bowls) + bowl > 10) {
            return "Invalid Score";
        }

        bowls.add(bowl);

        if (isSecondBowlOfFrame(bowls) && bowl == 10) {
            bowls.add(null);
        }

        int overallScore = 0;
        for (int bowlingIndex = 0; bowlingIndex < bowls.size(); bowlingIndex++) {
            Integer currentBowl = bowls.get(bowlingIndex);

            if (currentBowl == null) continue;

            if (isFirstBowlOfFrame(bowlingIndex) && isStrike(bowlingIndex, bowls) && hasMoreBowls(bowlingIndex, bowls, 2)) {
                overallScore += currentBowl + getNextBowl(bowlingIndex, bowls, 1) + getNextBowl(bowlingIndex, bowls, 2);
            }
            else if (isSecondBowlOfFrame(bowlingIndex) && isSpare(bowlingIndex, bowls) && hasMoreBowls(bowlingIndex, bowls, 1)) {
                overallScore += currentBowl + getNextBowl(bowlingIndex, bowls, 1);
            }
            else {
                overallScore += currentBowl;
            }
        }

        return String.valueOf(overallScore);
    }

    private boolean isStrike(int bowlingIndex, List<Integer> bowls) {
        return bowls.get(bowlingIndex) == 10;
    }

    private boolean isSpare(int bowlingIndex, List<Integer> bowls) {
        return bowls.get(bowlingIndex - 1) + bowls.get(bowlingIndex) == 10;
    }

    private boolean hasMoreBowls(int bowlingIndex, List<Integer> bowls, int num) {
        while(++bowlingIndex < bowls.size() && num > 0) {
            if (bowls.get(bowlingIndex) != null) {
                num--;
            }
        }

        return num == 0;
    }

    private int getNextBowl(int bowlingIndex, List<Integer> bowls, int num) {
        while(num > 0) {
            if(bowls.get(++bowlingIndex) != null) {
                num --;
            }
        }

        return bowls.get(bowlingIndex);
    }

    private boolean isFirstBowlOfFrame(int bowlingIndex) {
        return bowlingIndex % 2 == 0;
    }

    private boolean isSecondBowlOfFrame(List<Integer> bowls) {
        return isSecondBowlOfFrame(bowls.size());
    }

    private boolean isSecondBowlOfFrame(int bowlingIndex) {
        return bowlingIndex % 2 == 1;
    }

    private Integer getFirstBowlOfFrame(List<Integer> bowls) {
        return bowls.get(bowls.size() - 1);
    }

}
