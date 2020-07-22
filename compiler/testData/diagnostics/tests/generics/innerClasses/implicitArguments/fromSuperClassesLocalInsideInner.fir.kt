// !WITH_NEW_INFERENCE
// !CHECK_TYPE
// !DIAGNOSTICS: -UNUSED_VALUE -VARIABLE_WITH_REDUNDANT_INITIALIZER -TOPLEVEL_TYPEALIASES_ONLY

class A<R1, R2, R3, R4, R5, R6>

class Outer<T> {
    inner class Inner<F> {
        private fun <E> foobar() = {
            open class LocalOuter<X, Y> {
                inner class LocalInner<Z> {
                    fun a() = A<T, F, E, X, Y, Z>()
                }

                typealias LocalAlias<W> = <!OTHER_ERROR!>A<T, F, E, X, Y, W><!>
            }

            class Derived : LocalOuter<Double, Short>() {
                fun foo(): LocalInner<Long> = null!!
                fun bar(): <!OTHER_ERROR, OTHER_ERROR!>LocalAlias<Char><!> = null!!
            }

            Derived()
        }

        private fun noParameters() = {
            open class LocalOuter2<X, Y> {
                inner class LocalInner2<Z> {
                    fun a() = A<T, F, Any, X, Y, Z>()
                }

                typealias LocalAlias2<W> = <!OTHER_ERROR!>A<T, F, Any, X, Y, W><!>
            }

            class Derived2 : LocalOuter2<Double, Short>() {
                fun foo(): LocalInner2<Long> = null!!
                fun bar(): <!OTHER_ERROR, OTHER_ERROR!>LocalAlias2<Char><!> = null!!
            }
            Derived2()
        }

        fun test(z: Outer<String>.Inner<F>) {
            var x = foobar<String>()
            x = foobar<String>()

            x().foo().a() checkType { <!INAPPLICABLE_CANDIDATE!>_<!><A<T, F, String, Double, Short, Long>>() }
            x().bar() <!INAPPLICABLE_CANDIDATE!>checkType<!> { <!INAPPLICABLE_CANDIDATE!>_<!><A<T, F, String, Double, Short, Char>>() }

            x = foobar<Int>()
            x = z.foobar<String>()

            var y = noParameters()
            y = noParameters()

            y().foo().a() checkType { _<A<T, F, Any, Double, Short, Long>>() }
            y().bar() <!INAPPLICABLE_CANDIDATE!>checkType<!> { <!INAPPLICABLE_CANDIDATE!>_<!><A<T, F, Any, Double, Short, Char>>() }
        }
    }
}
