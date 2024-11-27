package feedbackservice;

import org.springframework.data.domain.Page;

import java.util.List;

public record CustomPageResponse(
        long total_documents,
        boolean is_first_page,
        boolean is_last_page,
        List<Feedback> documents
        ) {
    public static CustomPageResponse convert(Page<Feedback> pagedFeedbacks) {
        return new CustomPageResponse(
                pagedFeedbacks.getTotalElements(),
                pagedFeedbacks.isFirst(),
                pagedFeedbacks.isLast(),
                pagedFeedbacks.getContent()
        );
    }
}
