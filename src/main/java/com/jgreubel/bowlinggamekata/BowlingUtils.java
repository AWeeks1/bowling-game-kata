package com.jgreubel.bowlinggamekata;

class BowlingUtils {

    static boolean isStrike(int bowl) {
        return bowl == 10;
    }

    static boolean isSpare(int firstBowl, int secondBowl) {
        return firstBowl + secondBowl == 10;
    }

}