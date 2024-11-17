package br.gov.sp.cps.api.pixel.core.usecase;

import br.gov.sp.cps.api.pixel.core.domain.entity.Grupo;
import br.gov.sp.cps.api.pixel.core.domain.repository.GrupoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GrupoUC {

    private final GrupoRepository  grupoRepository;

    public GrupoUC(GrupoRepository grupoRepository){
        this.grupoRepository = grupoRepository;
    }
    @Transactional
    public List<Grupo>listar(){
        return grupoRepository.listarTodos();
    }
}
