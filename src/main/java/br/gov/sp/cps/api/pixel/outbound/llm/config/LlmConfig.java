package br.gov.sp.cps.api.pixel.outbound.llm.config;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LlmConfig {

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    @Value("${spring.ai.openai.base-url}")
    private String url;

    @Value("${spring.ai.openai.chat.model}")
    private String model;

    @Value("${prompt.token}")
    private Integer tokens;

    public OpenAiChatModel executar(){
        var openAiApi = new OpenAiApi(url, apiKey);
        var openAiChatOptions = OpenAiChatOptions.builder()
                .withModel(model)
                .withMaxTokens(tokens)
                .build();
        return new OpenAiChatModel(openAiApi, openAiChatOptions);
    }
}
