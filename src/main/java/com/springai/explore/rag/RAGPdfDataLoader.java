package com.springai.explore.rag;


import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.reader.tika.TikaDocumentReader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RAGPdfDataLoader {

    private  final VectorStore vectorStore;
    @Value("classpath:JavaExplore_employee_handbook_rag_demo.pdf")
    Resource pdfResource;
    public RAGPdfDataLoader(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @PostConstruct
    public void loadPdfDataToVectorStore(){

        TikaDocumentReader tikaDocumentReader = new TikaDocumentReader(pdfResource);
        List<Document> documents=tikaDocumentReader.get();

       TextSplitter textSplitter = TokenTextSplitter.builder()
        .withChunkSize(100)
                .withMaxNumChunks(400)
                .build();

        List<Document> documentChunks=textSplitter.split(documents);
        vectorStore.add(documentChunks);
         }

}
