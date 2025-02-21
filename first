fun main() {
    //println("Hello world!")
    //strFunc()
    //mathFunc()
    listsAndArrays()
}

//val - immutable
//var - mutable 

fun mathFunc() 
{
    val x = 7
    val y = 3 
    println(x+y)
    print("Sum in other way")
    val sum = x.plus(y)
    println("Result: $sum")

    val numOfSudents = 50
    if (numOfSudents in 1..50)
    {
        println(numOfSudents)
    }else
    {
        print("Inacessable")
    }

    val res  = 53
    when(res)
    {
        4 -> print("First case")
        in 1..40 ->print("In range")
        else->print("Invaliid res")
    }

    /*

    when(n)
    {
        1->print("no. 1")
        9->print("good shot")
        else-> print("Invalid input $x")
    }
    */
    println("                                        ")

    //var numOfBooks: Int? = null //optionals-?


}


fun strFunc()
{
    val myStr = "Johnny"
    val secondStr : String
    secondStr = "Kowalski"
    println("My name is $myStr $secondStr")

    val array  = arrayOf("Audi","Bmw","Honda")

    for ((index, value) in array.withIndex()) {
        println("the element at $index is $value")
    }

    repeat(2)
    {
        println("Hello")
    }
}

fun listsAndArrays()
{
    val myList = listOf("apple","pear","pinaple")
    println(myList) //unmutable
    val mutableList = mutableListOf(1,5,6,7)
    println(mutableList)
    println(mutableList.remove(0)) //returns boolean
    println(mutableList)
    val mixArr : Array<Any?> = arrayOf("hej",12,4,5,12)
    println("Content ${mixArr.contentToString()}")


    val a: String? = null 
    print(a?.length)
    //print(a!!.length)

}
