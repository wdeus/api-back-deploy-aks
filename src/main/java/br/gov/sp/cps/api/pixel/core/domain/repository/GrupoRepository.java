package br.gov.sp.cps.api.pixel.core.domain.repository;

import br.gov.sp.cps.api.pixel.core.domain.entity.Grupo;

import java.util.List;
import java.util.Optional;

public interface GrupoRepository {

    List<Grupo> listarTodos();

    Optional<Grupo> buscarPorId(Long id);

    Grupo salvar(Grupo grupo);
}
