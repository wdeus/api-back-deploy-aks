package br.gov.sp.cps.api.pixel.core.usecase;

import br.gov.sp.cps.api.pixel.core.domain.dto.command.EnviarEmailCommand;
import br.gov.sp.cps.api.pixel.core.domain.entity.Indicador;
import br.gov.sp.cps.api.pixel.core.domain.repository.IndicadorRepository;
import br.gov.sp.cps.api.pixel.outbound.jpa.bd.IndicadorRepositoryImpl;
import br.gov.sp.cps.api.pixel.outbound.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificacaoUC {

    private final EmailService emailService;
    private final IndicadorRepository indicadorRepository;
    private final IndicadorRepositoryImpl indicadorRepositoryImpl;

    public void verificarIndicadores(String usuario) {

        List<Indicador> indicadores = indicadorRepository.localizarPorUsuario(usuario);

        for (Indicador indicador : indicadores) {
            List<?> resultado = indicadorRepositoryImpl.executarConsultaIndicador(indicador);

            if (!resultado.isEmpty()) {
                enviarEmailNotificacao(indicador);
            }
        }
    }

    private void enviarEmailNotificacao(Indicador indicador) {
        EnviarEmailCommand emailCommand = EnviarEmailCommand.builder()
                .recipient("notificacoespro4tech@gmail.com")
                .subject("Indicador atingido")
                .msgBody("Nome do indicador: " + indicador.getDescricao())
                .build();
        emailService.sendSimpleMail(emailCommand);
    }
}
