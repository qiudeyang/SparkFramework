package org.apache.spark

/**
  * Created by qiudeyang on 19/07/16.
  */
import org.apache.spark.SparkContext._
object TestInput {
  def main(args:Array[String]): Unit ={
    val conf = new SparkConf().setMaster("local").setAppName("TestInput")
    val sc = new SparkContext(conf)
    val input = sc.textFile("/tmp/a.sh")
    val words = input.flatMap(x => x.split(" "))
    val count = words.map(word => (word,1)).reduceByKey((x,y) => x+y)
    println(count)
    count.saveAsTextFile("/tmp/b.sh")
  }

}
