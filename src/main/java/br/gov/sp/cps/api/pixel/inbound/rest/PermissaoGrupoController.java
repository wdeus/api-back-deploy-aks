package br.gov.sp.cps.api.pixel.inbound.rest;

import br.gov.sp.cps.api.pixel.core.domain.dto.SalvarPermissoesGrupoDTO;
import br.gov.sp.cps.api.pixel.core.domain.dto.projection.PermissaoGrupoProjection;
import br.gov.sp.cps.api.pixel.core.usecase.ListarPermissaoGrupoUC;
import br.gov.sp.cps.api.pixel.core.usecase.SalvarPermissaoGrupoUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissoes/grupos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PermissaoGrupoController {

    private final SalvarPermissaoGrupoUC salvarPermissaoGrupoUC;
    private final ListarPermissaoGrupoUC listarPermissaoGrupoUC;

    @PostMapping
    public ResponseEntity<Void> salvarPermissaoGrupo(@RequestBody SalvarPermissoesGrupoDTO data) {
        salvarPermissaoGrupoUC.salvarPermissaoGrupo(data.getGrupoId(), data.getPermissoesIds());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<PermissaoGrupoProjection>> listarPermissaoGrupo(@PathVariable Long id) {
        List<PermissaoGrupoProjection> permissoes = listarPermissaoGrupoUC.listarPermissaoGrupo(id);
        return ResponseEntity.ok(permissoes);
    }
}
