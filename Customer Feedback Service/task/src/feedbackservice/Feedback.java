package feedbackservice;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "feedback")
@Getter
@Setter
@ToString
public class Feedback {

    @Id
    private String id;
    @Min(1) @Max(5)
    private Integer rating;
    private String feedback;
    private String customer;
    private String product;
    private String vendor;
}
