package mk.scala.program

object Implicit {
  def main(args: Array[String]): Unit = {
    val value=10
    implicit val multipe=2
    
    def multiply(implicit by:Int)=value*by
    
    val result=multiply
    
    println(result)
    
  }
}