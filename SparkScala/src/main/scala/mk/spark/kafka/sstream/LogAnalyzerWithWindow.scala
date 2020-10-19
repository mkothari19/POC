package mk.spark.kafka.sstream
import mk.spark.utils.Context
import org.apache.spark.sql.functions.{regexp_extract,col,current_timestamp,window}
import org.apache.spark.sql.streaming.OutputMode

object LogAnalyzerWithWindow extends Context {
  def main(args: Array[String]): Unit = {
   
  
 val accessLines=spark.readStream.text("/Volumes/MYHARDDRIVE/scalaspark-gitrepo/dataset/logs")
 
 // Regular expression to extract results from Apache access logs
 
 val contanctSizeExp="\\s(d+)$"
 val statusExp="\\s(d{3})\\s"
 val generalExp="\"(\\S+)\\s(\\S+)\\s+(\\S+)\""
 val timeExp="\\[(\\d{2}/\\*{3}/\\d{4}:\\d{2}:\\d{2}:\\d{2}:\\d{4})]"
 val hostExp="(^\\S+\\.[\\S+\\.]+\\S+)\\s"
 
 val logDf=accessLines.select(regexp_extract(col("value"),hostExp,1).alias("host"),
     regexp_extract(col("value"),timeExp,1).alias("timestamp"),
     regexp_extract(col("value"),generalExp,1).alias("method"),
     regexp_extract(col("value"),generalExp,2).alias("endpoints"),
     regexp_extract(col("value"),generalExp,3).alias("protocol"),
     regexp_extract(col("value"),statusExp,1).cast("Integer").alias("status"),
     regexp_extract(col("value"),contanctSizeExp,1).cast("Integer").alias("content_type"))
 
  val logDf2=  logDf.withColumn("eventtime",current_timestamp())
       
  val endpointcount=logDf2.groupBy(window(col("eventtime"),"30 seconds","10 seconds"),col("endpoints")).count
  
  val sortedEndpointCount=endpointcount.orderBy(col("count").desc)
  
  val query=sortedEndpointCount.writeStream.outputMode(OutputMode.Complete()).format("console").queryName("counts").start()
   query.awaitTermination()
   spark.close()
  
  
}
}