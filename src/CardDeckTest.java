import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
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
     * @throws IOException
     */
    @Before
    public void init() throws IOException {
        Queue<Card> testCards = new LinkedList<>();
        int[] numbers = {1,2,3,4};
        for (int i: numbers) {
            Card newCard = new Card(i);
            testCards.add(newCard);
        }
        this.testDeck = new CardDeck(testCards, "test_filename");
    }

    /**
     * Test for removeCardFromTop method.
     *
     * Needs to return the top card of the deck.
     */
    @Test
    public void removeCardFromTopTest() {
        int removedCard = 1;
        Assert.assertEquals(removedCard, testDeck.removeCardFromTop().getCardNumber());
    }

    /**
     * Test for getTopCard method.
     *
     * Needs to return the top card of the deck.
     */
    @Test
    public void getTopCardTest() {
        int topCard = 1;
        Assert.assertEquals(topCard, testDeck.getTopCard().getCardNumber());
    }
}
