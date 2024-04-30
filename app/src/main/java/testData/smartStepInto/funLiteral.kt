package funLiteral

fun main() {
    //Breakpoint!
    f1() {
        f2()
    }
}

fun f1(f: () -> Unit) { f() }
fun f2() {}

// SMART_STEP_INTO_BY_INDEX: 2 - Will result in app hanging.
