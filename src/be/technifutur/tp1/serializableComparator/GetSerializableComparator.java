package be.technifutur.tp1.serializableComparator;

import java.io.Serializable;
import java.util.Comparator;

public abstract class GetSerializableComparator<T> implements Comparator<T>, Serializable {
    @Override
    public abstract int compare(T t1, T t2);
}
