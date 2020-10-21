package mk.scala.classes

class Person(var firstname:String,var lastname:String) {
  
  println("***Contructor beging***")
  var age=50
  private val home=System.getProperty("user.home")  
  
  override def toString():String=s"$firstname  $lastname age is $age"
  
 val printhome:Unit=println(s"${home}")
 val printFullname:Unit=println(this)
 
 printhome
 printFullname
 
 println("End of constructor")
  
  
}

object MainClass1{
  def main(args: Array[String]): Unit = {
   new Person("Maneesh","Kothari")
  }
}