// !LANGUAGE: -PrivateInFileEffectiveVisibility
// JVM_ABI_K1_K2_DIFF
// Reason: ^ unsupported language feature switch OFF
package p

private class C(val y: Int) {
    val initChild = { ->
        object {
            override fun toString(): String {
                return "child" + y
            }
        }
    }
}

fun box(): String {
    val c = C(3).initChild
    val x = c().toString()
    return if (x == "child3") "OK" else x
}
