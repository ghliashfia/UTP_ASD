import java.util.Scanner;

class Node {
    int year;
    String event;
    Node next, prev;

    Node(int year, String event) {
        this.year = year;
        this.event = event;
        this.next = null;
        this.prev = null;
    }
}

class TimeTravellerList {
    Node head, tail;

    void addEvent(int year, String event) {
        Node newNode = new Node(year, event);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    boolean removeEvent(int year) {
        if (head == null) return false;

        Node current = head;
        while (current != null && current.year != year) {
            current = current.next;
        }

        if (current == null) return false; // Tahun tidak ditemukan

        if (current == head) {
            head = head.next;
            if (head != null) head.prev = null;
            else tail = null;
        } else if (current == tail) {
            tail = tail.prev;
            if (tail != null) tail.next = null;
            else head = null;
        } else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }

        return true;
    }

    void travelForward() {
        if (head == null) {
            System.out.println("Timeline Kosong");
            return;
        }

        Node current = head;
        while (current != null) {
            System.out.print("[" + current.year + ": " + current.event + "]");
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }

    void travelBackward() {
        if (tail == null) {
            System.out.println("Timeline Kosong");
            return;
        }

        Node current = tail;
        while (current != null) {
            System.out.print("[" + current.year + ": " + current.event + "]");
            if (current.prev != null) {
                System.out.print(" <- ");
            }
            current = current.prev;
        }
        System.out.println();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TimeTravellerList timeline = new TimeTravellerList();

        if (!sc.hasNextInt()) {
            sc.close();
            return;
        }

        int Q = sc.nextInt();

        for (int i = 0; i < Q; i++) {
            int cmd = sc.nextInt();

            switch (cmd) {
                case 1:
                    int yr = sc.nextInt();
                    String evt = sc.next();
                    timeline.addEvent(yr, evt);
                    break;

                case 2:
                    int delYr = sc.nextInt();
                    if (!timeline.removeEvent(delYr)) {
                        System.out.println("Peristiwa pada tahun " + delYr + " tidak ditemukan");
                    }
                    break;

                case 3:
                    timeline.travelForward();
                    break;

                case 4:
                    timeline.travelBackward();
                    break;
            }
        }

        sc.close();
    }
}
