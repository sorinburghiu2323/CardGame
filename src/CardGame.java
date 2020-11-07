import java.io.*;
import java.util.*;

public class CardGame{

    private static int playerNumber;
    private static Stack<Card> pack;
    private static final ArrayList<Player> playerArray = new ArrayList<Player>();
    private static final ArrayList<CardDeck> cardDeckArray = new ArrayList<CardDeck>();

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Card Game Sim!");

        // Get number of players.
        System.out.println("How many players should play the game?");
        playerNumber = scanner.nextInt();

        // Get name of deck file (e.g: pack.txt).
        System.out.println("What deck should they use?");
        String packName = scanner.next();

        // Check the validity of the pack; start game if it's valid.
        Boolean isValidPack = cardPack(packName);
        if (!isValidPack) {
            System.out.println("Please add correct pack.");
        } else {
            distributeCards();
            startGame();
            System.out.println("Running game...");
        }
    }

<<<<<<< HEAD
    /**
     * @param filename
     * @return
     * @throws FileNotFoundException
     * cardPack method will read the pack provided by the user, the data inside the file
     * will be read and checked if valid. If the data provided is valid it will be loaded
     * into an ArrayList as Cards then shuffled for cards to be randomised.
     */
    public static Boolean cardPack(String filename) throws FileNotFoundException {
=======
    public static Boolean cardPack(String filename) throws ArithmeticException {
>>>>>>> 77f0ff54815207040d30fac9fe6e3cba6d83c995
        int count = 0;
        try {
            File file = new File(filename);
            Scanner reader = new Scanner(file);
            pack = new Stack<>();  // Create pack.

            while(reader.hasNextLine()){
                count += 1;
                String data = reader.nextLine();

                // Check if line is an integer.
                try {
                    Integer.parseInt(data);
                    if (Integer.parseInt(data) < 0){
                        throw new ArithmeticException("Card values must be a positive int.");
                    }
                } catch (Exception e){
                    throw new ArithmeticException("Each line of the file must be a positive integer.");
                }

                // Add card to pack.
                Card newCard = new Card(Integer.parseInt(data));
                pack.push(newCard);
            }
            reader.close();
            if (count == (8 * playerNumber)){
                Collections.shuffle(pack);
                return true;
            }
            else {
                System.out.println("File needs to contain 8 * number of players.");
                return false;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return false;
        }
    }

    /**
     * @throws IOException
     * The distributeCards method will distribute cards to the players in round robin fashion,
     * then use the remaining cards to distribute them to the decks.
     */
    public static void distributeCards() throws IOException {

        // Create player and cardDeck arrays.
        for (int i=1 ; i<=playerNumber; i++){
            Player player = new Player(i, makeHand(), "player" + i + "_output.txt");
            playerArray.add(player);
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
     * @return
     */
    public static Card[] makeHand(){
        Card[] hand = new Card[4];
        for (int i=0 ; i < 4; i++){
            hand[i] = pack.pop();
        }
        return hand;
    }

    /**
     * @return
     * @throws NullPointerException
     */
    public static Queue<Card> makeDeck() throws NullPointerException{
        Queue<Card> deck = new LinkedList<Card>();
        for(int i = 0; i<4 ; i++){
            deck.add(pack.pop());
        }
        return deck;

    }

    public static void startGame(){
        for(int i=0; i<playerNumber; i++) {
            playerArray.get(i).start();
        }
    }
}
