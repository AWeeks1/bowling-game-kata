package com.jgreubel.bowlinggamekata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.ArrayList;
import java.util.List;

import static com.jgreubel.bowlinggamekata.BowlingUtils.isStrike;

@ShellComponent
public class BowlingGameCLI {

    private List<Integer> bowls = new ArrayList<>();
    private boolean isSecondBowlOfFrame = false;

    private final ScoreCalculator scoreCalculator;

    @Autowired
    public BowlingGameCLI(ScoreCalculator scoreCalculator) {
        this.scoreCalculator = scoreCalculator;
    }

    @ShellMethod("Enter the number of pins you knock down per bowl")
    public String bowl(int bowl) {
        if (bowl < 0 || bowl > 10 || isSecondBowlOfFrame && getFirstBowlOfFrame(bowls) + bowl > 10) {
            return "Invalid Score";
        }

        bowls.add(bowl);

        isSecondBowlOfFrame = !isSecondBowlOfFrame && !isStrike(bowl);

        return String.valueOf(scoreCalculator.calc(bowls));
    }

    private Integer getFirstBowlOfFrame(List<Integer> bowls) {
        return bowls.get(bowls.size() - 1);
    }

}
