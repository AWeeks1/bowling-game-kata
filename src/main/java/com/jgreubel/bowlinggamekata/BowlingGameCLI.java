package com.jgreubel.bowlinggamekata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Stack;

@ShellComponent
public class BowlingGameCLI {

    private Stack<Frame> frames = new Stack<>();

    private final Parser parser;
    private final Displayer displayer;

    @Autowired
    public BowlingGameCLI(Parser parser, Displayer displayer) {
        this.parser = parser;
        this.displayer = displayer;
    }

    @ShellMethod("Record your bowling score per frame!")
    public String record(String bowlScore) {
        Frame currentFrame = getCurrentFrame(frames);

        try {
            currentFrame.addBowl(parser.parseBowlScore(bowlScore, currentFrame.getFrameScore()));
        } catch (InvalidBowlScoreException e) {
            return "Please enter a valid bowl score!";
        }

        String frameDisplay = displayer.getFrameDisplay(frames);

        if (frames.size() == 10) {
            frames = new Stack<>();
            frameDisplay = "Game Over! " + frameDisplay;
        }

        return frameDisplay;
    }

    private Frame getCurrentFrame(Stack<Frame> frames) {
        if(frames.empty() || frames.peek().isCompleted()) {
            frames.push(new Frame());
        }

        return frames.peek();
    }

}
