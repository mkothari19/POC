package mk.scala.option



object OptionDemo extends App {
  
val capital=Map("Tokyo"->"Japan","France"->"Paris","Delhi"->"India")

println(show(capital get "Delhi"))
println(show(capital get "XYZ"))

def show(x:Option[String])= x match {
  case Some(s)=>s
  case None=>"?"
    
 
}
  
}