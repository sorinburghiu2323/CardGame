import javax.sound.midi.Soundbank;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CardGame{

    private static int playerNumber;
    private static ArrayList<Card> pack;


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

        for (int i=0; i<playerNumber; i++) {

        }
    }

    public static Boolean cardPack(String filename) throws FileNotFoundException {
        int count = 0;
        try {
            File file = new File(filename);
            Scanner reader = new Scanner(file);
            pack = new ArrayList<>();  // Create pack.
            while(reader.hasNextLine()){
                count += 1;
                String data = reader.nextLine();

                // Check if line is an integer.
                try {
                    Integer.parseInt(data);
                } catch (Exception e){
                    System.out.println("File needs to contain only integers.");
                    return false;
                }

                // Add card to pack.
                Card newCard = new Card(Integer.parseInt(data));
                pack.add(newCard);
            }
            reader.close();
            if (count == (8 * playerNumber)){
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

    public void distributeCards() {

    }
}
