package br.gov.sp.cps.api.pixel.core.usecase;

import br.gov.sp.cps.api.pixel.core.domain.dto.RegisterDTO;
import br.gov.sp.cps.api.pixel.core.domain.entity.User;
import br.gov.sp.cps.api.pixel.core.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistroUC {

    private final UserRepository userRepository;

    public void registrarUsuario(RegisterDTO data) {
        if (userRepository.buscarPorLogin(data.getLogin()) != null) {
            throw new IllegalArgumentException("Login já existe");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        User user = new User(data.getLogin(), encryptedPassword, data.getRole());

        userRepository.salvar(user);
    }
}
