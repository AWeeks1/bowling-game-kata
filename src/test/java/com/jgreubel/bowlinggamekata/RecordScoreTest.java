package com.jgreubel.bowlinggamekata;

import com.jgreubel.bowlinggamekata.helpers.TestApplicationRunner;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(TestApplicationRunner.class)
public class RecordScoreTest {

    @Test
    public void playerCanRecordEntireScoreOfGame() {
        Assertions.assertThat(true).isTrue();
    }
}
