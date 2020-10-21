package mk.scala.expression

object ExpressionMatching {
  
  def main(args: Array[String]): Unit = {
    println(say(true))
    println(say(false))
    println(say("Hello"))
  }
  
  def say(word:Any):String=word match{
    case true=> "Your are sating true"
    case false=>"Your are saying false"
    case _=>"Your are saying Nothing"  
  }
  
  
}