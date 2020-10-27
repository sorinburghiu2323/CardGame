import org.w3c.dom.Node;

import java.util.LinkedList;
import java.util.Queue;

public class CircularLL {
    static Node head;
    static class Node{
        Object data;
        Node next;
        Node prev;
    }
    static void addNode(Object data){
        if(head == null){
            Node newNode = new Node();
            newNode.data = data;
            newNode.next = newNode.prev = newNode;
            head = newNode;
            return;
        }
        Node last = (head).prev;
        Node newNode = new Node();
        newNode.data = data;
        newNode.next = head;
        (head).prev = newNode;
        newNode.prev = last;
        last.next = newNode;
    }
    static Object traverse(){
        Node temp = head;
        while(temp.next.next != head){
            temp = temp.next.next;
        }
        return temp.data;
    }

    public static void main(String[] args) {
        Node l_list = null;
        Card[] cards1 = new Card[]{new Card(1), new Card(2), new Card(3), new Card(4)};
        Card[] cards2 = new Card[]{new Card(5), new Card(2), new Card(1), new Card(4)};

        Queue<Card> deck1 = new LinkedList<Card>();
        deck1.add(new Card(19));
        deck1.add(new Card(19));
        deck1.add(new Card(29));
        deck1.add(new Card(39));

        Queue<Card> deck2 = new LinkedList<Card>();
        deck2.add(new Card(16));
        deck2.add(new Card(16));
        deck2.add(new Card(26));
        deck2.add(new Card(36));


        //print the list
        System.out.printf("Circular doubly linked list: \n");
        System.out.println(CircularLL.traverse());
    }
}
