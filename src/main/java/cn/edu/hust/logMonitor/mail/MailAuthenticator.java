package cn.edu.hust.logMonitor.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuthenticator extends Authenticator {
    String userName;
    String userPassword;

    public MailAuthenticator() {
        super();
    }

    public MailAuthenticator(String user, String pwd) {
        super();
        userName = user;
        userPassword = pwd;
    }

    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, userPassword);
    }

}

