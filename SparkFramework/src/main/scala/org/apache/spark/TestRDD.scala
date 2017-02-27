package org.apache.spark

/**
  * Created by qiudeyang on 19/07/16.
  */
object TestRDD {
  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local").setAppName("TestInput")
    val sc = new SparkContext(conf)
    val input = sc.textFile("/tmp/a.sh")
    val linelength = input.map( x => x.length).persist()
    val filelength = linelength.reduce((x,y) => x+y)
    println(filelength)
  }

}
