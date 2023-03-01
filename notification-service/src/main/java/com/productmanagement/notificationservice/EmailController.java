package com.productmanagement.notificationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notif")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/mail")
    public String sendSimpleMail(@RequestBody EmailDetails emaildetails) {
        return emailService.sendSimpleMail(emaildetails);
    }

}
