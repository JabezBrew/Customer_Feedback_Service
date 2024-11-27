package feedbackservice;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DatabaseInitializer {


    private final MongoTemplate mongoTemplate;

    public DatabaseInitializer(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @PostConstruct
    public void init(){
        System.out.println("collection dropped!");
        mongoTemplate.dropCollection("feedback");
    }
}
