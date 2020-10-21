import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class CardGame{

    private static int playerNumber;
    private ArrayList<Card> pack;


    public static void main(String[] args) throws IOException {
        String fileName = "";
        FileReader reader = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Card Game Sim!");
        System.out.println("How many players should play the game?");
        playerNumber = scanner.nextInt();
        System.out.println("What deck should they use?");
        String packName = scanner.next();
        Boolean card = cardPack(packName);
        System.out.println(card);
    }

    public static Boolean cardPack(String filename) {
        int count = 0;
        try{
            File file = new File(filename);
            Scanner reader = new Scanner(file);
            while(reader.hasNextLine()){
                count += 1;
                String data = reader.nextLine();
                try {
                    Integer.parseInt(data);
                } catch (Exception e){
                    e.printStackTrace();
                    return false;
                }
            }
            reader.close();
            if (count == (8 * playerNumber)){
                return true;
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public void distributeCards() {

    }
}
