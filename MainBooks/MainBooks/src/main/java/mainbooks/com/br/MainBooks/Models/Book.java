package mainbooks.com.br.MainBooks.Models;

import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(nullable = true)
    private int BookCode;
    @Column(nullable = true)
    private String Title;
    @Column(nullable = true)
    private String AuthorsName;
    @Column(nullable = true)
    private int ISBN;
    @Column(nullable = true)
    private String PublishingCompany;

    public Book(int bookCode, String title, String authorsName, int ISBN, String publishingCompany) {
        BookCode = bookCode;
        Title = title;
        AuthorsName = authorsName;
        this.ISBN = ISBN;
        PublishingCompany = publishingCompany;
    }

    public Book() { }

    public Long getId() {
        return Id;
    }
    public void setId(Long id) {
        Id = id;
    }
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

    @Override
    public String toString() {
        return "Book{" +
                "Id=" + Id +
                ", BookCode=" + BookCode +
                ", Title='" + Title + '\'' +
                ", AuthorsName='" + AuthorsName + '\'' +
                ", ISBN=" + ISBN +
                ", PublishingCompany='" + PublishingCompany + '\'' +
                '}';
    }
}
