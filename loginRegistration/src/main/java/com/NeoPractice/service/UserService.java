package com.NeoPractice.service;

import com.NeoPractice.entity.User;
import com.NeoPractice.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
     private JavaMailSender mailSender;

    public String loadUsername(String username,String password)
    {
        User user=userRepository.findByUsername(username);
        if(user==null)
        {
            return "user not exist";
        }
        else
        {
            if(user.getUsername().equals(username) && user.getPassword().equals(password))
                return "login successfull";
            else
                return "invalid password";
        }
    }

    public void sendOTPEmail(User user)
            throws UnsupportedEncodingException, MessagingException {


        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("kajolmishra948@gmail.com", "Neosoft Support");
        helper.setTo(user.getEmail());

        String subject = "Registered Successfully !";

        String content = "<p>Hello " + user.getFirstname() + " Thank You !! for Registering</p>";


        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }
}
