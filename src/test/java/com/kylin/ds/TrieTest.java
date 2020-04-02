package com.kylin.ds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.kylin.ds.model.Trie;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TrieTest {

    private Trie trie;
    
    @Test
    public void testInsertWordsIntoTrieCorrectly() {
        trie = new Trie();
        String[] words = new String[] {"apple", "app", "orange", "oranges"};
        trie.insert(words);
        assertEquals(trie.getNumWords(), 4);
        trie.insert("apple");
        assertEquals(trie.getNumWords(), 4);
        trie.insert("apply");
        assertEquals(trie.getNumWords(), 5);
    }

    @Test
    public void testSearchWord() {
        trie = new Trie();
        String[] words = new String[] {"apple", "apply", "app", "orange", "oranges"};
        trie.insert(words);
        assertTrue(trie.searchCompleteWord("app"));
        assertFalse(trie.searchCompleteWord("apps"));
        assertFalse(trie.searchCompleteWord("orangecounty"));
        assertTrue(trie.searchBlurredWord("oran.."));
        assertTrue(trie.searchBlurredWord("orang.."));
        assertFalse(trie.searchBlurredWord("orang..."));
        assertTrue(trie.searchBlurredWord(".pp.."));
        
    }



}

