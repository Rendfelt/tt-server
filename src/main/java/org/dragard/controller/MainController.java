package org.dragard.controller;

import org.dragard.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/sender")
public class MainController {

    @Autowired
    private MessageService messageService;

    private Logger logger = LoggerFactory.getLogger(MainController.class);

    @RequestMapping
    public String mainPage() {
        return "main";
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ResponseBody
    public String sendMessage(@RequestBody String message) {
        try {
            messageService.send(message);
        } catch (Exception e) {
            logger.error("Sending message error: {}", e.getMessage());
        }
        return "main";
    }



}
