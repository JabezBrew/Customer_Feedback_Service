package feedbackservice;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping()
    public ResponseEntity<?> sendFeedback(@RequestBody @Valid Feedback feedback) {
        feedbackService.saveFeedback(feedback);
        return ResponseEntity.status(201)
                .header("Location", "/feedback/" + feedback.getId())
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFeedback(@PathVariable String id) {
        return ResponseEntity.ok(feedbackService.returnFeedback(id));
    }

    @GetMapping()
    public ResponseEntity<?> getAllFeedbacks(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int perPage,
            @RequestParam(required = false) String rating,
            @RequestParam(required = false) String customer,
            @RequestParam(required = false) String product,
            @RequestParam(required = false) String vendor
    ) {
        Page<Feedback> feedbacks = feedbackService.returnAllFeedbacks(page, perPage, rating, customer, product, vendor);
        return ResponseEntity.ok(CustomPageResponse.convert(feedbacks));
    }
}
