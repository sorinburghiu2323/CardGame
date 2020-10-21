public class CircularLinkedList {

    public class Node {
        Object data;
        Node next;

        public Node(Object data) {
            this.data = data;
        }
    }

    public Node head = null;
    public Node tail = null;

    public void add(int data) {
        //Create new node
        Node newNode = new Node(data);
        //Checks if the list is empty.
        if (head == null) {
            //If list is empty, both head and tail would point to new node.
            head = newNode;
            tail = newNode;
            newNode.next = head;
        } else {
            //tail will point to new node.
            tail.next = newNode;
            //New node will become new tail.
            tail = newNode;
            //Since, it is circular linked list tail will point to head.
            tail.next = head;
        }
    }

    //Displays all the nodes in the list
    public void display() {
        Node current = head;
        if (head == null) {
            System.out.println("List is empty");
        } else {
            System.out.println("Nodes of the circular linked list: ");
            do {
                //Prints each node by incrementing pointer.
                System.out.print(" " + current.data);
                current = current.next;
            } while (current != head);
            System.out.println();
        }
    }
    public void traverse(Object data){

    }
}
