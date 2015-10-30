package hw07;

public class OrderedListAr <A extends Comparable<? super A>>
        extends OrderedList<A> {
    private A[] vals;
    private int size;
    private int trueSize;
    private boolean isAscending;
    private final static int MIN_ARRAY_SIZE = 16;

    private void enlargeArray() {
        size *= 2;
        A[] newAr = (A[]) new Comparable[size];
        System.arraycopy(vals, 0, newAr, 0, size / 2);
        vals = newAr;
    }
    OrderedListAr (A[] array, boolean ifAscending){
        if (array == null) {
            trueSize = 0;
        }
        size = array.length * 2;
        vals = (A[]) new Comparable[size];
        isAscending = ifAscending;
        System.arraycopy(array, 0, vals, 0, array.length);
    }

    public A[] getVals() {
        return vals;
    }

    private boolean isInOrder(A fst, A snd) {
        return ((fst.compareTo(snd) <= 0) == isAscending);
        /*ascending == ascending
        descending != ascending
        ascending != descending
        descending == descending
         */
    }

    @Override
    public int getSize() {
        return trueSize;
    }

    @Override
    public A getVal(int index) {
        if (index >= trueSize) return null;
        return vals[index];
    }

    private void moveR(int ind) {
        if (ind == size) {
            enlargeArray();

            vals[ind + 1] = vals[ind];
            vals[ind] = null;
            return;
        }
        if (vals[size - 1] == null) {//dubious
            for (int i = trueSize - 1; i > ind; i--)
                vals[i] = vals[i - 1];
            vals[ind] = null;
            return;
        }
        enlargeArray();

        for (int i = size / 2; i > ind; i--)//`size/ 2` will be enough
            vals[i] = vals[i - 1];
        vals[ind] = null;
    }

    private void moveL(int ind) {
        if (ind <= 0) return;
        for (int i = ind; i < trueSize - 1; i++)
            vals[i] = vals[i + 1];
        vals[size - 1] = null;
    }

    private void setValue(A val, int l, int r) {
        int mid = (r - l) / 2 + l;
        if (r - l <= 1) {
            if (isInOrder(val, vals[mid])) {
                moveR(mid);//including mid
                vals[mid] = val;
                return;
            }
            moveR(mid + 1);
            vals[mid + 1] = val;
        }
        if (val.equals(mid)) {
            moveR(mid);//logically, it'd be `mid + 1`, but it's possible it does not exist.
            vals[mid] = val;
            return;
        }
        if (isInOrder(val, vals[mid]))
            setValue(val, l, mid);
        else
            setValue(val, mid + 1, r);
    }

    @Override
    public void setVal(A val) {
        if (trueSize == 0) {
            size = MIN_ARRAY_SIZE;
            vals = (A[]) new Comparable[size];
            vals[0] = val;
        }
        setValue(val, 0, size - 1);
        trueSize++;
    }

    @Override
    public void delVal(int index) {
        if (index > -1 && index < trueSize) {
            moveL(index + 1);
            trueSize--;
        }
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        for (int i = 0; i < trueSize; i++) {
            A curVal = vals[i];
            hashCode = 31 * hashCode + (curVal == null ? 0 : curVal.hashCode());
        }
        return hashCode;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof OrderedList)) return false;
        OrderedList otherList = (OrderedList) other;
        boolean res = true;
        if (size != otherList.getSize()) return false;
        for (int i = 0; i < trueSize; i++) {
            A curThis = vals[i];
            //unfortunately, I can't assign `otherList.getVal(i)` to a variable, for it has unknown type.
            if (curThis == null && otherList.getVal(i) != null) return false;
            if (curThis != null && otherList.getVal(i) == null) return false;
            if (!(curThis == null && otherList.getVal(i) == null))
                res = res && curThis.equals(otherList.getVal(i));
            //if both are nulls, we shouldn't do anything.
        }
        return res;
    }

    @Override
    public int compareTo (OrderedList<? extends A> other) {
        int minSize = Math.min(trueSize, other.getSize());
        for (int i = 0; i < minSize; i++) {
            int curCompare = vals[i].compareTo(other.getVal(i));
            if (curCompare != 0) return curCompare;
        }
        if (trueSize < other.getSize()) return -1;
        if (trueSize > other.getSize()) return 1;
        return 0;
    }
}