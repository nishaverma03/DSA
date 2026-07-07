import java.util.*;

class AllOne {

    class Node {
        int count;
        Set<String> keys;
        Node prev, next;

        Node(int count) {
            this.count = count;
            this.keys = new HashSet<>();
        }
    }

    private Node head, tail;
    private Map<String, Node> map;

    public AllOne() {
        head = new Node(0);
        tail = new Node(0);

        head.next = tail;
        tail.prev = head;

        map = new HashMap<>();
    }
    
    public void inc(String key) {
        if (!map.containsKey(key)) {
            if (head.next == tail || head.next.count != 1) {
                Node newNode = new Node(1);
                insertAfter(head, newNode);
            }

            head.next.keys.add(key);
            map.put(key, head.next);
        } else {
            Node current = map.get(key);
            int newCount = current.count + 1;

            if (current.next == tail || current.next.count != newCount) {
                Node newNode = new Node(newCount);
                insertAfter(current, newNode);
            }

            current.next.keys.add(key);
            map.put(key, current.next);
            current.keys.remove(key);

            if (current.keys.isEmpty()) {
                removeNode(current);
            }
        }
    }
    
    public void dec(String key) {
        Node current = map.get(key);

        if (current.count == 1) {
            map.remove(key);
        } else {
            int newCount = current.count - 1;

            if (current.prev == head || current.prev.count != newCount) {
                Node newNode = new Node(newCount);
                insertAfter(current.prev, newNode);
            }

            current.prev.keys.add(key);
            map.put(key, current.prev);
        }

        current.keys.remove(key);

        if (current.keys.isEmpty()) {
            removeNode(current);
        }
    }
    
    public String getMaxKey() {
        if (tail.prev == head) {
            return "";
        }

        return tail.prev.keys.iterator().next();
    }
    
    public String getMinKey() {
        if (head.next == tail) {
            return "";
        }

        return head.next.keys.iterator().next();
    }

    private void insertAfter(Node prevNode, Node newNode) {
        newNode.next = prevNode.next;
        newNode.prev = prevNode;
        prevNode.next.prev = newNode;
        prevNode.next = newNode;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}

