package com.jgreubel.bowlinggamekata;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

        int overallScore = bowls.stream()
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue)
                .sum();

        return String.valueOf(overallScore);
    }

    private boolean isSecondBowlOfFrame(List<Integer> bowls) {
        return bowls.size() % 2 == 1;
    }

    private Integer getFirstBowlOfFrame(List<Integer> bowls) {
        return bowls.get(bowls.size() - 1);
    }

}
