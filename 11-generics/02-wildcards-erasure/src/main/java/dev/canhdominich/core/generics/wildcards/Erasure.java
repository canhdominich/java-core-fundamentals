package dev.canhdominich.core.generics.wildcards;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public final class Erasure {

    private Erasure() {
    }

    public static String sameClassAtRunTime() {
        List<String> strings = new ArrayList<>();
        List<Integer> integers = new ArrayList<>();
        return "ArrayList<String> and ArrayList<Integer> share one class: " + (strings.getClass() == integers.getClass())
                + ", the class name is " + strings.getClass().getSimpleName();
    }

    public static String erasureOfTypeParameters() throws NoSuchMethodException {
        Method get = Holder.class.getMethod("get");
        Method set = Holder.class.getMethod("set", Number.class);
        return "Holder<T extends Number>: get() returns " + get.getReturnType().getSimpleName() + ", set takes " + set.getParameterTypes()[0].getSimpleName()
                + " (the bound replaces T)";
    }

    public static String bridgeMethods() {
        int bridges = 0;
        StringBuilder names = new StringBuilder();
        for (Method method : StringBox.class.getDeclaredMethods()) {
            if (method.isBridge()) {
                bridges++;
                names.append(method.getName()).append('(').append(method.getParameterTypes()[0].getSimpleName()).append(')');
            }
        }
        return "StringBox has " + bridges + " compiler-made bridge method: " + names;
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] newArray(Class<T> type, int length) {
        return (T[]) Array.newInstance(type, length);
    }

    public static String arrayThroughAClassToken() {
        String[] strings = newArray(String.class, 3);
        return "created a " + strings.getClass().getSimpleName() + " of length " + strings.length + " using Class<T> as a token";
    }

    public static <T> T create(Class<T> type) throws ReflectiveOperationException {
        return type.getDeclaredConstructor().newInstance();
    }

    public static String instanceThroughAClassToken() throws ReflectiveOperationException {
        StringBuilder made = create(StringBuilder.class);
        return "created an empty " + made.getClass().getSimpleName() + " with capacity " + made.capacity();
    }
}
