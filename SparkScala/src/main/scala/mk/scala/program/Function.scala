package mk.scala.program

object Function {
  def main(args: Array[String]): Unit = {
   //1.  Anonymous  
    val fun1=(x:Int,y:Int)=>x+y
    
    //2. Anonymous
    
  var fc2 = (_:Int) + (_:Int)
  
  //3. General
  def fun3(x:Int,y:Int):Int={x+y}  //or   def fun4(x:Int,y:Int)={x+y}
    maxAndMin(3,5)
    
    
  }
  
  
  // 4  Nested 
  
  def maxAndMin(x:Int,y:Int)={
    
    def min()={
      if(x>y){
       println(x +"y is minimun")
       
      }else{
        println(x +"x is minimun") 
      }
      
    }
   
    def max()={
      if(x>y){
         println(x +"x is maximum") 
      }else{
          println(x +"y is maximum") 
      }
    }
    min
    max
  }
  
  
}