package mk.scala.companion
/*1.A companion object is an object that’s declared in the same file as a class, and has the same name as the class
2. A companion object and its class can access each other’s private members
3. A companion object’s apply method lets you create new instances of a class without using the new keyword
4. A companion object’s unapply method lets you de-construct an instance of a class into its individual components
 * 
 */
class Student(var name:String,var age:Int) {
  
}

object Student{
 // De-construct object instance 
  def unapply(s:Student):String=s"${s.name},${s.age}"
  
}