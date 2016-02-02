/**
 * Created by Administrator on 2/1/2016.
 */

public class Book
{
    private int bookID;
    private String title;
    private String author;
    private String imageUrl;

    public int getBookID() {
        return bookID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
