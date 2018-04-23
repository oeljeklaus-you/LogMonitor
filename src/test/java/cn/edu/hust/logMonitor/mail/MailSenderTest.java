package cn.edu.hust.logMonitor.mail;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class MailSenderTest {
    @Test
    public void testSender()
    {
        List<String> recevicer=new LinkedList<String>();
        recevicer.add("1098181195@qq.com");
        List<String> ccList=new LinkedList<String>();
        ccList.add("1141318723@qq.com");
        MailInfo mail=new MailInfo("javaweb出现异常","抛出了空指针异常",recevicer,ccList);
        MessageSender.sendMail(mail);
    }
}
