package br.gov.sp.cps.api.pixel.core.domain.dto.command;
import java.util.List;

import br.gov.sp.cps.api.pixel.core.domain.dto.Eixo;
import br.gov.sp.cps.api.pixel.core.domain.dto.Filtro;
import lombok.Data;

@Data
public class VisualizarGraficoCommand {
    private Eixo eixoY;
    private Eixo eixoX; 
    private List<Filtro> filtros;
}
