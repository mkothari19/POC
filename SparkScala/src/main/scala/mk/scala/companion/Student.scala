package mk.scala.companion

class Student(var name:String,var age:Int) {
  
  override def toString=s"$name,$age"
  
}

object Student{
  
  def unapply(s:Student):String=s"${s.name},${s.age}"
  
}