package br.gov.sp.cps.api.pixel.inbound.rest;

import br.gov.sp.cps.api.pixel.core.domain.dto.projection.ComboboxProjection;
import br.gov.sp.cps.api.pixel.core.usecase.ObterDimensoesUC;
import br.gov.sp.cps.api.pixel.core.usecase.ObterFatosUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filtros")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FiltroController {

    private final ObterFatosUC obterFatosUC;
    private final ObterDimensoesUC obterDimensoesUC;

    @GetMapping("/fatos")
    public ResponseEntity<List<ComboboxProjection>> listarFatos(){
        return ResponseEntity.ok(obterFatosUC.executar());
    }

    @GetMapping("/dimensoes")
    public ResponseEntity<List<ComboboxProjection>> listarDimensoesPorFato(@RequestParam("fato") String fato){
        return ResponseEntity.ok(obterDimensoesUC.executar(fato));
    }
}
