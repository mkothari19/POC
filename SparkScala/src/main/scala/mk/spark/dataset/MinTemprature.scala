package mk.spark.dataset


import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.types.{StringType, IntegerType, FloatType}
import org.apache.spark.sql.functions.{round}
import mk.spark.utils.Context

object MinTemprature extends Context {
  case class Temprature(stationId:String,records_date:Int,measure_type:String,temprature:Float)
  
  def main(args: Array[String]): Unit = {
     
    val tempratureSchema=new StructType()
    .add("stationId",StringType,nullable=true)
    .add("records_date",IntegerType,nullable=true)
    .add("measure_type",StringType,nullable=true)
    .add("temprature",FloatType,nullable=true)
    import spark.implicits._
    val records=spark.read.schema(tempratureSchema)/*or  schema(Encoders.product[Temprature].schema)*/
                .csv("/Volumes/MYHARDDRIVE/scalaspark-gitrepo/dataset/1800.csv").as[Temprature]
    
   //println("SIZE "+records.rdd.partitions.size)
    // FILTER ONLY MIN TEMPRATURE
    val mintemp=records.filter($"measure_type"==="TMIN")
    val stationdf=mintemp.select("stationId", "temprature")
    val tempbystationid=stationdf.groupBy($"stationId").min("temprature")
    val result=tempbystationid.withColumn("temprature",round($"min(temprature)"*0.1f*(9.0f/5.0f)+32.0f,2))
                .select("stationId", "temprature").sort("temprature")
    
  result.show
   
  }
  
}