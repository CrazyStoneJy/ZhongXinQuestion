import java.util.Random;

public class TwoWayLinkedList implements IList {


    private int size = 0;
    private Node head, tail;


    @Override
    public boolean contains(int value) {
        Node current = head;
        while (current != null) {
            if (value == current.value) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    public void change() {
        if (head == null) {
            System.out.println("the head element of the list is null");
            return;
        }
        Node current = head;
        Node pre = null;
        Node next = current.next;
        while (current != null) {
            if (pre == null && next == null) {
                current.state = xor(0, 0);
            } else if (pre != null && next == null) {
                current.state = xor(pre.state, 0);
            } else if (pre == null && next != null) {
                current.state = xor(0, next.state);
            } else {
                current.state = xor(pre.state, next.state);
            }
            pre = current;
            current = current.next;
            if(current==null) {
                break;
            }
            next = current.next;
        }
    }


    private int xor(int a, int b) {
        return a ^ b;
    }

    @Override
    public int indexOf(int value) {
        int index = -1;
        Node current = head;
        while (current != null) {
            index++;
            if (value == current.value) {
                return index;
            }
            current = current.next;
        }
        return index;
    }

    public void add(int value, int state) {
        if (contains(value)) {
            System.out.println("this list already has " + value);
            return;
        }
        Node node = null;
        if (head == null) {
            node = new Node(value, state);
            head = node;
            tail = node;
        } else {
            Node current = head;
            Node pre = null;
            while (current != null) {
                pre = current;
                current = current.next;
            }
            node = new Node(pre, value, state);
            pre.next = node;
            tail = node;
        }
        size++;
    }

    @Override
    public void add(int value) {
        this.add(value, 0);
    }

    @Override
    public void remove(int value) {
        Node current = head;
        Node pre = null;
        boolean hasFind = false;
        while (current != null) {
            if (value == current.value) {
                hasFind = true;
                break;
            }
            pre = current;
            current = current.next;
        }
        if (hasFind) {
            if (pre == null) {
                head = current.next;
                if (current.next == null) {
                    tail = null;
                }
                head.pre = null;
            } else {
                pre.next = current.next;
                current.next.pre = pre;
                if (current.next == null) {
                    tail = pre;
                }
            }
            // 释放对象
            current = null;
            size--;
        }
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public void print() {
        Node current = head;
        StringBuilder sb = new StringBuilder();
        // head
        sb.append("head:").append(head != null ? head.value : "null")
                //tail
                .append(";tail:").append(tail != null ? tail.value : "null").append("\n");
        sb.append("size:").append(size).append("\n");

        while (current != null) {
            //current
            sb.append("current:").append(current.value)
                    .append(",state:").append(current.state)
                    .append("(")
                    .append("pre:").append(current.pre != null ? current.pre.value : "null")
                    .append(";")
                    .append("next:").append(current.next != null ? current.next.value : "null")
                    .append(")")
                    .append("->");
            current = current.next;
        }
        System.out.println(sb);
    }

    private static class Node {

        private int value;
        private Node pre;
        private Node next;
        private int state;
        private Random random = new Random();

        public Node(int value, int state) {
            pre = null;
            next = null;
            this.value = value;
            this.state = state;
        }

        public Node(int value) {
            pre = null;
            next = null;
            this.value = value;
            this.state = random.nextBoolean() ? 1 : 0;
        }

        public Node(Node pre, int value) {
            this.pre = pre;
            this.value = value;
            this.state = random.nextBoolean() ? 1 : 0;
        }

        public Node(Node pre, int value, int state) {
            this.pre = pre;
            this.value = value;
            this.state = state;
        }


    }


}
