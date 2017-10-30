package com.jgreubel.bowlinggamekata;

import com.jgreubel.bowlinggamekata.helpers.TestApplicationRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.shell.Shell;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(TestApplicationRunner.class)
public class RecordScoreTest {

    @Autowired
    private Shell shell;

    @Test
    public void playerCanRecordBowlingGameScore() {
        assertThat(shell.evaluate(() -> "record 0")).isEqualTo("Game Over! Your bowling score is 0! Well done!");
        assertThat(shell.evaluate(() -> "record 7")).isEqualTo("Game Over! Your bowling score is 7! Well done!");
        assertThat(shell.evaluate(() -> "record 300")).isEqualTo("Game Over! Your bowling score is 300! Well done!");
    }

    @Test
    public void displaysErrorMessageWhenRecordingInvalidBowlingGameScore() {
        assertThat(shell.evaluate(() -> "record -1")).isEqualTo("Please enter a valid bowling game score");
        assertThat(shell.evaluate(() -> "record 305")).isEqualTo("Please enter a valid bowling game score");
        assertThat(shell.evaluate(() -> "record 70.5")).isEqualTo("Please enter a valid bowling game score");
        assertThat(shell.evaluate(() -> "record cheese")).isEqualTo("Please enter a valid bowling game score");
    }
}
