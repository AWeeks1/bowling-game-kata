package com.jgreubel.bowlinggamekata;

class Frame {

    private Integer firstBowl;
    private Integer secondBowl;

    Frame() {}

    Frame(Integer firstBowl, Integer secondBowl) {
        this.firstBowl = firstBowl;
        this.secondBowl = secondBowl;
    }

    Integer getFirstBowl() {
        return firstBowl;
    }

    Integer getSecondBowl() {
        return secondBowl;
    }

    void addBowl(int bowl) {
        if(firstBowl == null) {
            firstBowl = bowl;
        }
        else if (secondBowl == null) {
            secondBowl = bowl;
        }
    }

    boolean isCompleted() {
        return (firstBowl != null && secondBowl != null) || (firstBowl != null && firstBowl == 10);
    }

    int getFrameScore() {
        int total = 0;

        if(firstBowl != null) {
            total += firstBowl;
        }

        if(secondBowl != null) {
            total += secondBowl;
        }

        return total;
    }

}
