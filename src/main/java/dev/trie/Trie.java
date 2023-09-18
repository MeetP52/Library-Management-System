package dev.trie;


import java.util.*;

public class Trie {
    private final TrieNode root;

    public Trie() {
        this.root = new TrieNode('0',false);
    }

    public void insert(String value, int bookId) {
        String [] values = value.split("\\s+");
        TrieNode node;
        char ch;
        for(String word : values) {
            node = root;
            for (int i = 0; i < word.length(); i++) {
                ch = Character.toLowerCase(word.charAt(i));
                if (node != null && node.getChildNodes().containsKey(ch)) {
                    node = node.getChildNodes().get(ch);
                } else if (node != null) {
                    node.getChildNodes().put(ch, new TrieNode(ch, i == word.length() - 1));
                    node = node.getChildNodes().get(ch);
                }
            }
            if(node != null &&node.isWordEndChar()) {
                node.addBook(bookId);
            } else if (node != null) {
                node.setWordEndChar(true);
                node.addBook(bookId);
            }
        }
    }

    public Set<Integer> find(String value) {
        String [] values = value.split("\\s+");
        TrieNode node = root;
        char ch;
        for(String word : values) {
            for (int i = 0; i < word.length(); i++) {
                ch = Character.toLowerCase(word.charAt(i));
                if (node.getChildNodes().containsKey(ch)) {
                    node = node.getChildNodes().get(ch);
                } else {
                    break;
                }
            }
        }
        return node.getBooks();
    }

    public boolean remove(String value, int bookID) {
        String [] values = value.split("\\s+");
        TrieNode node;
        char ch;
        for(String word : values) {
            node = root;
            for(int i = 0; i < word.length(); i++) {
                ch = Character.toLowerCase(word.charAt(i));
                if (node.getChildNodes().containsKey(ch)) {
                    node = node.getChildNodes().get(ch);
                } else {
                    insert(value,bookID);
                    return false;
                }
            }
            if(node.contains(bookID) && (!node.removeBook(bookID))) {
                System.out.println("Issue removing a book from search engine.");
            }
        }
        return true;
    }
}
