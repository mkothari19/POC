package mk.scala.expression

object ExpressionMatching {
  
  def main(args: Array[String]): Unit = {
    println(say(true))
    println(say(false))
    println(say("Hello"))
    
    println(evenOrOdd(7))
    count(5)
  }
  
  def say(word:Any):String=word match{
    case true=> "Your are saying true"
    case false=>"Your are saying false"
    case _=>"Your are saying Nothing"  
  }
  /*
   * Alternate case
   */
  def evenOrOdd(num:Int):String = num match {
    case 1 | 3 | 5 | 7 | 9 =>"odd"
    case 2 | 4 | 6 | 8 | 10 =>"even"
    case _ => "some other number"
  } 
  /*
   * If statement with in case    
   */
      
    def count(n:Int) = n match {
    case 1 => println("one, a lonely number")
    case x if x == 2 || x == 3 => println("two's company, three's a crowd")
    case x if x > 3 => println("4+, that's a party")
    case _ => println("i'm guessing your number is zero or less")
}

}