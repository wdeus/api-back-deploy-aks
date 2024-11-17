package br.gov.sp.cps.api.pixel.outbound.jpa;

import br.gov.sp.cps.api.pixel.core.domain.entity.Permissao;
import br.gov.sp.cps.api.pixel.core.domain.repository.PermissaoRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PermissaoJpaRepository extends JpaRepository<Permissao,Integer>, PermissaoRepository {

    default List<Permissao>listarTodos(){
        return findAll();
    }

    default Optional<Permissao> buscarPorId(int id) {
        return findById(id);
    }

    default Permissao salvar(Permissao permissao) {
        return save(permissao);
    }
}
