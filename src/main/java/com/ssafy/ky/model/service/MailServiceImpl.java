package com.ssafy.ky.model.service;
import javax.mail.internet.MimeMessage;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.ssafy.ky.dto.Mail;
 
 
@Service("mailService")
public class MailServiceImpl implements MailService {
 
    @Autowired
    JavaMailSender mailSender;
 
    @Override
    public void sendEmail(Mail mail) {
 
        final MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                
                helper.setFrom(mail.getMailFrom()); // recipient
                helper.setTo(mail.getMailTo()); //sender
                helper.setSubject(mail.getMailSubject()); // mail title
                helper.setText(mail.getMailContent(), true); // mail content
                FileSystemResource fsr = new FileSystemResource("card.jpg");
      		  	helper.addAttachment("card.jpg",fsr);
				/*
				 * FileSystemResource fsr = new FileSystemResource(
				 * "/Users/SSAFY/Desktop/WhereIsMyHome_8_7_Finish_Back/whereismyhome_8_7_finish_back/src/main/resources/static/assets/mail.html"
				 * ); helper.addAttachment(
				 * "/Users/SSAFY/Desktop/WhereIsMyHome_8_7_Finish_Back/whereismyhome_8_7_finish_back/src/main/resources/static/assets/mail.html"
				 * ,fsr);
				 */
            }
        };
 
        mailSender.send(preparator);
    }
}