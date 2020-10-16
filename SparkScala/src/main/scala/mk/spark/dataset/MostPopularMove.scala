package mk.spark.dataset
import scala.io.Codec
import scala.io.Source
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{StructType,IntegerType,StringType}
import org.apache.spark.sql.functions.{col,udf,desc}
import org.elasticsearch.spark.sql._
import org.apache.spark.sql.DataFrame

object MostPopularMove {
 case class MovieList(movieid:Int,custid:Int,rating:Int,release_date:String)
 
  def loadmoviename(): Map[Int,String]={
   // implicit val codec:Codec=Codec("ISO-8859-1")
    var moviename:Map[Int,String]=Map()  
    val lines=Source.fromFile("/Volumes/MYHARDDRIVE/scalaspark-gitrepo/dataset/moviename.items")
    for(line<-lines.getLines()){
    
      val fields=line.split('|')
      if(fields.length>1){
      moviename += (fields(0).toInt->fields(1))  
      }
      
    }
    lines.close()
      moviename
  }
  
  
  def main(args: Array[String]): Unit = {
     val spark=SparkSession.builder().appName("Top Rating Movie").master("local[*]")
     .config("spark.es.nodes","localhost")
    .config("spark.es.port","9200")
    //.config("spark.es.nodes.wan.only","true") //Needed for ES on AWS
     .getOrCreate()
    val namedic=spark.sparkContext.broadcast(loadmoviename)
    val schema=new StructType()
                .add("movieid",IntegerType,nullable=true)
                .add("custid",IntegerType,nullable=true)
                 .add("rating",IntegerType,nullable=true)
                  .add("release_date",StringType,nullable=true)
    
    import spark.implicits._
    val moviedataset=spark.read.option("sep","\t")
    .schema(schema)
    .csv("/Volumes/MYHARDDRIVE/MyData/movierating.data")
    .as[MovieList]
    
    val moviecount=moviedataset.groupBy("movieid").count()
   
    val lookupname:Int=>String =(movieid:Int)=>{
      namedic.value(movieid)
      
    }
    
    
    val lookNameUdf=udf[String,Int](lookupname)
    
   val moviewithname=moviecount.withColumn("MovieTitle", lookNameUdf(col("movieid"))) 
   val result=moviewithname.sort(desc("count"))
   result.toJSON.toDF().saveToEs("movie/popular")

   result.show()
   
  }
  
}