import java.util.Iterator;
import java.util.Stack;
import java.util.ArrayList;
/**
   An object of this class represents a binary tree. 
   Nodes may be decorated (labeled) with arbitrary objects (the default is null).
*/

public class BinaryTree implements java.util.Collection {

    public static void main(String[] args) {
        BinaryTree a = new BinaryTree();
        a.add("1");
        a.add("2");
        a.alternateAdd("3");
        a.alternateAdd("4");

        //System.out.println("b removed " + b2.remove("b"));
        //System.out.println(a.remove("1"));
        //System.out.println(a.toString());
        BinaryTree b = new BinaryTree();
        b.add("1");
        b.add("2");
        b.alternateAdd("3");
        b.alternateAdd("4");

        System.out.println(b.equals(a));

        BinaryTree c = newFromRootAndTwoTrees ("c", a, b);
        c.alternateAdd("d");
        c.add("e");



        //System.out.println(c.toString());
        BinaryTree e = new BinaryTree();
        e.add("1");
        e.alternateAdd("a");
        BinaryTree e2 = new BinaryTree();
        e2.add("1");
        e2.alternateAdd("b");

        //BinaryTree f = newFromRootAndTwoTrees("c", a, b);
        BinaryTree f = new BinaryTree();
        f.alternateAdd("d");
        f.add("e");

    }

    //private data
    private Node root;
    private int size;
    private int hashCode;

    /** Constructs an empty binary tree. */
 
    public BinaryTree() {
       this.root = null;
       this.size = 0;
       this.hashCode = hashCodeGenerator();
    }
   
    /** Returns a string that represents this binary tree. */
   
    public String toString() {
        if (this.isEmpty()) {
            return "Empty tree";
        }
        //Listed in preorder and inorder
        String result = "Preorder: ";
        Iterator it = this.iterator();
        while (it.hasNext()) {
            result = result + it.next() + "    ";
        }
        result = result + '\n' + "Inorder: ";

        Iterator it2 = this.inorderIterator();
        while (it2.hasNext()) {
            result = result + it2.next() + "    ";
        }

        return result;
    }

    /**
       Adds a node, decorated with obj, to this binary tree.
	   The new node becomes the root of the tree; the former tree becomes the left subtree 
	   of the root; the right subtree of the root will be empty.
    */

    public boolean add ( Object obj ) {
        Node n = new Node(obj);
        n.setLeft(this.root);
        n.setRight(null);
        if (this.size != 0) this.root.setParent(n);
        this.root = n;
        this.size++;
        return true;
    }
   
 	
    /**
       Adds a node, decorated with obj, to this binary tree.
	   The new node becomes the root of the tree; the left subtree of the root will be empty; 
	   the former tree becomes the right subtree of the root.
    */
 
    public boolean alternateAdd ( Object obj ) {
        Node n = new Node(obj);
        n.setRight(this.root);
        n.setLeft(null);
        if (this.size != 0) this.root.setParent(n);
        this.root = n;
        this.size++;
        return true;
    }
    
    /**
       Static factory for constructing new binary trees.
	   The root of the new tree will be decorated with obj; leftSubtree and rightSubtree, respectively, 
	   will become the left and right subtrees of the root.
    */

    public static BinaryTree newFromRootAndTwoTrees ( Object obj, BinaryTree leftSubtree, BinaryTree rightSubtree ) {
        BinaryTree b = new BinaryTree();
        Node n = new Node(obj);
        n.setRight(rightSubtree.root);
        n.setLeft(leftSubtree.root);
        b.root = n;
        b.size = rightSubtree.size() + leftSubtree.size() + 1;
        return b;
    }
    
		  	
    /** Returns a hashcode for this binary tree. */

    public int hashCode () {
       return this.hashCode;
    }

    private int hashCodeGenerator() {
        return (int)Math.floor(Math.random() * Integer.MAX_VALUE);
    }

	
    /** Throws an UnsupportedOperationException(). */
    public boolean addAll ( java.util.Collection c ) {
       throw new UnsupportedOperationException();
    }

	
    /** Re-initializes this to an empty binary tree. */	
    public void clear () {
        this.root = null;
        this.size = 0;
    }

    /**
       Returns true iff this binary tree contains (at least) one example
       of obj.
    */ 

    public boolean contains ( Object obj ) {
        Iterator it = this.iterator();
        while (it.hasNext()) {
            Object d = it.next();
            if (d.equals(obj)) {
                return true;
            }
        }
        return false;
    }

    /**
      Returns true iff this binary tree contains at least one example of each DIFFERENT
      object in Collection c. Note that c may contain several examples of some 
      (same) object, but this binary tree is only required to contain one such example.
    */

    public boolean containsAll ( java.util.Collection c ) {
       throw new UnsupportedOperationException();
    }

    /**
       Returns true iff this binary tree is equivalent, with respect to both structure and content, 
	   as Object obj.
    */
 
    public boolean equals ( Object obj ) {
        BinaryTree b = (BinaryTree)obj;
        if (this.isEmpty() && b.isEmpty()) return true;
        
        return equals(this.root, b.root);

    }

    private boolean equals (Node n1, Node n2) {
        if (n1 == null || n2 == null) return false;
        System.out.println("N1 and 2: " + n1.getData() + "  " + n2.getData());

        return (n1.getData().equals(n2.getData()) && equals(n1.getLeft(), n2.getLeft()) && equals(n1.getRight(), n2.getRight()));

    }
    
	 
    /** Returns true iff this binary tree is empty. */
    public boolean isEmpty () {
       return this.size == 0;
    }

	
    /** Returns a preorder iterator for this binary tree. All bets are off if the tree changes during the traversal. */
    public java.util.Iterator iterator () {
        return new PreorderIterator(this);
    }

    /** Returns an inorder iterator for this binary tree. All bets are off if the tree changes during the traversal. */
    public java.util.Iterator inorderIterator () {
        return new InorderIterator(this);
    }

    /** Returns a postorder iterator for this binary tree. All bets are off if the tree changes during the traversal. */
    public java.util.Iterator postorderIterator () {
        throw new UnsupportedOperationException();
    }	

    /**
       SUPPOSED to remove a matching object from this binary tree, and returns true,  
       provided that the matching object is at a leaf; if there is no matching leaf,
	   then the method returns false.
    */
    public boolean remove ( Object obj ) {
        // PreorderIterator it = new PreorderIterator(this);
        // while (it.hasNext()) {
        //     Node n = it.preorderNext();
        //     if (n.getData().equals(obj) && n.isLeaf()) {
        //         if (this.size == 1) {
        //             this.clear();
        //             return true;
        //         } else {
        //             if (n.getParent().getRight() != null) {
        //                 if (n.getParent().getRight().getData().equals(n.getData())) {
        //                     n.getParent().setRight(null);
        //                     n = null;
        //                 } else {
        //                     n.getParent().setLeft(null);
        //                     n = null;
        //                 }
        //             }
        //         }
        //         this.size--;
        //         System.out.println("New string " + this.toString());
        //         System.out.println("new size: " + this.size);
        //         return true;
        //     }
        // }
        // return false;
        throw new UnsupportedOperationException();
    }

	
    /** Throws an UnsupportedOperationException. */
    public boolean removeAll (  java.util.Collection c ) {
       throw new UnsupportedOperationException ();
    }

	
    /** Throws an UnsupportedOperationException(). */
    public boolean retainAll (  java.util.Collection c ) { // overrides Collection
       throw new UnsupportedOperationException ();
    }

	
    /** Returns the number of nodes in this binary tree. */
    public int size () {
       return this.size;
    }
 

    /** Throws an UnsupportedOperationException(). */
    public Object[] toArray () {
       throw new UnsupportedOperationException ();
    }

 
    /** Throws an UnsupportedOperationException(). */
    public Object[] toArray ( Object[] a ) {
       throw new UnsupportedOperationException ();
    }
    
    /** Private Node class to be linked together to create a BinaryTree. All getters and setters are self explanatory.*/
    private static class Node {

        private Node leftSubtree;
        private Node parent;
        private Node rightSubtree; 
        private Object data;
        private boolean visited;

        /** Node constructor*/
        Node (Object obj) {
            this.leftSubtree = null; 
            this.rightSubtree = null;
            this.parent = null;
            this.data = obj;
            this.visited = false; 
        }

        public boolean beenVisited() {
            return this.visited;
        }

        public Object getData() {
            return this.data;
        }

        public Node getLeft() {
            return this.leftSubtree;
        }

        public Node getParent() {
            return this.parent;
        }

        public Node getRight() {
            return this.rightSubtree;
        }

        public boolean isLeaf() {
            return this.leftSubtree == null && this.rightSubtree == null;
        }

        public void setLeft(Node n) {
            this.leftSubtree = n;
        }

        public void setParent(Node n) {
            this.parent = n;
        }

        public void setRight(Node n) {
            this.rightSubtree = n;
        }
    }
    
    private class InorderIterator implements Iterator {
        private Node current;
        private Node next;
        private int remaining = BinaryTree.this.size;

        Stack s = new Stack();

        public InorderIterator(BinaryTree b) {
            this.current = root;
        }
        /** Returns the next object in the tree, if there is one*/
        public Object next() {
            return inorderNext().getData();
        }
        /** Returns the next Node in the tree in inorder, if there is one*/
        public Node inorderNext() {
            if (!hasNext()) throw new IllegalStateException();
            if (this.next != null) {
                this.current = this.next;
            }

            while (this.current != null) {
                 if (!this.current.beenVisited()) s.push(this.current);
                this.current = this.current.getLeft();
            }

            this.current = (Node)s.pop();
            if (this.current.getRight() != null) {
                this.next = this.current.getRight();
                while (this.next.getLeft() != null) {
                    s.push(this.next);
                    this.next = this.next.getLeft();
                }
            } else {
                if (!s.isEmpty()) this.next = (Node)s.pop();
            }

            this.remaining--;
            this.current.visited = true;
            return this.current;
        }
        /** Returns true if there is at least 1 Node left to visit*/
        public boolean hasNext() {
            return this.remaining > 0;    
        }
        /** Removes the last element that was "served up"*/
        public void remove() {
            if (this.current == null) throw new IllegalStateException();
            if (this.current.getParent() == null) {
                this.next.setParent(null);
            } else {
                if (this.current.getLeft() != null) {
                    if (this.current.getParent().getLeft().getData().equals(this.current.getData())) {
                        this.current.getParent().setLeft(this.current.getLeft());
                    } else {
                        this.current.getParent().setRight(this.current.getLeft());
                    }
                } else if (this.current.getRight() != null) {
                    if (this.current.getParent().getLeft().getData().equals(this.current.getData())) {
                        this.current.getParent().setLeft(this.current.getLeft());
                    } else {
                        this.current.getParent().setRight(this.current.getLeft());
                    }
                } else {
                    if (this.current.getParent().getLeft().getData().equals(this.current.getData())) {
                        this.current.getParent().setLeft(null);
                    } else {
                        this.current.getParent().setRight(null);
                    }
                }
            }
        }


    }

    private class PreorderIterator implements Iterator {

        private Node current;
        private Node next;
        private int remaining = BinaryTree.this.size;

        Stack s = new Stack();

        public PreorderIterator(BinaryTree b) {
            this.current = root;
        }

        /** Returns the next object in the tree, if there is one*/
        public Object next() {
            return preorderNext().getData();
        }
        /** Returns the next Node in the tree in preorder, if there is one*/
        public Node preorderNext() {
            if (!hasNext()) throw new IllegalStateException();
            if (this.next != null) this.current = this.next;
            if (this.current.getLeft() != null) {
                if (this.current.getRight() != null) {
                    s.push(this.current.getRight());
                }
                this.next = this.current.getLeft();
            } else if (this.current.getRight() != null) {
                this.next = this.current.getRight();
            } else {
                if (!s.empty()) this.next = (Node)s.pop();
            }
            this.remaining--;
            return this.current;
        }

        /** Returns true if there is at least 1 Node left to visit*/
        public boolean hasNext() {
            return this.remaining > 0;
        }

        /** Removes the last element that was "served up"*/
        public void remove() {
            if (this.current == null) throw new IllegalStateException();
            if (this.current.getParent() == null) {
                this.next.setParent(null);
            } else {
                if (this.current.getLeft() != null) {
                    if (this.current.getParent().getLeft().getData().equals(this.current.getData())) {
                        this.current.getParent().setLeft(this.current.getLeft());
                    } else {
                        this.current.getParent().setRight(this.current.getLeft());
                    }
                } else if (this.current.getRight() != null) {
                    if (this.current.getParent().getLeft().getData().equals(this.current.getData())) {
                        this.current.getParent().setLeft(this.current.getLeft());
                    } else {
                        this.current.getParent().setRight(this.current.getLeft());
                    }
                } else {
                    if (this.current.getParent().getLeft().getData().equals(this.current.getData())) {
                        this.current.getParent().setLeft(null);
                    } else {
                        this.current.getParent().setRight(null);
                    }
                }
            }
        }

    }

}