package util;

import java.util.Objects;

public class Tuple<T1, T2> {
    public T1 item1;
    public T2 item2;

    public Tuple(T1 item1, T2 item2) {
        this.item1 = item1;
        this.item2 = item2;
    }

    public T1 getItem1() {
        return item1;
    }

    public void setItem1(T1 item1) {
        this.item1 = item1;
    }

    public T2 getItem2() {
        return item2;
    }

    public void setItem2(T2 item2) {
        this.item2 = item2;
    }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) return true;
        if (anObject == null || getClass() != anObject.getClass()) return false;
        Tuple<?, ?> aTuple = (Tuple<?, ?>) anObject;
        return item1.equals(aTuple.item1) &&
                item2.equals(aTuple.item2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item1, item2);
    }
}

