package br.gov.sp.cps.api.pixel.inbound.rest;

import br.gov.sp.cps.api.pixel.PixelApplication;
import br.gov.sp.cps.api.pixel.core.domain.dto.projection.PermissaoGrupoProjection;
import br.gov.sp.cps.api.pixel.core.usecase.ListarPermissaoGrupoUC;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = PixelApplication.class)
@AutoConfigureMockMvc
@WithMockUser(username="admin",roles={"USER","ADMIN"})
public class PermissaoGrupoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ListarPermissaoGrupoUC listarPermissaoGrupoUC;

    @Test
    void deveRetornarPermissoesDoGrupo() throws Exception {
        PermissaoGrupoProjection permissao = new PermissaoGrupoProjection() {
            @Override
            public Integer getIdPermissao() { return 1; }
            @Override
            public String getDescricao() { return "Teste"; }
            @Override
            public String getNomeGrupo() { return "Teste"; }
        };
        when(listarPermissaoGrupoUC.listarPermissaoGrupo(1L)).thenReturn(List.of(permissao));

        mockMvc.perform(get("/permissoes/grupos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].descricao").value("Teste"));
    }
}
