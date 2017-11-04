package com.jgreubel.bowlinggamekata;

import org.springframework.stereotype.Component;

import java.util.Stack;
import java.util.stream.IntStream;

@Component
class Displayer {

    String getFrameDisplay(Stack<Frame> frames) {
        int total = frames.stream().mapToInt(Frame::getFrameScore).sum();
        StringBuilder frameDisplay = new StringBuilder("|");

        IntStream.range(0, 10).forEach(frameIndex -> {
            if (frameIndex < frames.size()) {
                Frame frame = frames.get(frameIndex);

                String firstBowlDisplay = getFirstBowlDisplay(frame.getFirstBowl());
                String secondBowlDisplay = getSecondBowlDisplay(frame.getSecondBowl(), frame.getFrameScore());

                frameDisplay.append(firstBowlDisplay).append(secondBowlDisplay).append("|");
            } else {
                frameDisplay.append("  |");
            }
        });

        frameDisplay.append(String.format("Total:%3d|", total));
        return frameDisplay.toString();
    }

    private String getFirstBowlDisplay(Integer firstBowl) {
        if (firstBowl == null) {
            return " ";
        } else {
            if (firstBowl == 10) {
                return "X";
            } else {
                return firstBowl.toString();
            }
        }
    }

    private String getSecondBowlDisplay(Integer secondBowl, int frameScore) {
        if (secondBowl == null) {
            return " ";
        } else {
            if (frameScore == 10) {
                return "/";
            } else {
                return secondBowl.toString();
            }
        }
    }
}