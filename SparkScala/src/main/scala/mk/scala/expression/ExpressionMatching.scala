package mk.scala.expression

object ExpressionMatching {
  
  def main(args: Array[String]): Unit = {
    println(say(true))
    println(say(false))
    println(say("Hello"))
  }
  
  def say(word:Any):String=word match{
    case true=> "Your are saying true"
    case false=>"Your are saying false"
    case _=>"Your are saying Nothing"  
  }
  
  def evenOrOdd(num:Int):String = num match {
    case 1 | 3 | 5 | 7 | 9 =>"odd"
    case 2 | 4 | 6 | 8 | 10 =>"even"
    case _ => "some other number"
}
}