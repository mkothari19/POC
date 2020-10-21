package mk.scala.classes

/* trait does not allowed constructor param so abstract class come into picture
 *
 * trait Employee(name:String){} will not work
  
}*/
abstract class Employee(name:String) {
  //Concrete method
   def printName():Unit=println(name)
   
   // abstract method
   def caculateSalay():Unit
  
}