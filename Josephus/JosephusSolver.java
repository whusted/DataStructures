//WILLY import java.util.Iterator;

public class JosephusSolver {

    private int skip;
    private int participants;
    private int remaining;
    private JList jl;
    private String result;
    private JNode potatoHolder;
    //WILLY private Iterator it;

    /** Takes two string parameters participants and skip and prints the last man
    standing as per the Josephus Problem according to 2013 CMSI 281 Dorin specifications.
    */
    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        JosephusSolver js = new JosephusSolver(a, b);
        System.out.println(js.solveWith(a,b));
    }

    /** Constructor. */
    public JosephusSolver(int participants, int skip) {
        this.participants = participants;
        this.skip = skip;
        this.jl = new JList();
        //WILLY this.it = jl.iterator();
    }

    /** Empty constructor, for use with solveWith(). */
    public JosephusSolver() {
        this.jl = new JList();
        //WILLY this.it = jl.iterator();
    }

    /** Returns the last man standing. See description in main. */
    public String solveWith(int participants, int skip) {
        if (participants <= 0 || skip < 0) {
            throw new IllegalArgumentException("Bad integer input, try again.");
        }
        try {    
            JosephusSolver js = new JosephusSolver(participants, skip);
            js.setup();
            js.solve();
            this.result = js.getResult();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return this.result;
    }

    /** Preps the state of the solver such that it is ready to initiate a 
    result-producing sequence. */
    private void setup() {
        for (int i = 1; i <= this.participants; i++) {
            this.jl.add(new JNode(Integer.toString(i)));
        }
        this.remaining = this.jl.getSize();
    }

    /** Massages the state of this solver until a solution is known. */
    public void solve() {
        while ( this.remaining > 1 ) {
            this.passPotato(this.skip);
        }
    }

    /** Identifies and removes the next participant to be killed. */
    private void passPotato(int r) {
        if (r == 0) {
            this.kill();
        }
        else {
            this.jl.goPrevious();
            this.passPotato(r - 1);
        }
    }

    /** Removes the current participant */
    private void kill() {
        this.jl.doRemove();
        this.remaining = this.jl.getSize();
    }

    /** Getter for property result */
    public String getResult() {
        return this.jl.getCurrentAsString();
    }

}
