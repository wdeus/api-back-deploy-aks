package br.gov.sp.cps.api.pixel.outbound.jpa;

import br.gov.sp.cps.api.pixel.core.domain.entity.Grupo;
import br.gov.sp.cps.api.pixel.core.domain.repository.GrupoRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GrupoJpaRepository extends JpaRepository<Grupo,Long>, GrupoRepository {

    default List<Grupo> listarTodos(){
        return findAll();
    }

    default Optional<Grupo> buscarPorId(Long id) {
        return findById(id);
    }

    default Grupo salvar(Grupo grupo) {
        return save(grupo);
    }
}
