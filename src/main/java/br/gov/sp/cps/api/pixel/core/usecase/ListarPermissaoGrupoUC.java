package br.gov.sp.cps.api.pixel.core.usecase;

import br.gov.sp.cps.api.pixel.core.domain.dto.projection.PermissaoGrupoProjection;
import br.gov.sp.cps.api.pixel.core.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListarPermissaoGrupoUC {

    private final UserRepository userRepository;

    public List<PermissaoGrupoProjection> listarPermissaoGrupo(Long grupoId) {
        return userRepository.buscarPermissoesPorGrupoId(grupoId);
    }
}
