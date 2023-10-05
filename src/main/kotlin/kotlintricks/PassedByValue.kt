package kotlintricks

fun main() {

    val data = Data(1)    // variable data points to a reference say 100 which Data(1) is located at.

    // creating a value which is copy of the reference and modifying that value
    run {
        var copyData = data  // variable copyData also points to 100
        copyData.num = 4

        // since both have same reference both print true
        println(data.num == copyData.num) // true
        println(data === copyData) // true

        copyData = Data(7) // variable copyData points to say 200 which Data(7) is located at.

        // now they point to different location, so both prints false
        println(data.num == copyData.num) //false
        println(data === copyData) // false
    }


    val passedData = passedByValue(data)

    // prints true, so they both have the same reference
    println(passedData === data)

    // prints 10, which indicates that kotlin passes a value which is a copy of the reference.
    println(data.num)

    // so the method works exactly like the run block above
}


fun passedByValue(passedData: Data): Data {
    // passedData is a new variable also pointing at 100

    passedData.num = 10 // the object at 100 will be modified
    return passedData
}


class Data(var num: Int)