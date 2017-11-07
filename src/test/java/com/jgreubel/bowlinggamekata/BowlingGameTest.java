package com.jgreubel.bowlinggamekata;

import com.jgreubel.bowlinggamekata.helpers.TestApplicationRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.shell.Shell;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(TestApplicationRunner.class)
public class BowlingGameTest {

    @Autowired
    private Shell shell;

    @Test
    public void canRecordScorePerFrame() throws Exception {
        IntStream.rangeClosed(1, 9)
                .forEach(i -> shell.evaluate(() -> "bowl " + i));

        assertThat(shell.evaluate(() -> "bowl 10")).isEqualTo("55");
    }

    @Test
    public void doesNotAllowInvalidScores() throws Exception {
        assertThat(shell.evaluate(() -> "bowl -1")).isEqualTo("Invalid Score");
        assertThat(shell.evaluate(() -> "bowl 11")).isEqualTo("Invalid Score");
    }

}
