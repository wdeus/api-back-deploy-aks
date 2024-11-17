package br.gov.sp.cps.api.pixel.inbound.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import br.gov.sp.cps.api.pixel.core.domain.dto.command.VisualizarGraficoCommand;
import br.gov.sp.cps.api.pixel.core.usecase.VisualizarGraficoUC;

import java.util.List;

@RestController
@RequestMapping("visualizacao/grafico")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class GraficoController {

    private final VisualizarGraficoUC visualizarGraficoUC;

    @PostMapping
    public List<?> filtrar(@RequestBody VisualizarGraficoCommand command){
        return visualizarGraficoUC.executar(command);
    }
}
