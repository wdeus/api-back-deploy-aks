package br.gov.sp.cps.api.pixel.inbound.rest;

import br.gov.sp.cps.api.pixel.PixelApplication;
import br.gov.sp.cps.api.pixel.core.usecase.AnalisarDocumentoUC;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = PixelApplication.class)
@AutoConfigureMockMvc
@WithMockUser(username="admin",roles={"USER","ADMIN"})
class ImportacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnalisarDocumentoUC analisarDocumentoUC;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void importarTest() throws Exception {
        // Criação de um MockMultipartFile simulando um arquivo enviado na requisição
        MockMultipartFile file = new MockMultipartFile(
            "file",                      // Nome do parâmetro
            "documento.txt",              // Nome do arquivo
            MediaType.TEXT_PLAIN_VALUE,   // Tipo do arquivo
            "conteúdo do documento".getBytes() // Conteúdo do arquivo
        );

        // Simulação de requisição POST para o endpoint /importacao
        mockMvc.perform(multipart("/importacao")
                .file(file))
                .andExpect(status().isOk());  // Espera que o status da resposta seja 200 (OK)

        // Verifica se o método executar foi chamado com o conteúdo correto do arquivo
        verify(analisarDocumentoUC).executar(file.getBytes());
    }
}
