package dev.trie;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TrieNode {
    private char ch;
    private boolean wordEndChar;
    private final Map<Character, TrieNode> childNodes;
    private Set<Integer> books;

    public TrieNode() {
        this.childNodes = new HashMap<>();
        this.books = new HashSet<>();
    }

    public TrieNode(char ch, boolean wordEndChar) {
        this.ch = ch;
        this.wordEndChar = wordEndChar;
        this.childNodes = new HashMap<>();
        this.books = new HashSet<>();
    }

    private TrieNode(TrieNode node) {
        this.ch = node.ch;
        this.wordEndChar = node.wordEndChar;
        this.childNodes = Map.copyOf(node.childNodes);
        this.books = Set.copyOf(node.books);
    }

    public char getCh() {
        return ch;
    }

    public boolean isWordEndChar() {
        return wordEndChar;
    }

    public void setWordEndChar(boolean val) {
        this.wordEndChar = val;
    }

    public Map<Character, TrieNode> getChildNodes() {
        return childNodes;
    }

    public Set<Integer> getBooks() {
        return books;
    }

    public void setBooks(Set<Integer> books) {
        this.books = books;
    }

    public boolean addBook(int bookId) {
        return this.books.add(bookId);
    }

    public boolean removeBook(int bookId) {
        return this.books.remove(bookId);
    }

    public boolean contains(int bookId) {
        return this.books.contains(bookId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrieNode trieNode = (TrieNode) o;

        return getCh() == trieNode.getCh();
    }

    public TrieNode copy(String type) {
        if(type.equals("deep")) {
            return new TrieNode(this);
        }
        return this;
    }

    @Override
    public int hashCode() {
        int result = getCh();
        result = 31 * result + (isWordEndChar() ? 1 : 0);
        result = 31 * result + getChildNodes().hashCode();
        result = 31 * result + getBooks().hashCode();
        return result;
    }

    @Override
    public String toString() {
        String tab = "\n\t";
        return "TrieNode {" + "\n\t" +
                "ch = " + ch + "," + tab +
                "wordEndChar = " + wordEndChar + "," + tab +
                "childNodes = " + childNodes + "," + tab +
                '}';
    }
}
