package br.gov.sp.cps.api.pixel.core.usecase;

import br.gov.sp.cps.api.pixel.core.domain.dto.projection.ComboboxProjection;
import br.gov.sp.cps.api.pixel.core.domain.repository.FiltroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ObterFatosUC {

    private final FiltroRepository filtroRepository;

    public List<ComboboxProjection> executar(){
        return filtroRepository.buscarFatos();
    }
}
