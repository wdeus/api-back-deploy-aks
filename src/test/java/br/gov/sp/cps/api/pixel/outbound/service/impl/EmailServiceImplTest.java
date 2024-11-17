package br.gov.sp.cps.api.pixel.outbound.service.impl;

import br.gov.sp.cps.api.pixel.PixelApplication;
import br.gov.sp.cps.api.pixel.core.domain.dto.command.EnviarEmailCommand;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = PixelApplication.class)
class EmailServiceImplTest {

    @Test
    void deveChamarMetodoEnviar() {

        JavaMailSender mailSenderMock = Mockito.mock(JavaMailSender.class);
        EmailServiceImpl emailService = new EmailServiceImpl(mailSenderMock);

        EnviarEmailCommand emailCommand = new EnviarEmailCommand();
        emailCommand.setRecipient("teste@gmail.com");
        emailCommand.setMsgBody("Teste");
        emailCommand.setSubject("Teste");

        emailService.sendSimpleMail(emailCommand);

        verify(mailSenderMock, times(1)).send(Mockito.any(SimpleMailMessage.class));
    }
}
