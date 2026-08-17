package com.springai.explore.config;

import com.springai.explore.rag.SimpleFilteringPostProcessor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.preretrieval.query.transformation.TranslationQueryTransformer;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatClientConfig {

    @Bean
    public ChatMemory chatMemory(JdbcChatMemoryRepository jdbcChatMemoryRepository){
       return  MessageWindowChatMemory.builder()
                .maxMessages(5)
                .chatMemoryRepository(jdbcChatMemoryRepository)
                .build();
    }
    @Bean
    public RetrievalAugmentationAdvisor retrievalAugmentationAdvisor(VectorStore vectorStore,
                                                                     ChatClient.Builder chatClientBuilder){
        return  RetrievalAugmentationAdvisor.builder()
                //before retrieval
                .queryTransformers(TranslationQueryTransformer.builder()
                        .chatClientBuilder(chatClientBuilder)
                        .targetLanguage("english")
                        .build())
                .documentRetriever(VectorStoreDocumentRetriever.builder()
                        .vectorStore(vectorStore)
                        .topK(3)
                        .similarityThreshold(0.5)
                        .build())
                .documentPostProcessors(new SimpleFilteringPostProcessor())
                .build();
    }

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder,
                                 ChatMemory chatMemory,
                                 RetrievalAugmentationAdvisor retrievalAugmentationAdvisor){

        SimpleLoggerAdvisor loggerAdvisor=  new SimpleLoggerAdvisor();

        ///new Token
        MessageChatMemoryAdvisor memoryAdvisor =  MessageChatMemoryAdvisor
              .builder(chatMemory)
                .build();

        return  chatClientBuilder
                .defaultAdvisors(List.of(loggerAdvisor,memoryAdvisor,retrievalAugmentationAdvisor))
                .build();
    }
}
