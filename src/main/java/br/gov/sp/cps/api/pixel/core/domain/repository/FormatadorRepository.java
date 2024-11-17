package br.gov.sp.cps.api.pixel.core.domain.repository;

import br.gov.sp.cps.api.pixel.core.domain.dto.MapeamentoDTO;

import java.util.List;

public interface FormatadorRepository {

    <T> T executar(String json, Class<T> entidadeClass);

    List<String> extrairJsonObjects(String input);
}
