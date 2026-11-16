package RemedialUTP.AsmaraBudi;

import java.util.Scanner;

class Node {
    String data;
    Node next, prev;

    Node(String data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

class DoublyLinkedList {
    Node head, tail;

    void insertFirst(String nama) {
        Node newNode = new Node(nama);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    void insertLast(String nama) {
        Node newNode = new Node(nama);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    boolean delete(String nama) {
        if (head == null) {
            return false;
        }

        Node current = head;
        while (current != null && !current.data.equals(nama)) {
            current = current.next;
        }

        if (current == null) {
            return false;
        }

        if (current == head) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null; 
            }
        } 

        else if (current == tail) {
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            } else {
                head = null; 
            }
        } 
        
        else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }

        return true;
    }

    void printForward() {
        if (head == null) {
            System.out.println("Budi Jomblo Ngenes");
            return;
        }

        Node current = head;
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" <-> ");
            }
            current = current.next;
        }
        System.out.println();
    }

    void printBackward() {
        if (tail == null) {
            System.out.println("Budi Jomblo Ngenes");
            return;
        }

        Node current = tail;
        while (current != null) {
            System.out.print(current.data);
            if (current.prev != null) {
                System.out.print(" <-> ");
            }
            current = current.prev;
        }
        System.out.println();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DoublyLinkedList list = new DoublyLinkedList();

        if (!sc.hasNextInt()) {
            sc.close();
            return;
        }

        int Q = sc.nextInt();

        for (int i = 0; i < Q; i++) {
            int cmd = sc.nextInt();

            switch (cmd) {
                case 1:
                    list.insertLast(sc.next());
                    break;

                case 2:
                    list.insertFirst(sc.next());
                    break;

                case 3:
                    String nama = sc.next();
                    if (!list.delete(nama)) {
                        System.out.println(
                            "Budi tidak pernah ada hubungan dengan " + nama
                        );
                    }
                    break;

                case 4:
                    list.printForward();
                    break;

                case 5:
                    list.printBackward();
                    break;
            }
        }

        sc.close();
    }
}