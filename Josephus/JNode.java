public class JNode {

    private String value;
    private JNode previous;
    private JNode next;

    /** No functionality. */
    public static void main(String[] args) {
        throw new UnsupportedOperationException();
    }

    /** Constructs a JNode with a value and null previous and next pointers */
    public JNode (String value) {
        this.value = value;
        this.previous = null;
        this.next = null;
    }

    /** Constructs a JNode with a value and specified previous and next pointers */
    public JNode (String value, JNode previous, JNode next) {
        this.value = value;
        this.previous = previous;
        this.next = next;
    }
    
    /** Returns the value of this JNode */
    public String getValue() {
        return this.value;
    }

    /** Getter for property previous */
    public JNode getPrevious() {
        return this.previous;
    }

    /** Getter for property next */
    public JNode getNext() {
        return this.next;
    }

    /** Setter for property previous */
    public void setPrevious(JNode p) {
        this.previous = p;
    }

    /** Setter for property next */
    public void setNext(JNode q) {
        this.next = q;
    }

    /** Returns the value of this JNode */
    public String toString() {
        return this.value;
    }

    /** Sets all pointer properties of this JNode to null */
    public void nullify() {
        this.previous = null;
        this.next = null;
    }

}
