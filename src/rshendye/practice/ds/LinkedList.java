package rshendye.practice.ds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Implementation of LinkedList.
 */
public class LinkedList<T> {
    private static class Node<T> {
        T value;
        Node next;

        public Node() {
            value = null;
            next = null;
        }

        public Node(T value) {
            this.value = value;
            next = null;
        }
    }

    private Node head;

    public LinkedList(){
        head = null;
    }

    // adds element at the end of the list.
    public void add(T value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;             // first element
        } else {
            Node tail = head;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = newNode;
        }
    }

    // adds element at the front of the list.
    public void addFirst(T value) {
        Node newNode = new Node(value);
        if (head == null) {
            head =  newNode;
        } else {
            Node first = head;
            head = newNode;
            newNode.next = first;
        }
    }

    public void addAt(T value, int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Cannot insert at index lesser than 0");
        }

        if (head == null) {
            addFirst(value);
        }

        Node tail = head;
        for(int i = 1; i<index; i++) {
            tail = tail.next;
        }

        Node newNode = new Node(value);
        Node temp = tail;
        tail.next = newNode;
        newNode.next = temp;
    }

    // deletes the first occurrence of the value
    public void delete(T value) {
        if (head == null) {
            throw new IllegalStateException("Cannot delete from empty list.");
        }

        Node temp = head;
        while (temp != null) {
            if (temp.next == value) {
                temp.next = temp.next.next;
            }
        }
    }

    // deletes the first element of the list.
    public void deleteFirst() {
        if (head != null) {
            head.next = head.next.next;
        }
    }

    public void deleteAt(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Cannot delete at negative index");
        }

        if (index == 0) {
            deleteFirst();
        }

        Node nodeBeforeIndex = head;
        for (int i=1; i<index; i++) {
            nodeBeforeIndex = nodeBeforeIndex.next;
        }

        nodeBeforeIndex.next = nodeBeforeIndex.next.next;
    }

    public void deleteEnd() {
        if (head == null) {
            return;
        }

        if (head.next == null) {
            head = null;
        }

        // find the pointer to the last but one node.
        Node tail = head;
        while (tail.next.next != null) {
            tail = tail.next;
        }

        tail.next = null;
    }

    public static void main(String args[]) throws IOException {
        System.out.println("Starting to create a new linked list. To stop entering values, press q.");
        LinkedList<Integer> linkedList = new LinkedList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean doContinue = false;
        while (doContinue) {
            String value;
            Integer index;
            System.out.println("Enter the next number");
            String command = br.readLine();
            switch(command) {
                case "add":
                    value = br.readLine();
                    linkedList.add(Integer.parseInt(value));
                    break;

                case "addFirst":
                    value = br.readLine();
                    linkedList.addFirst(Integer.parseInt(value));
                    break;

                case "addAt":
                    value = br.readLine();
                    index = Integer.parseInt(br.readLine());
                    linkedList.addAt(Integer.parseInt(value), index);
                    break;

                case "delete":
                    value = br.readLine();
                    linkedList.delete(Integer.parseInt(value));
                    break;

                case "deleteFirst":
                    linkedList.deleteFirst();
                    break;

                case "deleteAt":
                    index = Integer.parseInt(br.readLine());
                    linkedList.deleteAt(index);
                    break;

                case "deleteEnd":
                    linkedList.deleteEnd();
                    break;

                case "q":
                default:
                    doContinue = false;
                    break;
            }
        }
    }
}
