package br.gov.sp.cps.api.pixel.inbound.rest;

import br.gov.sp.cps.api.pixel.core.domain.dto.AuthenticationDTO;
import br.gov.sp.cps.api.pixel.core.domain.dto.LoginDTO;
import br.gov.sp.cps.api.pixel.core.domain.dto.RegisterDTO;
import br.gov.sp.cps.api.pixel.core.usecase.LoginUC;
import br.gov.sp.cps.api.pixel.core.usecase.RegistroUC;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthenticationController {

    private final LoginUC loginUC;
    private final RegistroUC registroUC;

    @PostMapping("/login")
    public ResponseEntity<LoginDTO> login(@RequestBody @Valid AuthenticationDTO data) {
        LoginDTO response = loginUC.realizarLogin(data);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registrar(@RequestBody @Valid RegisterDTO data) {
        registroUC.registrarUsuario(data);
        return ResponseEntity.ok().build();
    }
}
