package mk.scala.caseclass

/*Case class constructor parameters are public val fields by default, 
 * so accessor methods are generated for each parameter.
An apply method is created in the companion object of the class, 
so you don’t need to use the new keyword to create a new instance of the class.
An unapply method is generated, which lets you use case classes in more ways in match expressions.
A copy method is generated in the class. You may not use this feature in Scala/OOP code,
 but it’s used all the time in Scala/FP.
equals and hashCode methods are generated, which let you compare objects and easily use them as keys in maps.
A default toString method is generated, which is helpful for debugging.
These features are all demonstrated in the following sections.
 * 
 */
trait Person{
  def name:String
}

case class Student(name:String,age:Int) extends Person
case class Teacher(name:String,subject:String) extends Person

object CaseClassDemo {
  def main(args: Array[String]): Unit = {
    /** Apply**/
    val s=Student("Maneesh",30)
    println("name::"+s.name +" age:: "+s.age)
    /**Unapply **/
    
    val s1=new Student("Maneesh",40)
    val t=new Teacher("Kothari","Maths")
    
  println( getPrintableString(s1))
  println(getPrintableString(t))
    
  }
  
  def getPrintableString(p: Person): String = p match {
    case Student(name, age) =>
        s"$name is a student age $age."
    case Teacher(name, whatTheyTeach) =>
        s"$name teaches $whatTheyTeach."
}
}