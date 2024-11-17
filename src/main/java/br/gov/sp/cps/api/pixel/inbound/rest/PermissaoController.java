package br.gov.sp.cps.api.pixel.inbound.rest;

import br.gov.sp.cps.api.pixel.core.domain.entity.Permissao;
import br.gov.sp.cps.api.pixel.core.usecase.PermissaoUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/permissoes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PermissaoController {

    private final PermissaoUC permissaoUC;

    @GetMapping
    public List<Permissao> listar() {
        return permissaoUC.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Permissao> buscarPorId(@PathVariable int id) {
        Optional<Permissao> permissao = permissaoUC.buscarPorId(id);
        return permissao.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
