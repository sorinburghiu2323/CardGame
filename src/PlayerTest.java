import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Test class for Player.
 */
public class PlayerTest {

    private Player testPlayer;

    /**
     * Mock object initializer for Player.
     * Given a chosen array of numbers for each of the following: hand, discardDeck, drawDeck,
     * test the following methods.
     *
     * @throws IOException - when writing to file is not possible.
     */
    @Before
    public void init() throws IOException {

        int[] handCards = {1,1,1,2};
        int[] discardCards = {3,3,2,1};
        int[] drawCards = {3,1,1,3};

        Card[] testHand = new Card[4];
        for (int i = 0; i < 4; i++) {
            Card newCard = new Card(handCards[i]);
            testHand[i] = newCard;
        }

        Queue<Card> testDiscard = new LinkedList<>();
        for (int i: discardCards) {
            Card newCard = new Card(i);
            testDiscard.add(newCard);
        }
        Queue<Card> testDraw = new LinkedList<>();
        for (int i: drawCards) {
            Card newCard = new Card(i);
            testDraw.add(newCard);
        }

        // Set all mock objects.
        CardDeck testDiscardCardDeck = new CardDeck(testDiscard, "test_filename");
        CardDeck testDrawCardDeck = new CardDeck(testDraw, "test_filename");
        this.testPlayer = new Player(1,testHand,"test_filename");
        testPlayer.setDrawDeck(testDrawCardDeck);
        testPlayer.setDiscardDeck(testDiscardCardDeck);
    }

    /**
     * Test for drawCard method.
     * Check to see if the expected output matches the number of the card the player draws.
     */
    @Test
    public void drawCardTest() {
        int expectedDraw = 3;
        Assert.assertEquals(expectedDraw, testPlayer.drawCard().getCardNumber());
    }

    /**
     * Test for removeCard method.
     * Discards card from players hand and adds it to the respective discard deck.
     * Checks the new contents of the discard deck.
     */
    @Test
    public void removeCardTest() {

        int[] discardAfterNumbers = {3,3,2,1,2};

        Card[] discardDeckAfter = new Card[5];
        for (int i = 0; i < 5; i++) {
            Card newCard = new Card(discardAfterNumbers[i]);
            discardDeckAfter[i] = newCard;
        }

        // Remove Card(2) from player's hand.
        testPlayer.removeCard(new Card(2));

        Assert.assertEquals("Deck: " + Arrays.toString(discardDeckAfter), testPlayer.getDiscardDeck().toString());
    }

    /**
     * Test for isWin method.
     * Using the instantiated player's hand in the mock object check to see if it
     * returns the appropriate boolean.
     *
     * Note: In this case, we know the player's hand is not a winning hand, thus
     *       the expected output is 'false'.
     *       Hand can be modified to test for 'true'.
     */
    @Test
    public void isWinTest() {
        boolean expectedOutcome = false;
        Assert.assertEquals(expectedOutcome, testPlayer.isWin());
    }

    /**
     * Test for takeTurn method.
     * Chose the hand after and compare it to the hand after the method call.
     *
     * Note: Hard to test as the card discarded from player's hand is chosen at random.
     *       In this case we know the Card(2) will get discarded, so we can test.
     *       Modifying the mock hand might cause the test to fail due to the randomness.
     *
     * @throws IOException - when writing to file is not possible.
     */
    @Test
    public void takeTurnTest() throws IOException {

        // Predicted new hand after taking turn.
        int[] handAfterNumbers = {1,1,1,3};

        Card[] handAfter = new Card[4];
        for (int i = 0; i < 4; i++) {
            Card newCard = new Card(handAfterNumbers[i]);
            handAfter[i] = newCard;
        }
        testPlayer.takeTurn();

        Assert.assertEquals(Arrays.toString(handAfter), Arrays.toString(testPlayer.getHand()));
    }

    /**
     * Test for hasWon method.
     * Check if the playerID is the same as the winner after the method call.
     *
     * @throws IOException - when writing to file is not possible.
     */
    @Test
    public void hasWonTest() throws IOException {
        testPlayer.hasWon();
        Assert.assertEquals(testPlayer.getPlayerID(), testPlayer.getWinner());
    }

    /**
     * Test for writeToFile method.
     * Creates a proxy file "tempFile.txt" and writes to it certain string values.
     * Afterwords, the file is read to see if it matches the string values that were
     * written to it accordingly.
     *
     * @throws IOException - when writing to file is not possible.
     */
    @Test
    public void writeToFileTest() throws IOException {
        File file;
        try{
            file = File.createTempFile("tempFile.txt",null);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        BufferedReader reader = new BufferedReader(new FileReader(file));
        file.deleteOnExit();
        try {
             writer.write("Test");
             writer.newLine();
             writer.flush();
             writer.write("Test2");
             Assert.assertEquals("Test",reader.readLine());
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            file.exists();
        }
    }
}