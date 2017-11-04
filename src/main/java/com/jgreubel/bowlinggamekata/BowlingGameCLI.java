package com.jgreubel.bowlinggamekata;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class BowlingGameCLI {

    @ShellMethod("Enter your entire bowling score")
    public String bowl(int score) {
        if(score < 0 || score > 300) {
            return "Invalid Score";
        }

        return String.valueOf(score);
    }
}
