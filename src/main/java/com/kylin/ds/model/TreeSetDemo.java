package com.kylin.ds.model;

import java.util.Arrays;
import java.util.TreeSet;

/** Based on https://docs.oracle.com/javase/7/docs/api/java/util/TreeSet.html 
 * 
 */
public class TreeSetDemo {

    public static void main(String[] args) {
        
        TreeSetDemo demo = new TreeSetDemo();
        demo.demoPrintInOrder();
        demo.demoSetOrders();

    }

    /** method demoPrintInOrder()
     * create a sample tree set and print the element in console
     */
    void demoPrintInOrder() {
        System.out.println("--- demoPrintInOrder() start ---");
        String phrase = "the quick brown fox jumps over the lazy dog";
        System.out.println("Phrase: " + phrase);
        String[] phrases = phrase.split(" ");
        TreeSet<String> words = new TreeSet<>();
        words.addAll(Arrays.asList(phrases));
        for (String string : words) {
            System.out.println(string);
        }
        System.out.println("--- demoPrintInOrder() end -----");
    }

    /** method - demoSetOrders
     * demo several comparison methods 
     */
    void demoSetOrders() {

        System.out.println("--- demoSetOrders() start ---");
        Integer[] numbers = new Integer[] {7, 9, 11, 3, 1, 3, 5, 5};
        TreeSet<Integer> set = new TreeSet<>();
        set.addAll(Arrays.asList(numbers));
        System.out.println("raw data: " + Arrays.asList(numbers));
        System.out.println("tree set created: " + set);
        
        /** method first() 
         * Returns the first (lowest) element currently in this set.
         */

        Integer first = set.first();
        System.out.println("first(): " + first);

        /** method last()
         * Returns the last (highest) element currently in this set.
         */
        Integer last = set.last();
        System.out.println("last(): " + last);

        /** method lower(e)
         * Returns the greatest element in this set strictly less than the given element, or null if there is no such element.
         */ 
        System.out.println("lower(6): " + set.lower(6));
        System.out.println("lower(5): " + set.lower(5));
        System.out.println("lower(1): " + set.lower(1));

        /** method floor(e)
         * Returns the greatest element in this set less than or equal to the given element, or null if there is no such element.
         */
        System.out.println("floor(1): " + set.floor(1));
        System.out.println("floor(0): " + set.floor(0));

        /** method ceiling(e)
         * Returns the least element in this set greater than or equal to the given element, or null if there is no such element.
         */

        System.out.println("ceiling(1): " + set.ceiling(1));
        System.out.println("ceiling(11): " + set.ceiling(11));

        /** method higher(e)
         * Returns the least element in this set strictly greater than the given element, or null if there is no such element.
         */
        System.out.println("higher(3): " + set.higher(3));
        System.out.println("higher(11): " + set.higher(11));

        System.out.println("--- demoSetOrders() end -----");
        
    }
}