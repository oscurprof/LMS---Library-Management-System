package book;

import java.io.Serializable;

public class Book implements Serializable {
    private String isbn,title,edition,author,genre,description,language,location;
    private int copies;
    private double fine;

    public String getStatus(){
        if (this.copies>0)
            return "Available";
        else return "Unavailable";
    }
    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getEdition() {
        return edition;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public String getLocation() {
        return location;
    }

    public int getCopies() {
        return copies;
    }

    public double getFine() {
        return fine;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }
    public void printAll(){
        System.out.println("\nISBN: "+this.isbn+"\nTitle: "+this.title+"\nEdition: "+this.edition+"\nGenre: "+this.genre+"\nDescription: "+this.description+"\nAuthor: "+this.author+"\nLanguage: "+this.language+"\nCopies Available: "+this.copies+"\nFine: "+this.fine+"\nLocation: "+this.location);
    }
}
