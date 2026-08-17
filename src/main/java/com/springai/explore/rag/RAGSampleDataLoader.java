package com.springai.explore.rag;


import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class RAGSampleDataLoader {

    private  final VectorStore vectorStore;


    public RAGSampleDataLoader(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    //@PostConstruct
    public void loadSampleDataToVectorStore(){
        List<String> sampleData=getSampleData();

        List<Document> documents = sampleData.stream()
                                .map(Document::new)
                                        .toList();
        vectorStore.add(documents);
    }


    private List<String> getSampleData(){
        return  List.of(
                "// Technology\n" +
                        "                \"Java is a popular programming language used to build enterprise applications and backend systems.\",\n" +
                        "                \"Spring Boot simplifies Java application development by providing auto configuration and embedded servers.\",\n" +
                        "                \"Docker allows developers to package applications and dependencies into containers.\",\n" +
                        "                \"Kubernetes is used to orchestrate and manage containerized applications at scale.\",\n" +
                        "                \"Vector databases store embeddings which allow semantic search using similarity instead of exact keywords.\",\n" +
                        "\n" +
                        "                // Share Market\n" +
                        "                \"The stock market is a place where investors buy and sell shares of publicly listed companies.\",\n" +
                        "                \"The Nifty 50 is a benchmark stock market index representing 50 major companies listed on the National Stock Exchange of India.\",\n" +
                        "                \"The Sensex is an index of 30 large companies listed on the Bombay Stock Exchange.\",\n" +
                        "                \"Bull market refers to a period when stock prices are rising or expected to rise.\",\n" +
                        "                \"Bear market refers to a period when stock prices are falling or expected to fall.\",\n" +
                        "                \"Investors often diversify their portfolios to reduce risk in the stock market.\",\n" +
                        "                \"Long term investing in fundamentally strong companies can generate significant wealth over time.\",\n" +
                        "\n" +
                        "                // Startup Ecosystem\n" +
                        "                \"A startup is a young company founded to develop a unique product or service and bring it to market.\",\n" +
                        "                \"India has one of the largest startup ecosystems in the world with companies like Flipkart, Zomato and Paytm.\",\n" +
                        "                \"Venture capital firms invest in startups with high growth potential.\",\n" +
                        "                \"Unicorn startups are companies valued at over one billion dollars.\",\n" +
                        "                \"Bangalore is known as the startup capital of India.\",\n" +
                        "\n" +
                        "                // War / Global Situation\n" +
                        "                \"Geopolitical tensions between countries can impact global markets and trade.\",\n" +
                        "                \"Wars often disrupt supply chains and increase commodity prices such as oil and gas.\",\n" +
                        "                \"International organizations like the United Nations work to maintain peace and security worldwide.\",\n" +
                        "                \"Defense budgets often increase during periods of geopolitical conflict.\",\n" +
                        "                \"Global conflicts can affect economic growth and financial stability.\",\n" +
                        "\n" +
                        "                // Cricket\n" +
                        "                \"Cricket is one of the most popular sports in India.\",\n" +
                        "                \"The International Cricket Council or ICC governs international cricket tournaments.\",\n" +
                        "                \"India won the ICC Cricket World Cup in 1983 and 2011.\",\n" +
                        "                \"Sachin Tendulkar is considered one of the greatest cricketers in history.\",\n" +
                        "                \"Virat Kohli is known for his aggressive batting style and leadership.\",\n" +
                        "                \"The Indian Premier League or IPL is one of the richest cricket leagues in the world.\",\n" +
                        "                \"MS Dhoni led India to victory in the 2007 T20 World Cup and the 2011 ODI World Cup.\",\n" +
                        "\n" +
                        "                // Economy\n" +
                        "                \"Inflation refers to the increase in prices of goods and services over time.\",\n" +
                        "                \"Central banks raise interest rates to control inflation.\",\n" +
                        "                \"Gross Domestic Product or GDP measures the total economic output of a country.\",\n" +
                        "                \"Economic growth is often driven by innovation, investments and consumer spending.\",\n" +
                        "\n" +
                        "                // General Knowledge\n" +
                        "                \"Artificial Intelligence enables machines to simulate human intelligence and decision making.\",\n" +
                        "                \"Machine learning is a subset of artificial intelligence where systems learn from data.\",\n" +
                        "                \"Cloud computing allows applications to run on remote servers instead of local machines.\",\n" +
                        "                \"Cybersecurity protects systems and data from digital attacks.\",\n" +
                        "                \"Renewable energy sources include solar, wind and hydroelectric power.\",\n" +
                        "\n" +
                        "                // Youtube - javaTechie\n" +
                        "                \"Java Techie is a YouTube channel focused on Java, Spring Boot, Microservices, and System Design tutorials.\",\n" +
                        "                \"Java Techie provides practical tutorials on Kafka, Kubernetes, gRPC, and Spring Boot.\",\n" +
                        "                \"The Java Techie channel helps developers prepare for technical interviews and learn backend development.\",\n" +
                        "                \"Yell is the creator of Java Techie YouTube channel.\" "
        );
    }
}
