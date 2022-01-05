package be.technifutur.tp1.serializableComparator;

public abstract class GetSerializableFunction {
    public static <T, R> SerializableFunction<T, R> makeSerializable(SerializableFunction<T, R> function) {
        return function;
    }
}
