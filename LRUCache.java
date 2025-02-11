class LRUCache {
    class Node {
        int key, value;
        Node prev, next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

    }

    HashMap<Integer, Node> map;
    Node head, tail;
    int capacity;

    private void addToHead(Node node) {

        node.next = head.next;
        head.next = node;
        node.next.prev = node;
        node.prev = head;

    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            removeNode(node);
            addToHead(node);
            node.value = value;
            return;
        }
        if (map.size() == capacity) {
            Node tailPrev = tail.prev;
            removeNode(tailPrev);
            map.remove(tailPrev.key);
        }
        Node node = new Node(key, value);
        addToHead(node);
        map.put(key, node);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */