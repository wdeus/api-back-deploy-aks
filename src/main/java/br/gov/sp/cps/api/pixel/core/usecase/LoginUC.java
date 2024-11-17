package br.gov.sp.cps.api.pixel.core.usecase;

import br.gov.sp.cps.api.pixel.core.domain.dto.AuthenticationDTO;
import br.gov.sp.cps.api.pixel.core.domain.dto.LoginDTO;
import br.gov.sp.cps.api.pixel.core.domain.entity.User;
import br.gov.sp.cps.api.pixel.core.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUC {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final ListarPermissaoGrupoUC listarPermissaoGrupoUC;

    public LoginDTO realizarLogin(AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getPassword());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        User user = (User) auth.getPrincipal();
        Long grupoId = user.getGrupo().getId();

        var permissoes = listarPermissaoGrupoUC.listarPermissaoGrupo(grupoId);

        return new LoginDTO(token, permissoes);
    }
}
