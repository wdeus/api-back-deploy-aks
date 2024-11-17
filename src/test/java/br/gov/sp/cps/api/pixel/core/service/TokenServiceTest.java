package br.gov.sp.cps.api.pixel.core.service;

import br.gov.sp.cps.api.pixel.PixelApplication;
import br.gov.sp.cps.api.pixel.core.domain.entity.User;
import br.gov.sp.cps.api.pixel.core.domain.enumeration.UserRole;
import br.gov.sp.cps.api.pixel.core.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = PixelApplication.class)
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class TokenServiceTest {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Value("${api.security.token.secret}")
    private String secret;

    @Test
    void deveGerarETerConteudoValidoNoToken() {
        User user = new User("usuario", "encryptedPassword", UserRole.USER);
        userRepository.salvar(user);

        String token = tokenService.generateToken(user);

        assertNotNull(token);
        String username = tokenService.validateToken(token);
        assertEquals("usuario", username);
    }

    @Test
    void deveRetornarStringVaziaParaTokenInvalido() {
        String invalidToken = "tokenInvalido";
        String username = tokenService.validateToken(invalidToken);

        assertEquals("", username);
    }
}
