package com.jgreubel.bowlinggamekata;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class BowlingGameCLI {

    private int frame = 1;
    private int score = 0;

    @ShellMethod("Record your bowling score per frame!")
    public String record(String frameScore) {
        try {
            score += parseFrameScore(frameScore);
        } catch (InvalidFrameScoreException e) {
            return "Please enter a valid bowling frame score";
        }

        if (frame < 10) {
            return String.format("Your score after %d frames is %d! Keep it up!", frame++, score);
        } else {
            return String.format("Game Over! Your bowling score is %d! Well done!", score);
        }

    }

    private int parseFrameScore(String frameScore) throws InvalidFrameScoreException {
        int intFrameScore;

        try {
            intFrameScore = Integer.parseInt(frameScore);
        } catch (NumberFormatException e) {
            throw new InvalidFrameScoreException();
        }

        if (intFrameScore < 0 || intFrameScore > 10) {
            throw new InvalidFrameScoreException();
        }

        return intFrameScore;
    }

    private class InvalidFrameScoreException extends Exception {}

}
