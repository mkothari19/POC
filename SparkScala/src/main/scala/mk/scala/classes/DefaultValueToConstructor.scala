package mk.scala.classes

class Socket(var timeout:Int=2000,var linger:Int=4000) {
  
  override  def toString()=s"$timeout and $linger"
  
}


object MainClass{
  def main(args: Array[String]): Unit = {
   println( new Socket())
   println(new Socket(1000))
  println(new Socket(1000,2000))
    
  }
}