package br.gov.sp.cps.api.pixel.inbound.rest;

import br.gov.sp.cps.api.pixel.core.domain.dto.command.VisualizarCardCommand;
import br.gov.sp.cps.api.pixel.core.usecase.VisualizarCardUC;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visualizacao/card")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CardController {
    private final VisualizarCardUC visualizarCardUC;

    @PostMapping
    public List<?> filtrar(@RequestBody VisualizarCardCommand command){
        return visualizarCardUC.executar(command);
    }
}
