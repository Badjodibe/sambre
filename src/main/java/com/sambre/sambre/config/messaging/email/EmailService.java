package com.sambre.sambre.config.messaging.email;


import com.sambre.sambre.entities.enumerations.EmailTemplateName;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {
    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String emailUsername;


    public EmailService(JavaMailSender emailSender, SpringTemplateEngine templateEngine) {
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    @Async
    public void sendEmail(String to,
                          String username,
                          String subject,
                          EmailTemplateName emailTemplateName,
                          String token
    ) throws MessagingException {
        String templateName;

        if (emailTemplateName == null){
            templateName = "confirm-email";
        }else {
            templateName = emailTemplateName.getName();
        }

        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(
                mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED,
                StandardCharsets.UTF_8.name()
        );

        Map<String, Object> properties = new HashMap<>();
        properties.put("username", username);
        properties.put("activationCode", token);

        Context context = new Context();
        context.setVariables(properties);

        helper.setFrom(emailUsername);
        helper.setTo(to);
        helper.setSubject(subject);

        String template = templateEngine.process(templateName, context);

        helper.setText(template, true);

        // Envoi du message
        emailSender.send(mimeMessage);

    }

    @Async
    public void sendValidationAccountEmail(String to,
                                           String username,
                                           String subject,
                                           EmailTemplateName emailTemplateName
    ) throws MessagingException {
        String templateName;

        if (emailTemplateName == null){
            templateName = "confirm-email";
        }else {
            templateName = emailTemplateName.getName();
        }

        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(
                mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED,
                StandardCharsets.UTF_8.name()
        );

        Map<String, Object> properties = new HashMap<>();
        properties.put("username", username);

        Context context = new Context();
        context.setVariables(properties);

        helper.setFrom(emailUsername);
        helper.setTo(to);
        helper.setSubject(subject);

        String template = templateEngine.process(templateName, context);

        helper.setText(template, true);

        // Envoi du message
        emailSender.send(mimeMessage);

    }
}
