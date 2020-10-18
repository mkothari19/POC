package mk.scala.program.opps

class Bank {
  
  def intresRate():Double={
    0
  }
}

class Axis extends Bank{
  
 override  def intresRate():Double={
    7.2
  }
   }

 class ICICI extends Bank{
override    def intresRate():Double={
    7.5
  }
 }
 
 class Yes extends Bank{
  override  def intresRate():Double={
    7.5
  }
 }
 object BankDriver{
   def main(args: Array[String]): Unit = {
     println("AXIS= "+ new Axis().intresRate())
     println("ICICI= "+ new ICICI().intresRate())
     println("Yes= "+ new Yes().intresRate())
     
   }
 }