import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.Buffer;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player testPlayer;
    private CardDeck testDiscardCardDeck;
    private CardDeck testDrawCardDeck;

    PlayerTest() throws IOException {
    }


    @Before
    public void init() throws IOException {

        Card[] testHand = new Card[4];
        int[] handCards = {9,10,11,12};
        for (int i: handCards) {
            Card newCard = new Card(i);
            testHand[i] = newCard;
        }

        Queue<Card> testDiscard = new LinkedList<>();
        int[] discardCards = {5,6,7,8};
        for (int i: discardCards) {
            Card newCard = new Card(i);
            testDiscard.add(newCard);
        }
        Queue<Card> testDraw = new LinkedList<>();
        int[] drawCards = {9,10,11,12};
        for (int i: drawCards) {
            Card newCard = new Card(i);
            testDraw.add(newCard);
        }

        this.testDiscardCardDeck = new CardDeck(testDiscard, "test_filename");
        this.testDrawCardDeck = new CardDeck(testDraw, "test_filename");
        this.testPlayer = new Player(1,testHand,"test_filename");

        testPlayer.setDrawDeck(testDrawCardDeck);
        testPlayer.setDiscardDeck(testDiscardCardDeck);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void drawCard() {
        Assert.assertEquals(9,testPlayer.drawCard().getCardNumber());
    }

    @Test
    void removeCard() {

    }

    @Test
    void isWin() {
    }

    @Test
    void takeTurn() {
    }

    @Test
    void hasWon() {
    }

    @Test
    void run() {
    }

    @Test
    void writeToFile() throws IOException {
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