package be.technifutur.tp1.serializableComparator;

import java.io.Serializable;
import java.util.Comparator;

public interface GetSerializableComparator<T> extends Comparator<T>, Serializable {
    @Override
    int compare(T t1, T t2);
}
