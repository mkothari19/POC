package mk.scala.functionalerrorhandling

object ErrorHandling {
  def main(args: Array[String]): Unit = {
    val result=toInt("1")
     println(result)
    
  }
  def toInt(n:String):Option[Int]={
      try{
        Some(Integer.parseInt(n.trim()))
            
      }catch{
        case e: Exception =>None
      }
      
    }
}