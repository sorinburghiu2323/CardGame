//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.Arrays;
//
//public class PlayerThread extends Thread{
//
//    BufferedWriter writer;
//
//    public PlayerThread(String fileName) throws IOException {
//        this.writer = new BufferedWriter(new FileWriter(fileName));
//    }
//
//    public void run(){
//        while (!done) {
//            synchronized (this) {
//                try {
//                    for (int i=0; i<4; i++) {
//                        if (hand[i].getCardNumber() != playerID) {
//                            Card tempCard = hand[i];
//                            hand[i] = drawCard();
//                            removeCard(tempCard);
//                            break;
//                        }
//                    }
//                    if (isWin()) {
//                        System.out.println(playerID + " has won.");
//                        notifyAll();
//                        this.done = true;
//                    }
//                    else {
//                        System.out.println(playerID + ": " + Arrays.toString(hand) + " finished turn.");
//                        Thread.sleep(5000);
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        System.out.println(playerID + " was informed someone won.");
//    }
//
//    protected void writeToFile(String message) throws IOException {
//        this.writer.write(message);
//        this.writer.newLine();
//        this.writer.flush();
//    }
//
//}
