package adventCode;

import java.io.IOException;
import java.util.*;


class Day232 {
    public static class Node {
        public Long value;
        public Node next;
        public Node previous;

        public Node(Long value) {
            this.value = value;
        }
    };


    public static void main(String[] args) throws IOException {
        String realInput = "586439172";
        String testInput = "389125467";
        String input = realInput;

        long highest = Long.parseLong(input.substring(0,1));
        Map<Long, Node> m = new HashMap<Long, Node>();

        Node head = null;
        Node current = head;

        for (String s : input.split("")) {
            long value = Long.parseLong(s);
            if (current == null) {
                current = new Node(value);
                head = current;
            } else {
                current.next = new Node(value);
                current = current.next;
            }
            m.put(value, current);
            current.next = head;

            if (value > highest) highest = value;
        }


        for (long i = highest + 1; i <= 1000000; ++i) {
            current.next = new Node(i);
            current = current.next;
            m.put(i, current);
        }
        current.next = head;

        long move = 0;
        current = head;
        while (move < 10000000) {
            current = move(current, m);
            ++move;
        }
        System.out.println("Part2: " + m.get(1L).next.value * m.get(1L).next.next.value);
    }


    public static Node move(Node head, Map<Long, Node> m) {
        Node current = head;
        Node first = current.next;
        Node last = current.next.next.next;
        current.next = last.next;

        long target = current.value - 1;
        while (target < 1 || target == first.value ||
                target == first.next.value || target == last.value) {
           target = (target < 1)? 1000000 : target - 1;
        }
        Node targetNode = m.get(target);
        last.next = targetNode.next;
        targetNode.next = first;

        return current.next;
    }
}
