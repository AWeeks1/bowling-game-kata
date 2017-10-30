package com.jgreubel.bowlinggamekata;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class BowlingGameCLI {

    @ShellMethod("Record your entire bowling game score!")
    public String record(String score) {
        if(!isValidScore(score)) {
            return "Please enter a valid bowling game score";
        }

        return String.format("Game Over! Your bowling score is %s! Well done!", score);
    }

    private boolean isValidScore(String score) {
        int intScore;

        try {
            intScore = Integer.parseInt(score);
        } catch (NumberFormatException e) {
            return false;
        }

        return intScore >= 0 && intScore <= 300;
    }

}
