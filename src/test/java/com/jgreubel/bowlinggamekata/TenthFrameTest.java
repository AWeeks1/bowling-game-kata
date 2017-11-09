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
public class TenthFrameTest {

    @Autowired
    private Shell shell;

    @Test
    public void countsTwoExtraBowls_whenScoringAStrikeOnTheTenthFrame() throws Exception {
        IntStream.rangeClosed(1, 18)
                .forEach(i -> shell.evaluate(() -> "bowl 2"));

        shell.evaluate(() -> "bowl 10");
        shell.evaluate(() -> "bowl 6");
        assertThat(shell.evaluate(() -> "bowl 4")).isEqualTo("56");
    }

    @Test
    public void countsOneExtraBowls_whenScoringASpareOnTheTenthFrame() throws Exception {
        IntStream.rangeClosed(1, 18)
                .forEach(i -> shell.evaluate(() -> "bowl 2"));

        shell.evaluate(() -> "bowl 5");
        shell.evaluate(() -> "bowl 5");
        assertThat(shell.evaluate(() -> "bowl 4")).isEqualTo("50");
    }

}
