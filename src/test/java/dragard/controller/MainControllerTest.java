package dragard.controller;

import org.dragard.ServerApplication;
import org.dragard.controller.MainController;
import org.dragard.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServerApplication.class)
public class MainControllerTest {

    @Autowired
    private MainController mainController;

    @Test
    public void contexLoads() {
        assertThat(mainController).isNotNull();
    }

    @Test
    public void testMainPage() {
        assertThat(mainController.mainPage()).isEqualTo("main");
    }

    @Test
    public void testSendMessage() throws Exception {
        MessageService messageService = mock(MessageService.class);
        assertThat(mainController.sendMessage("any")).isEqualTo("main");

        doThrow(new Exception()).when(messageService).send("any");

        assertThat(mainController.sendMessage("any")).isEqualTo("main");
    }

}
