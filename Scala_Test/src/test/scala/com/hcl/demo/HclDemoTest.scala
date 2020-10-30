package com.hcl.demo
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
import  org.scalatest.exceptions.TestFailedException
import org.scalactic.source.Position.apply

@RunWith(classOf[JUnitRunner])
class HclDemoTest extends FunSuite with BeforeAndAfter {
  test("Print 1 to 10 with comma seperator") {
    val s = HclDemo.printNumber(10,",")
   
    assert(s.equals("1,2,3,4,5,6,7,8,9,10"))
  }

   test("Print 1 to 10 with space seperator") {
     val s = HclDemo.printNumber(10," ")
   
    assert(s.equals("1 2 3 4 5 6 7 8 9 10"))
  }
    test("Not excepted result") {
     val s = HclDemo.printNumber(10," ")
   
    assert(!s.equals("1 2 3 4 5 6 7 8"))
  }
  
    test("Calculate AOT should give 50 when base is 10 and hight is 10") {
    val s = HclDemo.calculateAOT(10,10)
   
    assert(s==50)
  }

  test("Calculate AOT should not  give 50 when base is 5 and hight is 10") {
    val s = HclDemo.calculateAOT(5,10)
   
    assert(s!=50)
  }
  
  test("Are of trangle is zero when base is 0 ") {
    val s = HclDemo.calculateAOT(0,10)
   
    assert(s==0)
  }
    
   
  
}