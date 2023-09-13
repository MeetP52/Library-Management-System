package dev.book;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import dev.trie.Trie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class BookCatalog {
    private final Map<Integer,BookCatalogItem> books;
    private static BookCatalog catalog;
    private Trie titleTrie;
    private Trie seriesTrie;
    private Trie authorTrie;
    private Trie genreTrie;

    public BookCatalog() {
        this.books = new HashMap<>();
        generateBookData();
        setupTries();
    }

    public static BookCatalog getBookCatalog() {
        return (catalog == null) ? (catalog = new BookCatalog()) : catalog;
    }

    private void setupTries() {
        titleTrie = new Trie();
        seriesTrie = new Trie();
        authorTrie = new Trie();
        genreTrie = new Trie();

        books.forEach((bookId,book) -> {
            titleTrie.insert(book.getBook().getTitle(),bookId);
            seriesTrie.insert(book.getBook().getSeries(),bookId);
            book.getBook().getAuthors().forEach(author -> {
                authorTrie.insert(author,bookId);
            });
            book.getBook().getGenres().forEach(genre -> {
                genreTrie.insert(genre,bookId);
            });
        });
    }

    public void generateBookData() {
        String absFilePath = "E:\\Code\\Intellij\\User\\src\\main\\java\\docs\\book\\data2.txt";

        try(FileReader reader = new FileReader(absFilePath)) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(bufferedReader);

            Iterator<Map.Entry<String, JsonNode>> fields = rootNode.fields();
            while (fields.hasNext()) {
                Map.Entry<String,JsonNode> entry = fields.next();
                // int bookId = Integer.parseInt(entry.getKey());
                BookCatalogItem bookCatalogItem = mapper.treeToValue(entry.getValue(),BookCatalogItem.class);
                if(books.put(bookCatalogItem.hashCode(),bookCatalogItem) != null) {
                    System.out.println("Inserted book: " + bookCatalogItem.getBook().getTitle());
                }
            }
        } catch (IOException fnfe) {
            System.out.println(fnfe.getMessage());
        }
    }

    public boolean storeBookCatalogData() {
        String absFilePath = "E:\\Code\\Intellij\\User\\src\\main\\java\\docs\\book\\BookData.txt";

        try(FileWriter writer = new FileWriter(absFilePath)) {
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter jsonWriter = mapper.writerWithDefaultPrettyPrinter();
            jsonWriter.writeValue(writer,books);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public int findBookId(Book book) {
        return book.hashCode();
    }

    public Book findBook(int bookId) {
        return (books.get(bookId) != null) ? books.get(bookId).getBook().copy("deep") : null;
    }

    public Book findBook(Book book) {
        return (!books.containsKey(findBookId(book))) ? null : findBook(findBookId(book));
    }

    public List<Book> findBook(String value) {
        Set<Integer> ids = titleTrie.find(value);
        if(ids.isEmpty()) {
            ids = seriesTrie.find(value);
        }
        if(ids.isEmpty()) {
            ids = authorTrie.find(value);
        }
        if(ids.isEmpty()) {
            ids = genreTrie.find(value);
        }
        if(ids.isEmpty()) {
            return new LinkedList<>();
        }
        List<Book> results = new LinkedList<>();
        ids.forEach(i -> {
            if(books.containsKey(i)) {
                results.add(books.get(i).getBook().copy("deep"));
            }
        });
        return results;
    }

    public BookCatalogItem findBookItem(Book book) {
       return (findBook(findBookId(book)) == null) ? null : books.get(findBookId(book)).copy("deep");
    }

    public boolean addBook(Book book) {
        if(books.containsKey(findBookId(book))) {
            return false;
        }
        books.put(book.hashCode(),new BookCatalogItem(book));
        addTrieNode(book,book.hashCode());
        return true;
    }

    public boolean removeBook(Book book) {
        if(!books.containsKey(book.hashCode())) {
            return false;
        }
        books.remove(book.hashCode(),books.get(book.hashCode()));
        removeTrieNode(book,book.hashCode());
        return true;
    }

    public boolean addTrieNode(Book book, int id) {
        titleTrie.insert(book.getTitle(),id);
        seriesTrie.insert(book.getSeries(),id);
        book.getAuthors().forEach(author -> {
            authorTrie.insert(author,id);
        });
        book.getGenres().forEach(genre -> {
            genreTrie.insert(genre,id);
        });
        return true;
    }

    public boolean removeTrieNode(Book book, int id) {
        titleTrie.remove(book.getTitle(),id);
        seriesTrie.remove(book.getSeries(),id);
        book.getAuthors().forEach(author -> {
            authorTrie.remove(author,id);
        });
        book.getGenres().forEach(genre -> {
            genreTrie.remove(genre,id);
        });
        return true;
    }

    public boolean checkout(Book book) {
        if(findBook(book) == null) {
            return false;
        }
       return books.get(findBookId(book)).removeQuantity(1);
    }


    @Override
    public String toString() {
        return "BookCatalog {\n" +
                "books = " + books + "\n" +
                '}';
    }
}
