import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Test class for CardDeck.
 */
public class CardDeckTest {

    private CardDeck testDeck;

    /**
     * Mock object initializer for a CardDeck.
     * Given an array of numbers chosen, make a CardDeck with them and use it
     * to test.
     *
     * @throws IOException - when writing to file is not possible.
     */
    @Before
    public void init() throws IOException {
        Queue<Card> testCards = new LinkedList<>();
        int[] deckCards = {1,2,3,4};
        for (int i: deckCards) {
            Card newCard = new Card(i);
            testCards.add(newCard);
        }
        this.testDeck = new CardDeck(testCards, "test_filename");
    }

    /**
     * Test for addCardToBottom method.
     * Here we add a card at the bottom of the deck and check the outcome.
     */
    @Test
    public void addCardToBottomTest() {

        // Test the method by adding a new Card(2) to the deck.
        testDeck.addCardToBottom(new Card(2));

        Queue<Card> testCards = new LinkedList<>();
        int[] newCards = {1, 2, 3, 4, 2};
        for(int i: newCards) {
            Card newCard = new Card(i);
            testCards.add(newCard);
        }

        Assert.assertEquals(testCards.toString(), testDeck.getDeck().toString());
    }

    /**
     * Test for removeCardFromTop method.
     * Needs to return the top card of the deck.
     */
    @Test
    public void removeCardFromTopTest() {
        int removedExpected = 1;
        Assert.assertEquals(removedExpected, testDeck.removeCardFromTop().getCardNumber());
    }

    /**
     * Test for getTopCard method.
     * Needs to return the top card of the deck.
     */
    @Test
    public void getTopCardTest() {
        int topExpected = 1;
        Assert.assertEquals(topExpected, testDeck.getTopCard().getCardNumber());
    }
}
