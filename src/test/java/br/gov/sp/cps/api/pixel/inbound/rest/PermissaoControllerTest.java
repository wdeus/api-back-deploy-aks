package br.gov.sp.cps.api.pixel.inbound.rest;

import br.gov.sp.cps.api.pixel.PixelApplication;
import br.gov.sp.cps.api.pixel.core.domain.entity.Permissao;
import br.gov.sp.cps.api.pixel.core.usecase.PermissaoUC;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = PixelApplication.class)
@AutoConfigureMockMvc
@WithMockUser(username="admin",roles={"USER","ADMIN"})
public class PermissaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PermissaoUC permissaoUC;

    @Test
    void deveRetornarListaDePermissoes() throws Exception {
        List<Permissao> permissoes = Arrays.asList(new Permissao(1, "TESTE", "Teste", null));
        when(permissaoUC.listar()).thenReturn(permissoes);

        mockMvc.perform(get("/permissoes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].chave").value("TESTE"));
    }

    @Test
    void buscarPorIdDeveRetornarPermissaoQuandoExistir() throws Exception {
        Permissao permissao = new Permissao(1, "TESTE", "Teste", null);
        when(permissaoUC.buscarPorId(1)).thenReturn(Optional.of(permissao));

        mockMvc.perform(get("/permissoes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.chave").value("TESTE"));
    }

    @Test
    void buscarPorIdDeveRetornarNotFoundQuandoNaoExistir() throws Exception {
        when(permissaoUC.buscarPorId(1)).thenReturn(Optional.empty());

        mockMvc.perform(get("/permissoes/1"))
                .andExpect(status().isNotFound());
    }
}
