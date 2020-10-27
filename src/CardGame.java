import java.io.*;
import java.util.*;

public class CardGame{

    private static int playerNumber;
    private static Stack<Card> pack;
   // private static CircularLL gameOrder;
    private static ArrayList<Player> playerArray = new ArrayList<Player>();
    private static ArrayList<CardDeck> cardDeckArray = new ArrayList<CardDeck>();


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

        for (int i =0 ; i<playerArray.size() - 1 ; i++){
            Player temp = playerArray.get(i);
            System.out.println(temp.getNextPlayer());
        }


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
            Player player = new Player(i,makeHand());
            playerArray.add(player);
            CardDeck deck = new CardDeck(i,makeDeck());
            cardDeckArray.add(deck);
        }
        for (int j=0 ; j<=cardDeckArray.size() - 1 ; j++){
            Player tempPlayer = playerArray.get(j);

            if( j == cardDeckArray.size() - 1){
                tempPlayer.setDiscard(cardDeckArray.get(0));
                tempPlayer.setDraw(cardDeckArray.get(j));
                tempPlayer.setNextPlayer(playerArray.get(0));
            }
            else{
                tempPlayer.setDiscard(cardDeckArray.get(j+1));
                tempPlayer.setDraw(cardDeckArray.get(j));
                tempPlayer.setNextPlayer(playerArray.get(j+1));
            }
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
    /*
    public void startGame(){
        Thread player = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    playerArray.get(0).startTurn();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
    }
    public void setPlayerValues(){
        gameOrder.traverse();
    }
    */

}
