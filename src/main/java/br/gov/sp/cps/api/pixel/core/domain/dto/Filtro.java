package br.gov.sp.cps.api.pixel.core.domain.dto;
import lombok.Data;
@Data
public class Filtro {
    private String nome;
    private String campo;
    private String comparador;
    private String valor;
}
