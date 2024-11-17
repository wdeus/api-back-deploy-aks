package br.gov.sp.cps.api.pixel.core.domain.dto.command;

import br.gov.sp.cps.api.pixel.core.domain.dto.Eixo;
import br.gov.sp.cps.api.pixel.core.domain.dto.Filtro;
import lombok.Data;

import java.util.List;

@Data
public class VisualizarCardCommand {
    private Eixo eixoX;
    private List<Filtro> filtros;
}
