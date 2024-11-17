package br.gov.sp.cps.api.pixel.inbound.rest;

import br.gov.sp.cps.api.pixel.core.domain.dto.command.IndicadorCommand;
import br.gov.sp.cps.api.pixel.core.usecase.NotificacaoUC;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notificacoes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class NotificacaoController {

    private final NotificacaoUC notificacaoUC;

    @PostMapping
    public void obterNotificacao(@RequestBody IndicadorCommand indicadorCommand) {
        String usuario = indicadorCommand.getUsuario();
        notificacaoUC.verificarIndicadores(usuario);
    }
}
