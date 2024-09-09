package Finial.HMS.ADMIN_MODULE.EMAIL;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Data
@Slf4j
public class ForgetMailSender {

    @Autowired
    private JavaMailSender mailSender;


    private String fromName = "dont.replay Hospital_Management";

    @Value("${spring.mail.username}")
    private String username;


    public void  ForgetPassword(EmailEntity email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromName + " <" + username + ">");
        message.setTo(email.getEmail());
        message.setSubject("Reset Your Password...!");
        message.setText("Your OTP is: " + email.getOTP() +"\n"+
                "Expires in : "+ email.getTime());
        mailSender.send(message);
    }

}
