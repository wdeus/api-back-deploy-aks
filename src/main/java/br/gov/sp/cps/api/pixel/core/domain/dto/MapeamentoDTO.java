package br.gov.sp.cps.api.pixel.core.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapeamentoDTO {

    List<String> cargoParticipanteRH;
    List<String> criadorProcessoSeletivo;
    List<String> descricaoFeedback;
    List<String> descricaoProcessoSeletivo;
    List<String> dtAberturaVaga;
    List<String> dtAceiteOferta;
    List<String> dtContratacao;
    List<String> dtEntrevista;
    List<String> dtFechamentoVaga;
    List<String> dtFimProcessoSeletivo;
    List<String> dtHora;
    List<String> dtInicioProcessoSeletivo;
    List<String> entrevistador;
    List<String> localizacaoVaga;
    List<String> nomeParticipanteRH;
    List<String> nomeProcessoSeletivo;
    List<Integer> nrPosicoesVaga;
    List<String> requisitosVaga;
    List<String> responsavelVaga;
    List<String> resultado;
    List<BigDecimal> salarioInicial;
    List<String> statusProcessoSeletivo;
    List<String> statusVaga;
    List<String> tipo;
    List<Integer> tipoContrato;
    List<String> tituloVaga;
    int nrCandidatosInscritos;
    int nrContratacoes;
    int nrEntrevistas;
    int nrPosicoesAbertas;
    BigDecimal salarioInicialMedio;
    int tempoMedioProcesso;
}
