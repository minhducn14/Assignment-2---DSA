package Database.DataStructure;

public class CustomStack {
    private QuadTree.Node[] elements;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;

    public CustomStack() {
        elements = new QuadTree.Node[DEFAULT_CAPACITY];
    }

    public void push(QuadTree.Node node) {
        ensureCapacity();
        elements[size++] = node;
    }

    public QuadTree.Node pop() {
        if (size == 0) {
            throw new IllegalStateException("Pop from an empty stack");
        }
        QuadTree.Node result = elements[--size];
        elements[size] = null;  // Avoid loitering
        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            int newSize = elements.length * 2;
            QuadTree.Node[] newElements = new QuadTree.Node[newSize];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
    }
}
