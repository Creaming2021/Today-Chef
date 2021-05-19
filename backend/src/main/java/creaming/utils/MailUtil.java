package creaming.utils;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@Component
public class MailUtil {

    private final JavaMailSender javaMailSender;

    public MailUtil(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendChatRoomInfo(String email, String courseName, String roomName, String password) {
        StringBuilder mailContent = new StringBuilder();
        MimeMessagePreparator messagePreparer = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("noreply@creaming.co.kr");
            messageHelper.setTo(email);
            messageHelper.setSubject("[오늘 셰프] ");
            String content = mailContent.append("<strong style=\"font-size:1rem\">강의명</strong> : ").append(courseName).append("<br/>")
                    .append("<strong style=\"font-size:1rem\">채팅방 이름</strong> : ").append(roomName).append("<br/>")
                    .append("<strong style=\"font-size:1rem\">채팅방 비밀번호</strong> : ").append(password).append("<br/>")
                    .append("<img src='https://creaming-bucket-b204.s3.ap-northeast-2.amazonaws.com/logo_icon.png' width=\"300\"/>")
                    .toString();
            messageHelper.setText(content, true);
        };
        javaMailSender.send(messagePreparer);
    }

}
