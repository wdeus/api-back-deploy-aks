package br.gov.sp.cps.api.pixel.inbound.rest;

import br.gov.sp.cps.api.pixel.core.usecase.AnalisarDocumentoUC;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/importacao")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ImportacaoController {

    private final AnalisarDocumentoUC analisarDocumentoUC;

    @SneakyThrows
    @PostMapping
    public void importar(@RequestParam("file") MultipartFile file) {
        analisarDocumentoUC.executar(file.getBytes());
    }
}
