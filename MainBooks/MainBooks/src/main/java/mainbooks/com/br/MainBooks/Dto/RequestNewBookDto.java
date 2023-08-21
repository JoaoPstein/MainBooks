package mainbooks.com.br.MainBooks.Dto;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import mainbooks.com.br.MainBooks.Models.Book;

public class RequestNewBookDto {

    @NotNull
    @Digits(integer = 5, fraction = 1)
    private int BookCode;
    @NotBlank
    @NotNull
    private String Title;
    @NotBlank
    @NotNull
    private String AuthorsName;
    @NotNull
    @Digits(integer = 10, fraction = 5)
    private int ISBN;
    @NotBlank
    @NotNull
    private String PublishingCompany;

    public int getBookCode() {
        return BookCode;
    }

    public void setBookCode(int bookCode) {
        BookCode = bookCode;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthorsName() {
        return AuthorsName;
    }

    public void setAuthorsName(String authorsName) {
        AuthorsName = authorsName;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getPublishingCompany() {
        return PublishingCompany;
    }

    public void setPublishingCompany(String publishingCompany) {
        PublishingCompany = publishingCompany;
    }

    public Book toBook() {
        Book book = new Book();

        book.setAuthorsName(this.AuthorsName);
        book.setBookCode(this.BookCode);
        book.setISBN(this.ISBN);
        book.setPublishingCompany(this.PublishingCompany);
        book.setTitle(this.Title);

        return book;
    }

    public Book fromBook(Book book) {
        this.AuthorsName = book.getAuthorsName();
        this.BookCode = book.getBookCode();
        this.ISBN = book.getISBN();
        this.PublishingCompany = book.getPublishingCompany();
        this.Title = book.getTitle();

        return book;
    }

    @Override
    public String toString() {
        return "RequestNewBookDto{" +
                "BookCode=" + BookCode +
                ", Title='" + Title + '\'' +
                ", AuthorsName='" + AuthorsName + '\'' +
                ", ISBN=" + ISBN +
                ", PublishingCompany='" + PublishingCompany + '\'' +
                '}';
    }
}
