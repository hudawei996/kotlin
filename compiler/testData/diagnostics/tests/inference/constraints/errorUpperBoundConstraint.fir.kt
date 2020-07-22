// !LANGUAGE: +NewInference

// FILE: Sam.java

public interface Sam<K> {
    Sam.Result<K> compute();

    public static class Result<V> {
        public static <V> Sam.Result<V> create(V value) {}
    }
}

// FILE: Foo.java

public class Foo {
    public static <T> void foo(Sam<T> var1) {
    }
}

// FILE: test.kt

fun test(e: <!OTHER_ERROR, OTHER_ERROR!>ErrorType<!>) {
    Foo.foo {
        Sam.Result.<!INAPPLICABLE_CANDIDATE!>create<!>(e)
    }
}