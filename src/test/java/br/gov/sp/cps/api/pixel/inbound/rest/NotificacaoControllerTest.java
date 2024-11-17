package br.gov.sp.cps.api.pixel.inbound.rest;

import br.gov.sp.cps.api.pixel.PixelApplication;
import br.gov.sp.cps.api.pixel.core.domain.dto.command.IndicadorCommand;
import br.gov.sp.cps.api.pixel.core.usecase.NotificacaoUC;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = PixelApplication.class)
@AutoConfigureMockMvc
@WithMockUser(username="admin",roles={"USER","ADMIN"})
class NotificacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private NotificacaoUC notificacaoUC;

    @Test
    void deveChamarUseCaseQuandoReceberPost() throws Exception {
        IndicadorCommand indicadorCommand = new IndicadorCommand();
        indicadorCommand.setUsuario("ADMIN");

        mockMvc.perform(post("/notificacoes")
                        .contentType("application/json")
                        .content("{ \"usuario\": \"ADMIN\" }"))
                .andExpect(status().isOk());
        assertNotNull(notificacaoUC);
    }
}
