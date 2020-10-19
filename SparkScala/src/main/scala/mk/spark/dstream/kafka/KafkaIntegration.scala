package mk.spark.dstream.kafka
import org.apache.spark.sql.SparkSession
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.kafka010.LocationStrategies
import org.apache.spark.streaming.kafka010.ConsumerStrategies
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.spark.streaming.kafka010.CanCommitOffsets
import org.apache.spark.streaming.kafka010.HasOffsetRanges
import org.apache.spark.sql.functions.{explode}
import org.elasticsearch.spark.sql._
import mk.spark.utils.Context
object KafkaIntegration extends Context {
  def main(args: Array[String]): Unit = {
 val topics=Array("airbnb")
 val sparkcontext=spark.sparkContext
 val kafkaParam=Map[String,Object](ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> "localhost:9092",
  ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG -> classOf[StringDeserializer],
  ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG -> classOf[StringDeserializer],
  ConsumerConfig.GROUP_ID_CONFIG  -> "mygroup",
  ConsumerConfig.AUTO_OFFSET_RESET_CONFIG-> "earliest",
   ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG  -> "false", "spark.kafka.poll.time" -> "5000",
   "spark.streaming.kafka.maxRatePerPartition" -> "25", "spark.streaming.unpersist" -> "true"
      ,"spark.streaming.concurrentJobs" -> "2","spark.scheduler.mode"-> "FAIR")
  
 val ssc=new StreamingContext(sparkcontext,Seconds(2))
 
 //LocationStrategies.PreferConsistent
 //LocationStrategies.PreferBrokers
 //LocationStrategies.PreferFixed
      val stream=KafkaUtils.createDirectStream(ssc, LocationStrategies.PreferBrokers, ConsumerStrategies.Subscribe[String,String](topics, kafkaParam))
     
      //Commit offset to kafka 
         stream.foreachRDD{rdd=>
         val offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
         stream.asInstanceOf[CanCommitOffsets].commitAsync(offsetRanges)
         
        }
 
      val valuesDStream= stream.transform(rdd =>rdd.map(x=>x.value()))
   //   println(valuesDStream.count())
  //val s=stream.map(record => (record.value))
      valuesDStream.foreachRDD{rdd=>
     if(!rdd.isEmpty()){
       val df=spark.read.json(rdd)
         df.saveToEs("pnr/records")
     //val segment= df.select(df.col("PNRid"),df.col("recordLocator"),df.col("BookingDateTime"),explode(df.col("Segments.Segment")).as("Seg"))
       //segment.show()
       
        }
      }  
      
    ssc.start()
    ssc.awaitTermination()
    spark.close()
  }
}