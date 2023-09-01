package dev.book;

import java.util.HashSet;
import java.util.Set;

public class Book {
    private String title;
    private String series;
    private String description;
    private Set<String> authors;
    private Set<String> genres;

    public Book() {
        this.authors = new HashSet<>();
        this.genres = new HashSet<>();
    }

    public Book(String title, String series, String description, Set<String> authors, Set<String> genres) {
        this.title = title;
        this.series = series;
        this.description = description;
        this.authors = authors;
        this.genres = genres;
    }

    protected Book(Book book) {
        this.title = book.title;
        this.series = book.series;
        this.description = book.description;
        this.authors = Set.copyOf(book.authors);
        this.genres = Set.copyOf(book.genres);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<String> authors) {
        this.authors = authors;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    public Book copy(String type) {
        if(type.equals("deep")) {
            return new Book(this);
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (!getTitle().equals(book.getTitle())) return false;
        if (!getSeries().equals(book.getSeries())) return false;
        if (!getAuthors().equals(book.getAuthors())) return false;
        return getGenres().equals(book.getGenres());
    }

    @Override
    public int hashCode() {
        int result = getTitle().hashCode();
        result = 31 * result + getSeries().hashCode();
        result = 31 * result + getAuthors().hashCode();
        result = 31 * result + getGenres().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Book {" +
                "\n\t title= '" + title + '\'' +
                ",\n\t series= '" + series + '\'' +
                ",\n\t description= '" + description + '\'' +
                ",\n\t authors= " + authors +
                ",\n\t genres= " + genres + "\n\t" +
                '}';
    }
}
