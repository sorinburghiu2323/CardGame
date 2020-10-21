import java.io.*;
import java.util.Scanner;

public class CardGame {
    public static void main(String[] args) throws IOException {
        String fileName = "";
        FileReader reader = null;


        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Card Game Sim!");
        System.out.println("How many players should play the game?");
        int playerNumber = scanner.nextInt();
        System.out.println("What deck should they use?");
        try{
            fileName = scanner.nextLine();
            reader = new FileReader(fileName);
            while (reader.)
        } catch (IOException e){
            e.printStackTrace();
            return;
        }
    }
}
