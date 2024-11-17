package br.gov.sp.cps.api.pixel.core.usecase;

import java.util.List;
import org.springframework.stereotype.Service;
import br.gov.sp.cps.api.pixel.core.domain.dto.command.VisualizarGraficoCommand;
import br.gov.sp.cps.api.pixel.core.domain.repository.GraficoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class VisualizarGraficoUC {
    private final GraficoRepository graficoRepository;

    public List<?> executar(VisualizarGraficoCommand command){
        return graficoRepository.getGrafico(command);
}
}
