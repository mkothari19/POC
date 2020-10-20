package mk.scala.program

class Companion {
  
  def printFileName()
  {
    println("FileName:::::"+Companion.hiddenfilename)
  }
  
}
object Companion{
   private val hiddenfilename="/Volume/MyHardDrive/tmp.log"
  def main(args: Array[String]): Unit = {
   new Companion().printFileName()
  }
  
}