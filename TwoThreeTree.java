/**
  * @author Vadim Yastrebov
 * @version 2017.08.15
 */
public class TwoThreeTree <T extends Comparable> {
    private TTNode<T> root;

    public TwoThreeTree(TTNode<T> root){
        this.root = root;
    }

    public TwoThreeTree() {
        this.root = null;
    }

    public T search(T value) {  //TODO
        if (value != null) {
            return search(root, value).getLow();
        }
        return null;
    }

    private TTNode<T> search(TTNode<T> current, T value) {
        if (current == null) {
            return null;
        }

        if (!current.isLeaf()) {
            int compareHigh = value.compareTo(current.getHigh());
            int compareLow = value.compareTo(current.getLow());
            int compareChildLow, compareChildHigh;

            if (compareLow < 0) {
                //going left.

                compareChildLow = value.compareTo(current.getLeft().getLow());
                compareChildHigh = value.compareTo(current.getLeft().getHigh());
                if (compareChildLow == 0 || compareChildHigh == 0) {
                    return current;
                }
                return search(current.getLeft(), value);
            } else if (compareHigh > 0) {
                //going right

                compareChildLow = value.compareTo(current.getRight().getLow());
                compareChildHigh = value.compareTo(current.getRight().getHigh());
                if (compareChildLow == 0 || compareChildHigh == 0) {
                    return current;
                }
                return search(current.getRight(), value);

            } else if (compareLow > 0 && compareHigh < 0) {
                //going middle

                compareChildLow = value.compareTo(current.getMiddle().getLow());
                compareChildHigh = value.compareTo(current.getMiddle().getHigh());
                if (compareChildLow == 0 || compareChildHigh == 0) {
                    return current;
                }
                return search(current.getMiddle(), value);
            }
        }
        return current;
    }

    public void print() {
        traverse(root);
    }

    public void insert(T value) {
        insert(root, value);
        }
    //<editor-fold desc="useless code">
    //        TTNode<T> found = search(root, value);
//        int compareHigh = value.compareTo(found.getHigh());
//        int compareLow = value.compareTo(found.getLow());
//        int compareChildLow, compareChildHigh;

        //<editor-fold desc="Что мы накодили вчера">
        //        if (found.canInsert()) {
//            if (compareHigh > 0) {
//                found.setHigh(value);
//            } else {
//                found.setLow(value);
//            }
//        } else {
//            if (compareHigh < 0 && compareLow > 0) {
//                //value is to be pushed uplevel
//                found.setLeft(new TTNode<T>(found.getLow()));
//                found.setRight(new TTNode<T>(found.getHigh()));
//                found.setHigh(value);
//                found.setLow(value);
//            } else if (compareLow < 0) {
//                //value is to the left
//                found.setLeft(new TTNode<T>(value));
//                found.setRight(new TTNode<T>(found.getHigh()));
//                found.setHigh(found.getLow());
//            } else if (compareHigh > 0) {
//                //value to the right
//                found.setRight(new TTNode<T>(value));
//                found.setLeft(new TTNode<T>(found.getLow()));
//                found.setLow(found.getHigh());
//            }
        //</editor-fold>


        /* if inserting into the leaf */
        //<editor-fold desc="The big idea">
        /*

        Тема в том, что нам нужно определить, исходя из found ноды, куда мы пихаем: влево, вправо или в середину.
        После этого мы проверяем ноду left/right/middle на canInsert() && isLeaf(). Если да, то у нас простой случай и
        мы просто пихаем это значение в эту ноду и дерево перестраивать не нужно.
         */

        /* Смотрим, в какую сторону идти, чтоб найти одну из трех возможных нужных нам дочерних нод.
        * лишь бы эта found нода не была рутом
        */

        /*  (Страница 709 Insert 36) Если же вставляем значение в забитую ноду и она isLeaf(), то мы как бы вставляем
        *это значение третьим значением этой ноды. Потом эту ноду мы делим на три (исходящие из одного парента) и
        * среднее значение (из этих трех) поднимаем в парент.
        * Если же этот парент забит, то надо опять делить ноду на три и среднее значение пушить наверх. Два оставшихся значения
        * с первого пуша надо перевязать на значения сплит нод этого парента.(левая идет справа от левого сплит-парента,
        * а правая идет слева от правого сплит-парента) (левая пара самых дочерних элементов идет к low сплит-паренту,
        * а правая к high сплит-паренту
        *

         */
        //</editor-fold>
        //<editor-fold desc="Что я делал ночью">
//         if (compareHigh < 0 && compareLow > 0 && found.getMiddle().isLeaf()) {
//            // If the target child leaf node is in the middle
//        } else if (compareLow < 0 && found.getLeft().isLeaf()) {
//            //If the target child leaf node is on the left
//            compareChildHigh = value.compareTo(found.getLeft().getHigh());
//            compareChildLow = value.compareTo(found.getLeft().getLow());
//            if (found.getLeft().canInsert()) {
//                //if inserting into the leaf which has one value.       <----- EASY CASE
//                if (compareChildHigh > 0) {
//                    //if what we insert is to be a larger or smaller value (high or low)
//                    found.getLeft().setHigh(value);
//                } else {
//                    found.getLeft().setLow(value);
//                }
//            } else {
//                // <----- IF NOT EASY
//            }
//        } else if (compareHigh > 0 && found.getRight().isLeaf()) {
//            //If the target child leaf node is on the right
//        }
        //</editor-fold>

//        if (found.isLeaf()) {
//            if (found.canInsert()) {
//                //easy insert
//                if ((value.compareTo(found.getLow()) < 0)) {
//                    found.setLow(value);
//                } else {
//                    found.setHigh(value);
//                }
//            } else {
//                //hardcore insert
//                split(found, value);
//            }
//
//        }
    //</editor-fold>

    private void insert(TTNode<T> current, T value) {
        if (root == null) {
            root = new TTNode<T>(value);
            return;
        }
        TTNode<T> found = search(current, value);
        if (found.canInsert()) {
            //easy insert
            if ((value.compareTo(found.getLow()) < 0)) {
                found.setLow(value);
            } else {
                found.setHigh(value);
            }
        } else {
            //hardcore insert
            split(found, value);
        }

    }

    private void split(TTNode<T> current, T value) {
        TTNode<T> newRoot;
        T loVal = min(value, current.getHigh(), current.getLow());
        T midVal = mid(value, current.getHigh(), current.getLow());
        T hiVal = max(value, current.getHigh(), current.getLow());

        if (!current.hasChild()) {

            newRoot = new TTNode<>(midVal);
            newRoot.setLeft(new TTNode<>(loVal));
            newRoot.setRight(new TTNode<>(hiVal));


            if (current.equals(root)) {
                root = newRoot;
            } else {
                split(newRoot, midVal);
            }

        } else {
            TTNode<T> searchedNode = search(root, current.getLow());

            if (searchedNode.getMiddle() == null) {
                if (midVal.compareTo(searchedNode.getLow()) > 0) {
                    searchedNode.setHigh(midVal);

                    searchedNode.setMiddle(current.getLeft());
                    searchedNode.setRight(current.getRight());
                } else {
                    searchedNode.setLow(midVal);

                    searchedNode.setMiddle(current.getRight());
                    searchedNode.setLeft(current.getLeft());
                }

            } else {
                if (searchedNode.equals(root)) {
                    midVal = mid(current.getLow(), searchedNode.getHigh(), searchedNode.getLow());
                    loVal = min(current.getLow(), searchedNode.getHigh(), searchedNode.getLow());

                    newRoot = new TTNode<>(midVal);

                    newRoot.setLeft(new TTNode<>(loVal));
                    newRoot.getLeft().setLeft(searchedNode.getLeft());
                    newRoot.getLeft().setRight(searchedNode.getMiddle());

                    newRoot.setRight(current);

                    root = newRoot;
                } else {
                    split(current, searchedNode.getLow());
                }
            }
        }

    }

    private T min(T val1, T val2, T val3){
        if (val1.compareTo(val2) < 0 && val1.compareTo(val3) < 0) {
            return val1;
        } else if (val2.compareTo(val1) < 0 && val2.compareTo(val3) < 0) {
            return val2;
        } else {
            return val3;
        }
    }

    private T mid(T val1, T val2, T val3) {
        if (val1.compareTo(val2) < 0 && val1.compareTo(val3) > 0) {
            return val1;
        } else if (val1.compareTo(val3) < 0 && val2.compareTo(val3) > 0) {
            return val3;
        } else {
            return val2;
        }
    }

    private T max(T val1, T val2, T val3) {
        if (val1.compareTo(val2) > 0 && val1.compareTo(val3) > 0) {
            return val1;
        } else if (val2.compareTo(val1) > 0 && val2.compareTo(val3) > 0) {
            return val2;
        } else {
            return val3;
        }
    }

    private void traverse(TTNode<T> current){
        if (current == null) return;
        traverse(current.getLeft());
        System.out.print(current.getLow() + " ");
        if (current.getMiddle() != null) traverse(current.getMiddle());
        if (!current.canInsert()) System.out.print(current.getHigh() + " ");
        traverse(current.getRight());
    }
}
