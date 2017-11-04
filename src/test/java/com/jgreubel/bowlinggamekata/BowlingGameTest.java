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
public class BowlingGameTest {

    @Autowired
    private Shell shell;

    @Test
    public void canRecordEntireGameScore() throws Exception {
        assertThat(shell.evaluate(() -> "bowl 300")).isEqualTo("300");
    }

    @Test
    public void doesNotAllowInvalidScores() throws Exception {
        assertThat(shell.evaluate(() -> "bowl -1")).isEqualTo("Invalid Score");
        assertThat(shell.evaluate(() -> "bowl 301")).isEqualTo("Invalid Score");
    }

}
