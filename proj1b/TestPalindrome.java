import org.junit.Test;

import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque<Character> d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testPalindrome() {
        assertFalse(palindrome.isPalindrome("hello"));
        assertTrue(palindrome.isPalindrome("abcba"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("aa"));
        assertFalse(palindrome.isPalindrome("abca"));
        assertFalse(palindrome.isPalindrome("ab"));
        OffByOne offByOne = new OffByOne();
        assertFalse(palindrome.isPalindrome("abcba", offByOne));
        assertTrue(palindrome.isPalindrome("abcab", offByOne));
        assertFalse(palindrome.isPalindrome("hello", offByOne));
        assertTrue(palindrome.isPalindrome("flake", offByOne));
        OffByN offBy5 = new OffByN(5);
        assertFalse(palindrome.isPalindrome("abcba", offBy5));
        assertTrue(palindrome.isPalindrome("a", offBy5));
        assertFalse(palindrome.isPalindrome("hello", offBy5));
        assertTrue(palindrome.isPalindrome("ainf", offBy5));
    }
}
