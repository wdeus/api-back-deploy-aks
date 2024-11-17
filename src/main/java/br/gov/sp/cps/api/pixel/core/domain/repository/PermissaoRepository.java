package br.gov.sp.cps.api.pixel.core.domain.repository;

import br.gov.sp.cps.api.pixel.core.domain.entity.Permissao;

import java.util.List;
import java.util.Optional;

public interface PermissaoRepository {

    Permissao salvar(Permissao permissao);

    List<Permissao> listarTodos();

    Optional<Permissao> buscarPorId(int id);
}
