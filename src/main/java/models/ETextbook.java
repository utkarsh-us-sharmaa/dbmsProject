package models;

public class ETextbook {
    private int textbookID;
    private String title;
    private String textContent;
    private String imageURL;

    // Default Constructor
    public ETextbook() {
    }

    // Parameterized Constructor
    public ETextbook(int textbookID, String title, String textContent, String imageURL) {
        this.textbookID = textbookID;
        this.title = title;
        this.textContent = textContent;
        this.imageURL = imageURL;
    }

    // Getters and Setters
    public int getTextbookID() {
        return textbookID;
    }

    public void setTextbookID(int textbookID) {
        this.textbookID = textbookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
