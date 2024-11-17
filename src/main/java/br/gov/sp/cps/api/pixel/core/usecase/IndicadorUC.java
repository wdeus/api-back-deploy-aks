package br.gov.sp.cps.api.pixel.core.usecase;

import br.gov.sp.cps.api.pixel.core.domain.dto.command.IndicadorCommand;
import br.gov.sp.cps.api.pixel.core.domain.entity.Indicador;
import br.gov.sp.cps.api.pixel.core.domain.repository.IndicadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IndicadorUC {

    private final IndicadorRepository indicadorRepository;

    public IndicadorUC(IndicadorRepository indicadorRepository) {
        this.indicadorRepository = indicadorRepository;
    }

    public Indicador postar(IndicadorCommand indicadorCommand) {
        return indicadorRepository.salvar(Indicador.toEntity(indicadorCommand));
    }

    public List<Indicador> listar() {
        return indicadorRepository.listarTodos();
    }

    public Optional<Indicador> buscarPorId(int id){
        return indicadorRepository.buscarPorId(id);
    }

    public void deletar(int id) {
        indicadorRepository.deletarPorId(id);
    }


}
