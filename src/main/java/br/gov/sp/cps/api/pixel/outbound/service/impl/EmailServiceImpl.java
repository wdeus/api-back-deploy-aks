package br.gov.sp.cps.api.pixel.outbound.service.impl;

import br.gov.sp.cps.api.pixel.core.domain.dto.command.EnviarEmailCommand;
import br.gov.sp.cps.api.pixel.outbound.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final String SENDER = "notificacoespro4tech@gmail.com";

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendSimpleMail(EnviarEmailCommand command)
    {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(SENDER);
            mailMessage.setTo(command.getRecipient());
            mailMessage.setText(command.getMsgBody());
            mailMessage.setSubject(command.getSubject());
            javaMailSender.send(mailMessage); //dar um verify no send
            log.info("Email enviado com sucesso");
        }
        catch (Exception e) {
            log.info("Erro ao enviar email: {}", e.getCause());
        }
    }
}
