package com.jgreubel.bowlinggamekata;

import org.springframework.stereotype.Component;

@Component
class Parser {
    int parseBowlScore(String frameScore, int currentFrameScore) throws InvalidBowlScoreException {
        int intBowlScore;

        try {
            intBowlScore = Integer.parseInt(frameScore);
        } catch (NumberFormatException e) {
            throw new InvalidBowlScoreException();
        }

        if (intBowlScore < 0 || intBowlScore + currentFrameScore > 10) {
            throw new InvalidBowlScoreException();
        }

        return intBowlScore;
    }
}