package dragard.service;

import org.dragard.ServerApplication;
import org.dragard.service.MessageService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServerApplication.class)
public class MessageServiceTest {

    private static final String TEST_STRING = "test";
    private static final String TEST_STRING2 = "testadfargergerwhrthegw";
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    @Autowired
    private MessageService messageService;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testSend() throws Exception {
        messageService.send(TEST_STRING);
        assertThat(outContent.toString()).contains(TEST_STRING);
        messageService.send(TEST_STRING2);
        assertThat(outContent.toString()).contains(TEST_STRING2);
    }
}
