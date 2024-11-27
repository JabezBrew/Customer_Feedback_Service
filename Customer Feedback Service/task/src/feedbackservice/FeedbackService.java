package feedbackservice;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;



@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public void saveFeedback(Feedback feedback) {
        feedbackRepository.save(feedback);
    }

    public Feedback returnFeedback(String id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * Returns a page of feedbacks based on the provided parameters.
     *
     * @param page     The page number (starting from 0).
     * @param perPage  The number of feedbacks per page.
     * @param rating   Filters the feedbacks by rating.
     * @param customer Filters the feedbacks by customer.
     * @param product  Filters the feedbacks by product.
     * @param vendor   Filters the feedbacks by vendor.
     * @return A Page object containing the feedbacks that match the specified criteria.
     */
    public Page<Feedback> returnAllFeedbacks(int page, int perPage, String rating, String customer, String product, String vendor) {
        if (perPage < 5 || perPage > 20) perPage = 10;
        if (page < 0) page = 0;
        int x = (page < 1) ? 0 : 1;

        PageRequest pageRequest = PageRequest.of(page - x, perPage, Sort.by(Sort.Direction.DESC, "id"));
        return findAllFeedbacks(pageRequest, rating, customer, product, vendor);
    }

    /**
     * Finds feedbacks based on the provided parameters.
     *
     * @param pageRequest the page request object specifying the page number, page size, and sort order
     *        of the feedbacks to retrieve
     * @param rating the rating to filter the feedbacks by
     * @param customer the customer to filter the feedbacks by
     * @param product the product to filter the feedbacks by
     * @param vendor the vendor to filter the feedbacks by
     * @return a Page object containing the feedbacks that match the specified criteria
     */
    public Page<Feedback> findAllFeedbacks(PageRequest pageRequest, String rating, String customer, String product, String vendor) {
        Feedback feedbackExample = new Feedback();
        if (rating != null) feedbackExample.setRating(Integer.valueOf(rating));
        if (customer != null) feedbackExample.setCustomer(customer);
        if (product != null) feedbackExample.setProduct(product);
        if (vendor != null) feedbackExample.setVendor(vendor);

        if (filtersHaveAtLeastOneNonNullField(feedbackExample)) { //if filter parameter(s) present, use Example to query
            //System.out.println(feedbackExample);
            Example<Feedback> example = Example.of(feedbackExample);
            return feedbackRepository.findAll(example, pageRequest);
        }
        return feedbackRepository.findAll(pageRequest);
    }

    /**
     * Checks if the provided Feedback object has at least one non-null field.
     *
     * @param feedback The Feedback object to be checked.
     * @return {@code true} if the Feedback object has at least one non-null field, {@code false} otherwise.
     */
    public static boolean filtersHaveAtLeastOneNonNullField(Feedback feedback) {
        for (Field field : feedback.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.get(feedback) != null) {
                    return true;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        }
        return false;
    }


}
