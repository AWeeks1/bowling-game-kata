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
    public void playerCanRecordTenBowlingFrameScores() {
        assertThat(shell.evaluate(() -> "record 0")).isEqualTo("Your score after 1 frames is 0! Keep it up!");
        assertThat(shell.evaluate(() -> "record 10")).isEqualTo("Your score after 2 frames is 10! Keep it up!");
        assertThat(shell.evaluate(() -> "record 6")).isEqualTo("Your score after 3 frames is 16! Keep it up!");
        assertThat(shell.evaluate(() -> "record 2")).isEqualTo("Your score after 4 frames is 18! Keep it up!");
        assertThat(shell.evaluate(() -> "record 2")).isEqualTo("Your score after 5 frames is 20! Keep it up!");
        assertThat(shell.evaluate(() -> "record 9")).isEqualTo("Your score after 6 frames is 29! Keep it up!");
        assertThat(shell.evaluate(() -> "record 10")).isEqualTo("Your score after 7 frames is 39! Keep it up!");
        assertThat(shell.evaluate(() -> "record 0")).isEqualTo("Your score after 8 frames is 39! Keep it up!");
        assertThat(shell.evaluate(() -> "record 1")).isEqualTo("Your score after 9 frames is 40! Keep it up!");
        assertThat(shell.evaluate(() -> "record 3")).isEqualTo("Game Over! Your bowling score is 43! Well done!");
    }

    @Test
    public void displaysErrorMessageWhenRecordingInvalidScorePerFrame() {
        assertThat(shell.evaluate(() -> "record -1")).isEqualTo("Please enter a valid bowling frame score");
        assertThat(shell.evaluate(() -> "record 11")).isEqualTo("Please enter a valid bowling frame score");
        assertThat(shell.evaluate(() -> "record 2000")).isEqualTo("Please enter a valid bowling frame score");
        assertThat(shell.evaluate(() -> "record 70.5")).isEqualTo("Please enter a valid bowling frame score");
        assertThat(shell.evaluate(() -> "record cheese")).isEqualTo("Please enter a valid bowling frame score");
    }
}
