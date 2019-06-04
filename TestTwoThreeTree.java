/**
 * @author Vadim Yastrebov (CFD2), Dmytro Sytnik (VanArman)
 * @version 15 August, 2017
 */
public class TestTwoThreeTree {
    public static void main(String[] args) {
        TTNode<Integer> one = new TTNode<>(1);
        TTNode<Integer> three = new TTNode<>(3);
        TTNode<Integer> five = new TTNode<>(5);
        TTNode<Integer> seven = new TTNode<>(7);
        TTNode<Integer> nineTen = new TTNode<>(10, 9, null, null, null);
        TTNode<Integer> two = new TTNode<>(2, 2, one, null, three);
        TTNode<Integer> sixEight = new TTNode<>(8, 6, five, seven, nineTen);
        TTNode<Integer> root = new TTNode<>(4, 4, two, null, sixEight);


        TwoThreeTree<Integer> TTTreee = new TwoThreeTree<>(root);
        TwoThreeTree<Integer> TTTree2 = new TwoThreeTree<>();

        for(int i = 1; i <= 10; i++){
            TTTree2.insert(i);
        }

        for (int i = 1; i <= 10; i++) {
            System.out.print("# " + i + " theoretical value:\t");
            try {
                System.out.println(TTTreee.search(i) + " exact value:\t" + TTTree2.search(i));
            } catch (Exception e) {
                System.out.println("(X,,,X)");
            }
        }

        System.out.println("======================\tIn order print\t======================");
        TTTreee.print();
    }
}