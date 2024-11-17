package br.gov.sp.cps.api.pixel.core.domain.repository;

import br.gov.sp.cps.api.pixel.core.domain.entity.Contratacao;

import java.util.List;

public interface ContratacaoRepository {
    List<Contratacao> salvar(List<Contratacao> contratacoes);
}
