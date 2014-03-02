public class JTest {

    public static void main(String[] args) {
        JosephusSolver js = new JosephusSolver();
        assert js.solveWith(3, 8).equals("3");
        assert js.solveWith(5, 2).equals("3");
        assert js.solveWith(5, 3).equals("1");
        assert js.solveWith(8, 4).equals("7");
        assert js.solveWith(4, 2).equals("1");
        assert js.solveWith(9, 9).equals("3");
        assert js.solveWith(7, 5).equals("6");
        assert js.solveWith(6, 0).equals("2");
        assert js.solveWith(100, 0).equals("2");
        assert js.solveWith(1, 100).equals("1");
        System.out.println("All tests passed for JosephusSolver");
    }
    
}
