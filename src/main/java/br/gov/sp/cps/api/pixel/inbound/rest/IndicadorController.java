package br.gov.sp.cps.api.pixel.inbound.rest;

import br.gov.sp.cps.api.pixel.core.domain.dto.command.IndicadorCommand;
import br.gov.sp.cps.api.pixel.core.domain.entity.Indicador;
import br.gov.sp.cps.api.pixel.core.usecase.IndicadorUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/indicadores")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class IndicadorController {

    private final IndicadorUC indicadorUC;

    @PostMapping
    public ResponseEntity<Indicador> criarIndicador(@RequestBody IndicadorCommand indicadorCommand) {
        Indicador novoIndicador = indicadorUC.postar(indicadorCommand);
        return ResponseEntity.ok(novoIndicador);
    }

    @GetMapping
    public ResponseEntity<List<IndicadorCommand>> listarTodosIndicadores() {
        List<Indicador> indicadores = indicadorUC.listar();
        List<IndicadorCommand> indicadoresDTO = indicadores.stream()
                .map(Indicador::toCommand)
                .collect(Collectors.toList());
        return ResponseEntity.ok(indicadoresDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IndicadorCommand> buscarIndicadorPorId(@PathVariable int id) {
        Optional<Indicador> indicadorOptional = indicadorUC.buscarPorId(id);
        if (indicadorOptional.isPresent()) {
            IndicadorCommand indicadorCommand = indicadorOptional.get().toCommand();
            return ResponseEntity.ok(indicadorCommand);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarIndicador(@PathVariable int id) {
        Optional<Indicador> indicadorOptional = indicadorUC.buscarPorId(id);
        if (indicadorOptional.isPresent()) {
            indicadorUC.deletar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
