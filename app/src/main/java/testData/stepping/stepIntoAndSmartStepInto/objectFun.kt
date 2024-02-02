package objectFun

object A {
    fun bar() {
        val a = 1
    }
}

fun main(args: Array<String>) {
    //Breakpoint!
    // Break here, step into: Lands on 3 instead of 5
    A.bar()
}

// IGNORE_K2_SMART_STEP_INTO