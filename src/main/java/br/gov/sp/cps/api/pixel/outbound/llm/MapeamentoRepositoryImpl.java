package br.gov.sp.cps.api.pixel.outbound.llm;

import br.gov.sp.cps.api.pixel.core.domain.entity.*;
import br.gov.sp.cps.api.pixel.core.domain.repository.*;
import br.gov.sp.cps.api.pixel.outbound.llm.config.LlmConfig;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MapeamentoRepositoryImpl implements MapeamentoRepository {

    private final LlmConfig llmConfig;
    private final FormatadorRepository formatadorRepository;
    private final AcaoSeletivaRepository acaoSeletivaRepository;
    private final ContratacaoRepository contratacaoRepository;
    private final EntrevistaRepository entrevistaRepository;
    private final FatoEntrevistaRepository fatoEntrevistaRepository;
    private final FatoVagaRepository fatoVagaRepository;
    private final FeedbackRepository feedbackRepository;
    private final ParticipanteRHRepository participanteRHRepository;
    private final PeriodoRepository periodoRepository;
    private final ProcessoSeletivoRepository processoSeletivoRepository;
    private final VagaRepository vagaRepository;

    @Override
    @SneakyThrows
    public void popularEntidades(String mapeamentoDTO) {
        System.out.println("Inicio Mapeamento");
        var chatModel = llmConfig.executar();
        List<String> jsons = formatadorRepository.extrairJsonObjects(mapeamentoDTO);

        List<AcaoSeletiva> acoesSeletivas = converterJsonParaEntidades(jsons, AcaoSeletiva.class, chatModel);
        acaoSeletivaRepository.salvar(acoesSeletivas);

        List<Contratacao> contratacoes = converterJsonParaEntidades(jsons, Contratacao.class, chatModel);
        contratacaoRepository.salvar(contratacoes);

        List<Entrevista> entrevistas = converterJsonParaEntidades(jsons, Entrevista.class, chatModel);
        entrevistaRepository.salvar(entrevistas);

        List<Feedback> feedbacks = converterJsonParaEntidades(jsons, Feedback.class, chatModel);
        feedbackRepository.salvar(feedbacks);

        List<ParticipanteRH> participantesRH = converterJsonParaEntidades(jsons, ParticipanteRH.class, chatModel);
        participanteRHRepository.salvar(participantesRH);

        List<Periodo> periodos = converterJsonParaEntidades(jsons, Periodo.class, chatModel);
        periodoRepository.salvar(periodos);

        List<ProcessoSeletivo> processos = converterJsonParaEntidades(jsons, ProcessoSeletivo.class, chatModel);
        processoSeletivoRepository.salvar(processos);

        List<Vaga> vagas = converterJsonParaEntidades(jsons, Vaga.class, chatModel);
        vagaRepository.salvar(vagas);

        List<FatoEntrevista> fatoEntrevista = converterJsonParaEntidades(jsons, FatoEntrevista.class, chatModel);
        fatoEntrevistaRepository.popularEntidades(fatoEntrevista, entrevistas,vagas,feedbacks,acoesSeletivas,participantesRH,contratacoes);
        fatoEntrevistaRepository.salvar(fatoEntrevista);

        List<FatoVaga> fatoVagas = converterJsonParaEntidades(jsons, FatoVaga.class, chatModel);
        fatoVagaRepository.popularEntidades(fatoVagas, processos, periodos, vagas);
        fatoVagaRepository.salvar(fatoVagas);
        System.out.println("Fim Mapeamento");
    }

    @SneakyThrows
    public <T> List<T> converterJsonParaEntidades(List<String> jsons, Class<T> entidadeClass, OpenAiChatModel chatModel) {
        List<T> entidades = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();

        String estruturaEntidade = obterEstruturaEntidade(entidadeClass);
        String estruturaRetorno = carregarEstrutura();

        for (String json : jsons) {
            params.put("json", json);
            params.put("entidade", estruturaEntidade);
            params.put("estrutura", estruturaRetorno);

            String entidadeMapeada = ChatClient.create(chatModel).prompt()
                    .user(u -> u.text("Converta o json a seguir {json} para a entidade: {entidade}. Os atributos da entidade que contem prefixo id, devem ser preenchidos com 0. " +
                                    "Os atributos do tipo date devem ser representados com string. O retorno tem que ser baseado nesta estrutura: {estrutura} e nada mais (sem comentarios extras)." +
                                    " Os campos com prefixo nr devem ser todos preenchidos com 1")
                            .params(params))
                    .call()
                    .content();
            System.out.println("MAPEAMENTO: " + entidadeMapeada);
            T entidade = formatadorRepository.executar(entidadeMapeada, entidadeClass);
            entidades.add(entidade);
        }

        return entidades;
    }

    private <T> String obterEstruturaEntidade(Class<T> entidadeClass) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> estrutura = new HashMap<>();

        for (Field field : entidadeClass.getDeclaredFields()) {
            String nomeCampo = field.getName();

            if (field.isAnnotationPresent(JsonProperty.class)) {
                JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);
                nomeCampo = jsonProperty.value(); // Usa o valor da anotação como nome do campo
            }

            estrutura.put(nomeCampo, field.getType().getSimpleName());
        }

        try {
            return mapper.writeValueAsString(estrutura);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar a estrutura da entidade", e);
        }
    }

    private <T> String obterEstruturaFato(Class<T> entidadeClass) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> estrutura = new HashMap<>();

        for (Field field : entidadeClass.getDeclaredFields()) {
            String nomeCampo = field.getName();

            if (field.isAnnotationPresent(JsonProperty.class)) {
                JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);
                nomeCampo = jsonProperty.value();
                estrutura.put(nomeCampo, field.getType().getSimpleName());
            }
        }

        try {
            return mapper.writeValueAsString(estrutura);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar a estrutura da entidade", e);
        }
    }

    public String carregarEstrutura() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("prompt/estrutura.json");
        return new String(inputStream.readAllBytes());
    }
}
