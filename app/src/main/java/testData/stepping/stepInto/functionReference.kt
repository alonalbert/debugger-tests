package functionReference

fun test(s: () -> String) {
    s()
}

// Break here and step-into. Lands on 5 instead of 4.
fun a() = "OK"

fun main(args: Array<String>) {
    //Breakpoint!
    test(::a)
}

// STEP_INTO: 7