// TARGET_BACKEND: WASM
// USE_NEW_EXCEPTION_HANDLING_PROPOSAL
// TODO: remove the test when KT-66906 will be resolved
class MyString {
    var s = ""
    operator fun plus(x : String) : MyString {
        s += x
        return this
    }

    override fun toString(): String {
        return s
    }
}


fun test1() : MyString {
    var r = MyString()
    try {
        r + "Try1"

        try {
            r + "Try2"
            if (true)
                return r
        } finally {
            r + "Finally2"
            if (true) {
                return r
            }
        }

        return r
    } finally {
        r + "Finally1"
    }
}


fun box(): String {
  return if (test1().toString() == "Try1Try2Finally2Finally1") "OK" else "fail: ${test1()}"
}