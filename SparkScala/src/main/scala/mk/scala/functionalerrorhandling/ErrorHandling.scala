package mk.scala.functionalerrorhandling

import scala.util.{Try,Success,Failure}

object ErrorHandling {
  def main(args: Array[String]): Unit = {
    val result=toInt("1")
     println(result)
    val sum=add("30","20")
    println(sum)
    
  }
  
  /* Using trios Option.Some and None
   * 
   */
  def toInt(n:String):Option[Int]={
      try{
        Some(Integer.parseInt(n.trim()))
      }catch{
        case e: Exception =>None
      }
      
  }
  
      /*
       * Using trios Try,Success and Failure
       */
      def add(a: String,b:String): Try[Int] = Try {
    Integer.parseInt(a.trim)+ Integer.parseInt(b.trim)
    }
    }
