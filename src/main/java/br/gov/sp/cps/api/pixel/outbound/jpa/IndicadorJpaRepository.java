package br.gov.sp.cps.api.pixel.outbound.jpa;

import br.gov.sp.cps.api.pixel.core.domain.entity.Indicador;
import br.gov.sp.cps.api.pixel.core.domain.repository.IndicadorRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IndicadorJpaRepository extends JpaRepository<Indicador,Integer>, IndicadorRepository {

    default Indicador salvar(Indicador indicador) {
        return save(indicador);
    }

    default List<Indicador> listarTodos() {
        return findAll();
    }

    default Optional<Indicador> buscarPorId(int id) {
        return findById(id);
    }

    default List<Indicador> localizarPorUsuario(String usuario){
        return findByUsuario(usuario);
    }

    default void deletarPorId(int id) {
        deleteById(id);
    }

    List<Indicador> findByUsuario(String usuario);
}
