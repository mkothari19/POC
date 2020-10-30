package mk.promo

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
import scala.collection.mutable.Map
import mk.scala.option.OptionDemo

@RunWith(classOf[JUnitRunner])
class OptionDemoTest extends FunSuite with BeforeAndAfter {
  var data=Map.empty[String,String]
  before{
    data++=Map("Tokyo"->"Japan","France"->"Paris","Delhi"->"India")
  }
  
  test("Test capitals of city"){
    val result=OptionDemo.show(data get "Delhi")
    
    assert(result=="India")
}
}