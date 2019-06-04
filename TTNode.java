/**
 * @author Vadim Yastrebov (CFD2) 100908473
 * @version 2017.08.15
 */
public class TTNode<T> {
    private TTNode<T> left, right, middle;
    private T low, high;

    public TTNode(T value) {
        high = value;
        low = value;
        left = null;
        right = null;
        middle = null;
    }
    public TTNode(T lowKey, T highKey, TTNode<T> left, TTNode<T> middle, TTNode<T> right) {
        high = highKey;
        low = lowKey;
        this.left = left;
        this.right = right;
        this.middle = middle;
    }

    public TTNode<T> getLeft() {
        return left;
    }

    public TTNode<T> getMiddle() {
        return middle;
    }

    public TTNode<T> getRight() {
        return right;
    }

    public T getHigh() {
        return high;
    }

    public T getLow() {
        return low;
    }

    public void setLeft(TTNode<T> left) {
        this.left = left;
    }

    public void setRight(TTNode<T> right) {
        this.right = right;
    }

    public void setMiddle(TTNode<T> middle) {
        this.middle = middle;
    }

    public void setLow(T low) {
        this.low = low;
    }

    public void setHigh(T high) {
        this.high = high;
    }

    public boolean isLeaf() {
        return left == null && right == null && middle == null;
    }

    public boolean canInsert() {
        return high == low;
    }

    public boolean hasChild() {return left != null && high != null;}

    //<editor-fold desc="Unused code">
    //    public boolean setValue(T value) {
//        if (low != null || value == null) {
//            return false;
//        } else {
//            if ((int) high < (int) value) {
//                low = high;
//                high = value;
//                return true;
//            } else {
//                low = value;
//                return true;
//            }
//        }
//    }

//    public void removeValue(T value) {
//        if (value == low) {
//            low = null;
//        } else if (value == high) {
//            high = low;
//            low = null;
//        }
//    }
    //</editor-fold>

}
