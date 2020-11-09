import java.io.*;
import java.util.*;

/**
 * Main runner class of the card game.
 * Initializes the game by:
 *      - Reading a pack file and creating an adequate pack stack.
 *      - Creating players for the game.
 *      - Creating card decks for the game
 *      - Distributing cards to players and decks.
 *      - Start the game by starting the Player threads.
 */
public class CardGame {

    private static int playerNumber;
    private static Stack<Card> pack;  // Pack read from given file.
    private static final ArrayList<Player> playerArray = new ArrayList<Player>();
    private static final ArrayList<CardDeck> cardDeckArray = new ArrayList<CardDeck>();

    /**
     * Main method to run the card game.
     * Consists of input statements to set up the game with a given pack and number
     * of players.
     */
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Card Game Sim!");

        // Get number of players.
        boolean invalidPlayerNumber = false;
        System.out.println("How many players should play the game?");
        try {
            playerNumber = scanner.nextInt();
        } catch (Exception e) {
            invalidPlayerNumber = true;
        }

        if (!invalidPlayerNumber && playerNumber > 0) {

            // Get name of deck file (e.g: pack.txt).
            System.out.println("What deck should they use?");
            String packName = scanner.next();

            // Run the game assuming all the rules to start the game are followed.
            try {
                createPack(packName);
                distributeCards();
                startGame();
                System.out.println("Running game...");
            } catch (NegativeCardValueException | NonIntegerCardValueException |
                    InvalidNumberOfCardsException | FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Player number must be a positive non-zero integer.");
        }
    }

    /**
     * Method to create a pack given the filename.
     * The pack is a stack of Card instances.
     * Cards must be positive integers, thus each line of the file must be a
     * positive integer.
     *
     * @param filename - Name of the file.
     * @throws NegativeCardValueException - when a card has a negative value.
     * @throws NonIntegerCardValueException - when a card has a non-integer value.
     * @throws InvalidNumberOfCardsException - when the number of cards is not 8 * numberOfPlayers.
     * @throws FileNotFoundException - when the path cannot be found from filename.
     */
    public static void createPack(String filename) throws
            NegativeCardValueException, NonIntegerCardValueException,
            InvalidNumberOfCardsException, FileNotFoundException {

        int count = 0;
        File file = new File(filename);  // Throws exception by default if file cannot be found.
        Scanner reader = new Scanner(file);
        pack = new Stack<>();  // Create pack.

        while(reader.hasNextLine()){
            count += 1;
            String data = reader.nextLine();

            // Check if line is a positive integer.
            try {
                Integer.parseInt(data);
                if (Integer.parseInt(data) < 0){
                    throw new NegativeCardValueException("Card values must be a positive int.");
                }
            } catch (Exception e){
                throw new NonIntegerCardValueException("Each line of the file must be a positive integer.");
            }

            // Add card to pack.
            Card newCard = new Card(Integer.parseInt(data));
            pack.push(newCard);
        }
        reader.close();
        if (count == (8 * playerNumber)){
            Collections.shuffle(pack);  // Shuffle the pack.
        } else {
            throw new InvalidNumberOfCardsException("File needs to contain 8 * number of players.");
        }
    }

    /**
     * Create the adequate number of players and decks.
     * Assign them with a hand and a deck respectively.
     *
     * @throws IOException - when writing to file is not possible.
     */
    public static void distributeCards() throws IOException {

        // Distribute cards to players.
        for (int i=1 ; i<=playerNumber; i++){
            Player player = new Player(i, makeHand(), "player" + i + "_output.txt");
            playerArray.add(player);
        }

        // Distribute cards to decks.
        for (int i=1 ; i<=playerNumber; i++){
            CardDeck deck = new CardDeck(makeDeck(), "deck" + i + "_output.txt");
            cardDeckArray.add(deck);
        }

        // Set player discard and draw decks.
        for (int j=0 ; j<=cardDeckArray.size() - 1 ; j++){
            Player tempPlayer = playerArray.get(j);
            if ( j == cardDeckArray.size() - 1){
                tempPlayer.setDiscardDeck(cardDeckArray.get(0));
            }
            else{
                tempPlayer.setDiscardDeck(cardDeckArray.get(j+1));
            }
            tempPlayer.setDrawDeck(cardDeckArray.get(j));
        }
    }

    /**
     * From the pack, create a hand of 4 cards.
     *
     * @return hand - array of Cards.
     */
    public static Card[] makeHand(){
        Card[] hand = new Card[4];
        for (int i=0 ; i < 4; i++){
            hand[i] = pack.pop();
        }
        return hand;
    }

    /**
     * From the pack, create a deck of 4 cards.
     *
     * @return deck - queue of Cards.
     * @throws NullPointerException - when a card cannot be added to the queue.
     */
    public static Queue<Card> makeDeck() throws NullPointerException{
        Queue<Card> deck = new LinkedList<>();
        for(int i = 0; i<4 ; i++){
            deck.add(pack.pop());
        }
        return deck;

    }

    /**
     * Method to start the player threads, thus starting the game.
     */
    public static void startGame(){
        for(int i=0; i<playerNumber; i++) {
            playerArray.get(i).start();
        }
    }
}
