import java.util.Iterator;

public class JList implements Iterable {
    
    private class JListIterator implements Iterator {
        private JNode current;
        private JList myJList;

        public JListIterator(JList j) {
            this.myJList = j;
            this.current = j.getEntry();
        }

        /** Returns the next object in the list */
        public Object next() {
            this.current = this.current.getNext();
            return this.current;
        }
        /** Returns and sets current to the previous JNode. */
        public Object previous() {
            this.current = this.current.getPrevious();
            return this.current;
        }

        /** Returns whether or not the current JNode has non-null next pointer. */
        public boolean hasNext() {
            return this.current.getNext() != null;
        }

        /** Returns whether or not the current JNode has non-null previous pointer. */
        public boolean hasPrevious() {
            return this.current.getPrevious() != null;
        }

        /** Removes the next object in the list */
        public void remove() {
            JNode killTarget = this.current;
            this.current = this.current.getPrevious();
            myJList.remove(killTarget);
        }

        /** JList iterates by pointing to some node, current, and then by traversing in the
        next or previous directions. Returns the present, current JNode that is pointed to by 
        this list. */
        public JNode getCurrent() {
            return this.current;
        }
    }
    
    private JNode entry;
    private JNode mostRecent;
    private int size;
    private JListIterator iterator;

    /** No functionality. */
    public static void main(String[] args) {
         throw new UnsupportedOperationException();
    }

    /** Constructs a new JList of size 0. */
    public JList() {
        this.size = 0;
    }

    /** Adds a JNode object to this JList's collection. */
    public void add(JNode j) {
        if (this.size == 0) {
            this.entry = j;
            this.mostRecent = j;
            this.size++;
            this.iterator = iterator(); //iterator can only be init if there is >1 element in list
        }
        else {
            j.setPrevious(this.mostRecent); 
            j.setNext(entry);
            this.entry.setPrevious(j);
            this.mostRecent.setNext(j);
            this.mostRecent = j;
            this.size++;
        }
    }

    public JListIterator iterator() {
        return new JListIterator(this);
    }

    /** Returns the number of JNodes in this JList. */
    public int getSize() {
        return this.size;
    }

    /** The first JNode added to the JList is the entry node. The entry JNode 
    should not be removed if the JList is to be added to via add(). */
    public JNode getEntry() {
        return this.entry;
    }

    /** Returns the present, current value of the JNode that is pointed to by this list. */
    public String getCurrentAsString() {
        return this.iterator.getCurrent().toString();
    }

    public JListIterator getIterator() {
        return this.iterator;
    }

    public void goNext() {
        this.iterator.next();
    }

    public void goPrevious() {
        this.iterator.previous();
    }

    public void doRemove() {
        this.iterator.remove();
    }

    /** Private work done by remove().  This method's job is disconnect a node from the list and make the relevant re-connections.
    this method DOES NOT do any work to ensure that the iterator's current field is correct. Call from iterator's remove method.
    Throws IllegalStateException if JList is empty. */
    private void remove(JNode j) {
        if (this.size != 0) {
            j.getPrevious().setNext(j.getNext());
            j.getNext().setPrevious(j.getPrevious());
            //System.out.println("Node " + j.toString() + " takes his final breath...");
            j.nullify();
            this.size--;
        }
        else {
            throw new IllegalStateException("Cannot remove element from empty JList");
        }
    }
} 
