package mk.scala.program

object Implicit {
  def main(args: Array[String]): Unit = {
    val value=10
    implicit val multipe=2
    def multiply(implicit by:Int)=value*by
    val result=multiply
    println(result)
  
    val str1="Hello"
    implicit val str2="World!!"
    
    def printHello(implicit str3:String)=str1+" "+str3
    println("Call printHello with no parameter")
    val result1=printHello
    println(result1)
    
    println("Call printHello with  parameter")
      val result2=printHello("GUYS")
    println(result2)
    
    
  }
}