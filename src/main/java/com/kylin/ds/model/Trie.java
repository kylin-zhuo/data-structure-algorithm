package com.kylin.ds.model;

public class Trie {

    class TrieNode {
        
        public char val; // the current char
        public String word = "";
        public boolean isLeaf = false;
        public TrieNode[] children = new TrieNode[26];;
        
        public TrieNode(char val) {
            this.val = val;
        }
    }

    private TrieNode root;

    private int numWords;

    public Trie() {
        root = new TrieNode('*');
        numWords = 0;
    }

    public void insert(String[] words) {
        for (String word : words) {
            insert(word);
        }
    }
    
    /** 
     * Inserts a word into the trie. 
     */
    public void insert(String word) {
        if (word == null) {
            return;
        }
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode(c);
            }
            node = node.children[index];
        }
        if (!node.isLeaf) {
            node.isLeaf = true;
            node.word = word;
            numWords += 1;
        }
        
    }
    
    /** 
     * Returns if the word is in the trie. 
     */
    public boolean searchCompleteWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return false;
            }
            node = node.children[index];
        }
        return node.isLeaf;
    }

    /** 
     * Returns if the word is in the data structure. 
     * A word could contain the dot character '.' to represent any one letter. 
     */
    public boolean searchBlurredWord(String word) {
        return blurredSearchHelper(word, 0, root);
    }

    private boolean blurredSearchHelper(String word, int pos, TrieNode node) {
        if (pos == word.length()) return !node.word.equals("");   
        if (word.charAt(pos) != '.') {
            if (node.children[word.charAt(pos) - 'a'] == null) {
                return false;
            }
            return blurredSearchHelper(word, pos + 1, node.children[word.charAt(pos) - 'a']);
        } else {
            for (int i = 0; i < node.children.length; i++) {
                if (node.children[i] != null) {
                    if (blurredSearchHelper(word, pos + 1, node.children[i])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /** 
     * Returns if there is any word in the trie that starts with the given prefix. 
     */
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return false;
            }
            node = node.children[index];
        }
        return true;
    }

    public int getNumWords() {
        return numWords;
    }

    public void setNumWords(int numWords) {
        this.numWords = numWords;
    }

    public TrieNode getRoot() {
        return root;
    }

}


