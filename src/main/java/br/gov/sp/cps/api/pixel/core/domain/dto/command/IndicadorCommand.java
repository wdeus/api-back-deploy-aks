package br.gov.sp.cps.api.pixel.core.domain.dto.command;

import br.gov.sp.cps.api.pixel.core.domain.dto.Filtro;
import lombok.Data;

@Data
public class IndicadorCommand {
    private int id;
    private Filtro indicador;
    private Filtro filtro;
    private String usuario;
    private String descricao;
}
