package com.hcl.buninss

import scala.util.Success

import org.junit.runner.RunWith
import org.scalatest.BeforeAndAfter
import org.scalatest.FunSuite

import com.caseclass.business.Airthmetic
import com.caseclass.business.Bank
import org.scalatest.junit.JUnitRunner
import scala.collection.mutable.Map


@RunWith(classOf[JUnitRunner])
class DiscountTest extends FunSuite with BeforeAndAfter{
  
  var empdata=Map.empty[String,String]
  before{
    empdata++=Map("10"->"Maneesh","20"->"Rahul","30"->"Payal")
    
  }
  
  test("Discount test"){
    val discountprice=Bank.discount(100.0,10)
    assert(discountprice===Success(90.0))
  }
 
  test("Devision by zero"){
     
   val thrown=  intercept[ArithmeticException]{
     val v=Airthmetic.devision(10, 0)  
   
     }
     assert(thrown.getMessage === "/ by zero")
   }
  test("Test employee name"){
    val name=Bank.showEmployee(empdata get "10")
   
    assert(name==="Maneesh")
  }
  test("Test employee name is not available"){
    val name=Bank.showEmployee(empdata get "50")
    assert(name==="?")
  }
}