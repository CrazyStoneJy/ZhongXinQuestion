import java.util.Random;

public class TwoWayLinkedList implements IList {


    private int size = 0;
    // the head of this two-way linked list.
    private Node head;
    // the tail of this two-way linked list.
    private Node tail;


    /**
     * check the linked list whether or not contain this element
     *
     * @param value the element that u want to check.
     * @return
     */
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

    /**
     * check the linked list whether or not empty.
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * the size of linked list.
     *
     * @return
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * this function is used to change the two-way linked list state that one by one.
     */
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
            if (current == null) {
                break;
            }
            next = current.next;
        }
    }

    /**
     * this function is used to exclusive or two elements.
     *
     * @param a element a
     * @param b element b
     * @return
     */
    private int xor(int a, int b) {
        return a ^ b;
    }

    /**
     * get the index of element in the linked list.
     *
     * @param value
     * @return
     */
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

    /**
     * this function is used to add an element to two-way linked list.
     * u can set state 0 or 1,present 【活跃状态】
     * if u want to add element with default state,u can invoke{@link #add(int)}
     *
     * @param value this is index of the linked list, just to distinguish an element.
     * @param state the state of the element.
     */
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

    /**
     * add an element with default state. default state is zero.
     * u can add element state,by this function {@link #add(int, int)}
     *
     * @param value
     */
    @Override
    public void add(int value) {
        this.add(value, 0);
    }

    /**
     * if the element is contain in the linked list, remove it.
     *
     * @param value
     */
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

    /**
     * clear the linked list
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * this function is used to print linked list.
     */
    public void print() {
        Node current = head;
        StringBuilder sb = new StringBuilder();
//        // head
//        sb.append("head:").append(head != null ? head.value : "null")
//                //tail
//                .append(";tail:").append(tail != null ? tail.value : "null").append("\n");
//        sb.append("size:").append(size).append("\n");

        while (current != null) {
//            //current
//            sb.append("current:").append(current.value)
//                    .append(",state:").append(current.state)
//                    .append("(")
//                    .append("pre:").append(current.pre != null ? current.pre.value : "null")
//                    .append(";")
//                    .append("next:").append(current.next != null ? current.next.value : "null")
//                    .append(")")
//                    .append("->");
            sb.append(current.state);
            current = current.next;
        }
        System.out.println(sb);
    }

    /**
     * the data structure of Node
     */
    private static class Node {
        /* the value fo node */
        private int value;
        /* previous element */
        private Node pre;
        /* the next element */
        private Node next;
        /* the state of this element */
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
