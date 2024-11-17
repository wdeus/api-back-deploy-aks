package br.gov.sp.cps.api.pixel.outbound.llm;

import br.gov.sp.cps.api.pixel.core.domain.repository.AnaliseRepository;
import br.gov.sp.cps.api.pixel.core.domain.repository.FormatadorRepository;
import br.gov.sp.cps.api.pixel.outbound.llm.config.LlmConfig;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AnaliseRepositoryImpl implements AnaliseRepository {

    private final LlmConfig llmConfig;

    private final FormatadorRepository formatadorRepository;

    @Override
    @SneakyThrows
    public String processarDados(String documento) {
        var chatModel = llmConfig.executar();
        Map<String, Object> params = new HashMap<>();
        params.put("document", documento);
        params.put("format", carregarPrompt());
        String mapeamentoDTO = ChatClient.create(chatModel).prompt()
                .user(u -> u.text("Retorne apenas a estrutura json: {format} de acordo com os dados a seguir: {document}. Você deve retornar uma lista da estrutura fornecida, " +
                                "de acordo com cada linha do documento em analise. Para os valores de data, desconsiderar as horas/minutos e retornar na estrutura yyyy-mm-dd. Os campos com prefixo nr devem ser todos preenchidos com 1." +
                                "O campo salarioInicialMedio deve ser a média dos salários de registros que possuem nomeProcessoSeletivo igual." +
                                "O campo tempoMedioProcesso deve ser a diferença entre os campos dtAberturaVaga e dtFechamentoVaga")
                        .params(params))
                .call()
                .content();
        return mapeamentoDTO;
    }

    public String carregarPrompt() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("prompt/prompt.json");
        return new String(inputStream.readAllBytes());
    }
}
