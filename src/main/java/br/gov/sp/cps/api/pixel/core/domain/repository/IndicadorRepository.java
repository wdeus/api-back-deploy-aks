package br.gov.sp.cps.api.pixel.core.domain.repository;

import br.gov.sp.cps.api.pixel.core.domain.entity.Indicador;

import java.util.List;
import java.util.Optional;

public interface IndicadorRepository {

    Indicador salvar(Indicador indicador);

    List<Indicador> listarTodos();

    Optional<Indicador> buscarPorId(int id);

    List<Indicador> localizarPorUsuario(String usuario);

    void deletarPorId(int id);

}
