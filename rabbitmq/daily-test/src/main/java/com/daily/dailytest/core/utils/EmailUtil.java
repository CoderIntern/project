package com.daily.dailytest.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * <p> Date             :2018/1/18 </p>
 * <p> Module           : </p>
 * <p> Description      : </p>
 * <p> Remark           : </p>
 *
 * @author yangdejun
 * @version 1.0
 * <p>--------------------------------------------------------------</p>
 * <p>修改历史</p>
 * <p>    序号    日期    修改人    修改原因    </p>
 * <p>    1                                     </p>
 */
public class EmailUtil {

    //发送者xx@xx.com
    private String from;

    //发送服务
    private JavaMailSender sender;

    //日志
    private static final Logger logger = LoggerFactory.getLogger(EmailUtil.class);

    /**
     * 发送文字邮件
     * @param to
     * @param titile
     * @param content
     */
    public void sendSimpleEmail(String to, String titile, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        //发送者
        message.setFrom(from);
        //接收者
        message.setTo(to);
        //发送标题
        message.setSubject(titile);
        //发送内容
        message.setText(content);
        sender.send(message);
        logger.info("email send successfuly ~~~");
    }

    /**
     * 发送带附件的邮件
     * @param to
     * @param title
     * @param content
     * @param filePath
     */
    public void sendAttachmentsEmail(String to, String title, String content, String filePath) {
        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(title);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);

            sender.send(message);
            logger.info("email send susccessfuly ~~~");
        } catch (MessagingException e) {
            logger.error("email send fail !!!", e);
        }
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public JavaMailSender getSender() {
        return sender;
    }

    public void setSender(JavaMailSender sender) {
        this.sender = sender;
    }
}
