package com.springai.explore.controller;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@RestController
@RequestMapping("/rag")
public class RAGController {


    private  final ChatClient chatClient;
    private  final VectorStore vectorStore;

    @Value("classpath:prompts/systemDataPrompt.st")
    private Resource template;

    public RAGController(ChatClient chatClient,
                         VectorStore vectorStore){
        this.chatClient = chatClient;
        this.vectorStore = vectorStore;
    }

    @GetMapping("/connect")
    public  String connectToRagAI(@RequestParam String prompt,
                                  @RequestHeader String username){
                        //RAG flow implementation will go here
// R- Retrieval Query
//        SearchRequest searchRequest=SearchRequest.builder()
//                .query(prompt)
//                .topK(3)
//                .similarityThreshold(0.5)
//                .build();
//
//        //A - Augmentation
//      List<Document> similarDocuments =vectorStore.similaritySearch(searchRequest);
//
//      // extract the text content from the retrieved documents
//        List<String> similarResults=similarDocuments.stream()
//                .map(Document::getText)
//                .toList();

        //G- Generation
        return chatClient.prompt()
//                .system(promptSystemSpec ->
//                        promptSystemSpec
//                                .text(template)
//                                .param("documents",similarResults))
                .advisors(advisorSpec -> advisorSpec.param(CONVERSATION_ID,username))
                .user(prompt)
                .call()
                .content();
//return  results - String;


    }


}
