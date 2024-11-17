package br.gov.sp.cps.api.pixel.core.usecase;

import br.gov.sp.cps.api.pixel.core.domain.entity.Indicador;
import br.gov.sp.cps.api.pixel.core.domain.entity.Permissao;
import br.gov.sp.cps.api.pixel.core.domain.repository.PermissaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissaoUC {

    private final PermissaoRepository permissaoRepository;

    public PermissaoUC(PermissaoRepository permissaoRepository){
        this.permissaoRepository = permissaoRepository;
    }

    public List<Permissao>listar(){
        return permissaoRepository.listarTodos();
    }

    public Optional<Permissao> buscarPorId(int id){
        return permissaoRepository.buscarPorId(id);
    }
}
