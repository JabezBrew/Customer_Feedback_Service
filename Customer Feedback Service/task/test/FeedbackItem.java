import java.util.Random;
import java.util.stream.Stream;

class FeedbackItem {
    private String id;
    private Integer rating;
    private String feedback;
    private String customer;
    private String product;
    private String vendor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
}

class FeedbackItemMother {
    private static final Random rnd = new Random();
    private static final String[] products = {
            "Apple iPhone 13", "Samsung Galaxy S21", "Sony PlayStation 5", "Microsoft Xbox Series X",
            "Bose QuietComfort 35 II Headphones", "Amazon Echo Dot (4th Generation)",
            "Apple MacBook Pro (16 inch, M1 Pro)", "Dyson V11 Torque Drive Cordless Vacuum Cleaner",
            "Instant Pot Duo 7-in-1 Electric Pressure Cooker", "Samsung 55\" Class QLED Q80A Series - 4K UHD TV"
    };
    private static final String[] feedback = {
            "Lorem ipsum dolor sit amet.", "Consectetur adipiscing elit.", "Duis aute irure dolor.",
            "In reprehenderit in voluptate velit.", "Esse cillum dolore eu fugiat.",
            "Nulla pariatur excepteur sint occaecat.", "Cupidatat non proident, sunt in culpa."
    };
    private static final String[] customers = {
            "John Doe", "Jane Smith", "Emily Johnson", "Robert Brown", "Patricia Davis", "Michael Miller",
            "Elizabeth Garcia", "William Martinez", "Jennifer Robinson", "Charles Wilson"
    };
    private static final String[] vendors = {
            "BlueSky Electronics", "Pinnacle Home Appliances", "Quantum Computing Solutions",
            "Nebula Network Devices", "TerraFirma Outdoor Gear"
    };

    private FeedbackItemMother() { }

    public static FeedbackItem create() {
        var item = new FeedbackItem();
        item.setRating(getRandomRating());
        item.setFeedback(feedback[rnd.nextInt(feedback.length)]);
        item.setCustomer(getRandomCustomer());
        item.setProduct(getRandomProduct());
        item.setVendor(getRandomVendor());
        return item;
    }

    public static FeedbackItem noCustomer() {
        var item = create();
        item.setCustomer(null);
        return item;
    }

    public static FeedbackItem noFeedback() {
        var item = create();
        item.setFeedback(null);
        return item;
    }

    public static FeedbackItem noFeedbackNoCustomer() {
        var item = create();
        item.setFeedback(null);
        item.setCustomer(null);
        return item;
    }

    public static FeedbackItem createRandom() {
        var dice = rnd.nextInt(100);
        if (dice < 50) {
            return create();
        } else if (dice < 70) {
            return noFeedback();
        } else if (dice < 90) {
            return noCustomer();
        } else {
            return noFeedbackNoCustomer();
        }
    }

    public static FeedbackItem[] createRandomInRange(int min, int max) {
        return Stream.generate(FeedbackItemMother::createRandom)
                .limit(rnd.nextInt(min, max))
                .toArray(FeedbackItem[]::new);
    }

    public static String getRandomCustomer() {
        return customers[rnd.nextInt(customers.length)];
    }

    public static String getRandomProduct() {
        return products[rnd.nextInt(products.length)];
    }

    public static int getRandomRating() {
        return rnd.nextInt(1, 6);
    }

    public static String getRandomVendor() {
        return vendors[rnd.nextInt(vendors.length)];
    }
}
