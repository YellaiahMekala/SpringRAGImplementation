package com.springai.explore.rag;

import org.jetbrains.annotations.NotNull;
import org.springframework.ai.document.Document;
import org.springframework.ai.rag.Query;
import org.springframework.ai.rag.postretrieval.document.DocumentPostProcessor;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class SimpleFilteringPostProcessor implements DocumentPostProcessor {
    @Override
    public List<Document> process(Query query, List<Document> documents) {
        System.out.println("using simple filter in post processing to filter documents:");
                        return documents.stream()
                .filter( doc -> !doc.getText().contains("Company Overview"))
                .toList();
    }

    @Override
    public List<Document> apply(Query query, List<Document> documents) {
        return DocumentPostProcessor.super.apply(query, documents);
    }

    @NotNull
    @Override
    public <V> BiFunction<Query, List<Document>, V> andThen(@NotNull Function<? super List<Document>, ? extends V> after) {
        return DocumentPostProcessor.super.andThen(after);
    }
}
