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

    //Strike on first bowl
    //Strike on second bowl
    //Spare
    //Start game over again
    //Three digit score display?
    //Invalid bowls due to previous bowl
    // - next bowl works fine

    @Test
    public void playerCanRecordTenBowlingFramesWithTwoBowlsPerFrame() {
        assertThat(shell.evaluate(() -> "record 4")).isEqualTo("|4 |  |  |  |  |  |  |  |  |  |Total:  4|");
        assertThat(shell.evaluate(() -> "record 5")).isEqualTo("|45|  |  |  |  |  |  |  |  |  |Total:  9|");
        assertThat(shell.evaluate(() -> "record 10")).isEqualTo("|45|X |  |  |  |  |  |  |  |  |Total: 19|");
        assertThat(shell.evaluate(() -> "record 7")).isEqualTo("|45|X |7 |  |  |  |  |  |  |  |Total: 26|");
        assertThat(shell.evaluate(() -> "record 3")).isEqualTo("|45|X |7/|  |  |  |  |  |  |  |Total: 29|");
        assertThat(shell.evaluate(() -> "record 0")).isEqualTo("|45|X |7/|0 |  |  |  |  |  |  |Total: 29|");
        assertThat(shell.evaluate(() -> "record 0")).isEqualTo("|45|X |7/|00|  |  |  |  |  |  |Total: 29|");
        assertThat(shell.evaluate(() -> "record 0")).isEqualTo("|45|X |7/|00|0 |  |  |  |  |  |Total: 29|");
        assertThat(shell.evaluate(() -> "record 10")).isEqualTo("|45|X |7/|00|0/|  |  |  |  |  |Total: 39|");
        assertThat(shell.evaluate(() -> "record 1")).isEqualTo("|45|X |7/|00|0/|1 |  |  |  |  |Total: 40|");
        assertThat(shell.evaluate(() -> "record 2")).isEqualTo("|45|X |7/|00|0/|12|  |  |  |  |Total: 42|");
        assertThat(shell.evaluate(() -> "record 6")).isEqualTo("|45|X |7/|00|0/|12|6 |  |  |  |Total: 48|");
        assertThat(shell.evaluate(() -> "record 1")).isEqualTo("|45|X |7/|00|0/|12|61|  |  |  |Total: 49|");
        assertThat(shell.evaluate(() -> "record 3")).isEqualTo("|45|X |7/|00|0/|12|61|3 |  |  |Total: 52|");
        assertThat(shell.evaluate(() -> "record 7")).isEqualTo("|45|X |7/|00|0/|12|61|3/|  |  |Total: 59|");
        assertThat(shell.evaluate(() -> "record 4")).isEqualTo("|45|X |7/|00|0/|12|61|3/|4 |  |Total: 63|");
        assertThat(shell.evaluate(() -> "record 4")).isEqualTo("|45|X |7/|00|0/|12|61|3/|44|  |Total: 67|");
        assertThat(shell.evaluate(() -> "record 10")).isEqualTo("Game Over! |45|X |7/|00|0/|12|61|3/|44|X |Total: 77|");
    }

    @Test
    public void displaysErrorMessageWhenRecordingInvalidScorePerFrame() {
        assertThat(shell.evaluate(() -> "record -1")).isEqualTo("Please enter a valid bowl score!");
        assertThat(shell.evaluate(() -> "record 11")).isEqualTo("Please enter a valid bowl score!");
        assertThat(shell.evaluate(() -> "record 2000")).isEqualTo("Please enter a valid bowl score!");
        assertThat(shell.evaluate(() -> "record 70.5")).isEqualTo("Please enter a valid bowl score!");
        assertThat(shell.evaluate(() -> "record cheese")).isEqualTo("Please enter a valid bowl score!");
    }
}
