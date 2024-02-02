package continueLabel

fun main(args: Array<String>) {
    //Breakpoint!
    for(el in 0..1) {
        val a1 = 1

        // Break here. Resume once and `step into`. Lands on 10 instead of 13.
        if (el == 0) {
            continue

        }
        val a2 = 2
    }
    val a3 = 3
}

// STEP_INTO: 9