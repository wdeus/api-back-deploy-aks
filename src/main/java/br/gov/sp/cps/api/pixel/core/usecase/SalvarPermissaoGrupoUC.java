package br.gov.sp.cps.api.pixel.core.usecase;

import br.gov.sp.cps.api.pixel.core.domain.entity.Grupo;
import br.gov.sp.cps.api.pixel.core.domain.entity.Permissao;
import br.gov.sp.cps.api.pixel.core.domain.repository.GrupoRepository;
import br.gov.sp.cps.api.pixel.core.domain.repository.PermissaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalvarPermissaoGrupoUC {

    private final GrupoRepository grupoRepository;
    private final PermissaoRepository permissaoRepository;
    
    public void salvarPermissaoGrupo(Long idGrupo, List<Integer> permissoesId) {
        Grupo grupo = grupoRepository.buscarPorId(idGrupo)
                .orElseThrow(() -> new IllegalArgumentException("Grupo não encontrado"));


        List<Permissao> permissoes = new ArrayList<>();
        for (Integer permissaoId : permissoesId) {

            Permissao permissao = permissaoRepository.buscarPorId(permissaoId)
                    .orElseThrow(() -> new IllegalArgumentException("Permissão não encontrada: ID " + permissaoId));
            permissoes.add(permissao);
        }

        grupo.setPermissoes(permissoes);
        grupoRepository.salvar(grupo);
    }
}
