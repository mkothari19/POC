package mk.scala.traits

trait TraitsDemo {
  def printMessage
  def printMessage(message:String)={
    println(message)
  }
  
  //Abstract field
  var a:Int
  //Concrete fields
  var b=10
  val c:Int
  
}

class MyClass extends TraitsDemo{
   //Override traits field
   var a=20
    b=50 
    override val c=30
     //Override traits abstract method
   def printMessage(){
     println("a ::: "+a)
     println("b::: "+b)
      println("c::: "+c)
   } 
   
}

object MainClass {
  def main(args: Array[String]): Unit = {
    val obj=new MyClass()
    obj.printMessage()
  }
}