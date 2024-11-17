package br.gov.sp.cps.api.pixel.core.domain.entity;

import br.gov.sp.cps.api.pixel.core.domain.dto.Filtro;
import br.gov.sp.cps.api.pixel.core.domain.dto.command.IndicadorCommand;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "config_Indicador")
public class Indicador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idConfigIndicador;

    @Column(name = "indicador_nome")
    private String indicadorNome;

    @Column(name = "indicador_campo")
    private String indicadorCampo;

    @Column(name = "indicador_comparador")
    private String indicadorComparador;

    @Column(name = "indicador_valor")
    private String indicadorValor;

    @Column(name = "filtro_nome")
    private String filtroNome;

    @Column(name = "filtro_campo")
    private String filtroCampo;

    @Column(name = "filtro_comparador")
    private String filtroComparador;

    @Column(name = "filtro_valor")
    private String filtroValor;

    @Column(nullable = false)
    private String usuario;

    private String descricao;


    public static Indicador toEntity(IndicadorCommand indicadorDto){
        Indicador indicador = new Indicador();
        indicador.setIndicadorNome(indicadorDto.getIndicador().getNome());
        indicador.setIndicadorCampo(indicadorDto.getIndicador().getCampo());
        indicador.setIndicadorComparador(indicadorDto.getIndicador().getComparador());
        indicador.setIndicadorValor(indicadorDto.getIndicador().getValor());
        indicador.setFiltroNome(indicadorDto.getFiltro().getNome());
        indicador.setFiltroCampo(indicadorDto.getFiltro().getCampo());
        indicador.setFiltroComparador(indicadorDto.getFiltro().getComparador());
        indicador.setFiltroValor(indicadorDto.getFiltro().getValor());
        indicador.setUsuario(indicadorDto.getUsuario());
        indicador.setDescricao(indicadorDto.getDescricao());
        return indicador;
    }

    public IndicadorCommand toCommand() {
        IndicadorCommand indicadorCommand = new IndicadorCommand();

        Filtro indicadorFiltro = new Filtro();
        indicadorFiltro.setNome(this.indicadorNome);
        indicadorFiltro.setCampo(this.indicadorCampo);
        indicadorFiltro.setComparador(this.indicadorComparador);
        indicadorFiltro.setValor(this.indicadorValor);

        Filtro filtro = new Filtro();
        filtro.setNome(this.filtroNome);
        filtro.setCampo(this.filtroCampo);
        filtro.setComparador(this.filtroComparador);
        filtro.setValor(this.filtroValor);

        
        indicadorCommand.setId(this.idConfigIndicador);
        indicadorCommand.setIndicador(indicadorFiltro);
        indicadorCommand.setFiltro(filtro);
        indicadorCommand.setUsuario(this.usuario);
        indicadorCommand.setDescricao(this.descricao);

        return indicadorCommand;
    }
}

