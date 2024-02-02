// FILE: defaultAccessors.kt
package defaultAccessors

fun main(args: Array<String>) {
    //Breakpoint!
    // Break here and `step into` 3 times.
    //   JVM: 15, 21, 15
    //   Android: 15, 21, 16
    A().testPublicPropertyInClass()
    testPublicPropertyInLibrary()
}

class A: B() {
    fun testPublicPropertyInClass() {
        prop
        prop = 2
    }
}

open class B {
    public var prop: Int = 1
}

fun testPublicPropertyInLibrary() {
    val myClass = B1()
    myClass.prop
    myClass.prop = 2
}

// STEP_INTO: 21
// SKIP_SYNTHETIC_METHODS: true
// SKIP_CONSTRUCTORS: true

// FILE: customLib/simpleLibFile.kt
//package customLib.simpleLibFile

public fun foo() {
    1 + 1
}

class B1 {
    public var prop: Int = 1
}
