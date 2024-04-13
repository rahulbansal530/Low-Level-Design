import java.util.Date;

public class BookItem extends Book {
    private String id;
    private Rack rack;
    private Date issueDate;
    private Date publicationDate;
    private boolean isAvailable;
    private double price;

    
    public BookItem(String isbn, String title, String author, String publisher, 
    String id, Rack rack, Date issueDate, Date publicationDate, boolean isAvailable, double price) {
        super(isbn, title, author, publisher);
        this.id = id;
        this.rack = rack;
        this.issueDate = issueDate;
        this.publicationDate = publicationDate;
        this.isAvailable = isAvailable;
        this.price = price;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Rack getRack() {
        return rack;
    }
    public void setRack(Rack rack) {
        this.rack = rack;
    }
    public Date getIssueDate() {
        return issueDate;
    }
    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }
    public Date getPublicationDate() {
        return publicationDate;
    }
    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    
}
