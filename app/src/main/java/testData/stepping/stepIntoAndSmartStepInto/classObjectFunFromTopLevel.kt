package classObjectFunFromTopLevel

class A {
    companion object {
        fun bar() {
            val a = 1
        }
    }
}

fun main(args: Array<String>) {
    //Breakpoint!
    // Break here, step into: Lands on 4 instead of 6
    A.bar()
}

// IGNORE_K2_SMART_STEP_INTO