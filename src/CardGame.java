import java.io.*;
import java.util.*;

public class CardGame{

    private static int playerNumber;
    private static Stack<Card> pack;
    private static CircularLL gameOrder;


    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Card Game Sim!");
        System.out.println("How many players should play the game?");
        playerNumber = scanner.nextInt();

        System.out.println("What deck should they use?");
        String packName = scanner.next();
        Boolean isValidPack = cardPack(packName);
        if (!isValidPack) {
            System.out.println("Please add correct pack.");
        }
        distributeCards();


    }

    public static Boolean cardPack(String filename) throws FileNotFoundException {
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
                        throw new ArithmeticException("Card values must be a positive int");
                    }
                } catch (Exception e){
                    throw new FileNotFoundException("File was not found");
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

    public static void distributeCards() {

        for (int i=0 ; i<playerNumber; i++){
            Player player = new Player(makeHand());
            gameOrder.addNode(player);
            System.out.println(player.toString());
            CardDeck deck = new CardDeck(makeDeck());
            gameOrder.addNode(deck);
            System.out.println(deck.toString());
        }
    }

    public static Card[] makeHand(){
        Card[] hand = new Card[4];
        for (int i=0 ; i < 4; i++){
            hand[i] = pack.pop();
        }
        return hand;
    }
    public static Queue<Card> makeDeck() throws NullPointerException{
        Queue<Card> deck = new LinkedList<Card>();
        for(int i = 0; i<4 ; i++){
            deck.add(pack.pop());
        }
        return deck;

    }
}
