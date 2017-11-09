package com.jgreubel.bowlinggamekata;

import com.jgreubel.bowlinggamekata.helpers.TestApplicationRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.shell.Shell;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(TestApplicationRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BowlScoringTest {

    @Autowired
    private Shell shell;

    @Test
    public void canRecordScorePerBowl() throws Exception {
        IntStream.rangeClosed(1, 19)
                .forEach(i -> shell.evaluate(() -> "bowl 2"));

        assertThat(shell.evaluate(() -> "bowl 2")).isEqualTo("40");
    }

    @Test
    public void doesNotAllowInvalidScoresPerBowl() throws Exception {
        assertThat(shell.evaluate(() -> "bowl -1")).isEqualTo("Invalid Score");
        assertThat(shell.evaluate(() -> "bowl 11")).isEqualTo("Invalid Score");
    }

    @Test
    public void doesNotAllowInvalidScoresPerFrame() throws Exception {
        shell.evaluate(() -> "bowl 4");
        assertThat(shell.evaluate(() -> "bowl 7")).isEqualTo("Invalid Score");
    }

    @Test
    public void onlyUsesBowlsFromSameFrame_whenCheckingForValidScoresPerFrame() throws Exception {
        shell.evaluate(() -> "bowl 4");
        shell.evaluate(() -> "bowl 5");
        assertThat(shell.evaluate(() -> "bowl 7")).isEqualTo("16");
    }

    @Test
    public void itCountsAStrikeAsAnEntireFrame() throws Exception {
        shell.evaluate(() -> "bowl 10");
        assertThat(shell.evaluate(() -> "bowl 7")).isEqualTo("17");
    }

    @Test
    public void addsNextTwoBowlAsBonusWhenBowlingAStrike() throws Exception {
        shell.evaluate(() -> "bowl 10");
        shell.evaluate(() -> "bowl 5");
        assertThat(shell.evaluate(() -> "bowl 3")).isEqualTo("26");
    }

    @Test
    public void addsNextBowlAsBonusWhenBowlingASpare() throws Exception {
        shell.evaluate(() -> "bowl 5");
        shell.evaluate(() -> "bowl 5");
        assertThat(shell.evaluate(() -> "bowl 7")).isEqualTo("24");
    }

    @Test
    public void itCorrectlyScoresAStrikeAndASpare() throws Exception {
        shell.evaluate(() -> "bowl 10");
        shell.evaluate(() -> "bowl 5");
        shell.evaluate(() -> "bowl 5");
        assertThat(shell.evaluate(() -> "bowl 7")).isEqualTo("44");
    }

}
