package mk.scala.exception

import scala.io.Source
import java.io.FileNotFoundException
import java.io.IOException

object Exceptions {
  def main(args: Array[String]): Unit = {
    
    try{
      val filename=args(0)
      loadmoviename(filename)
    }catch{
      case e:FileNotFoundException=>println("Input file is not available")
      case e:IOException=>println("IOException occured to read a file")
      case _:Throwable=>println("Got some other exception")
    }
  }
  
  
  def loadmoviename(filename:String): Map[Int,String]={
   // implicit val codec:Codec=Codec("ISO-8859-1")
    var moviename:Map[Int,String]=Map()  
    val lines=Source.fromFile( filename)
    for(line<-lines.getLines()){
    
      val fields=line.split('|')
      if(fields.length>1){
      moviename += (fields(0).toInt->fields(1))  
      }
      
    }
    lines.close()
      moviename
  }
  
}