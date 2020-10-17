package mk.scala.program

object RepetedMethodParam {
  def main(args: Array[String]): Unit = {
    
    def add(n:Int*):Int={
      
      n.fold(0)(_+_)
    }
    
    println(add(1,2,3,4,5,6,7,8))
  
    def mul(n:Int*):Int={
      n.product
      
    }
    
    println(mul(Array(10,22,3,4):_*))
    
    def show(x:String,y:Any *)={
      "%s IS %s".format(x,y.mkString("_").toUpperCase())
      
    }
    
    println(show("MY NAME","Manish Kothari"))
  
  }
  
}