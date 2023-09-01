package dev.book;

public class BookCatalogItem {
    private Book book;
    private int quantity;

    public BookCatalogItem() {};

    public BookCatalogItem(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    public BookCatalogItem(Book book) {
        if(!this.equals(book)) {
            this.book = book.copy("deep");
            this.quantity = 20;
        }
    }

    public BookCatalogItem(BookCatalogItem item) {
        this.book = item.book.copy("deep");
        this.quantity = item.quantity;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookCatalogItem that = (BookCatalogItem) o;

        return getBook().equals(that.getBook());
    }

    @Override
    public int hashCode() {
        return getBook().hashCode();
    }

    public BookCatalogItem copy(String type) {
        if(type.equals("deep")) {
            return new BookCatalogItem(this);
        }
        return this;
    }

    @Override
    public String toString() {
        return "Item {" +
                "\n\t" + book +
                ",\n\t quantity = " + quantity + "\n" +
                '}';
    }
}
