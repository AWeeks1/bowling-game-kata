package com.jgreubel.bowlinggamekata;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class BowlingGameCLI {

    private int score = 0;

    @ShellMethod("Enter your entire bowling score")
    public String bowl(int score) {
        if (score < 0 || score > 10) {
            return "Invalid Score";
        }

        this.score += score;

        return String.valueOf(this.score);
    }
}
