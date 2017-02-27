package org.apache.spark

/**
  * Created by qiudeyang on 28/07/16.
  */
object TestFunction {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("testfunction").setMaster("local[3]")
    val sc = new SparkContext(conf)
    def sum(x:Int)=x+4
    println(sum(6))
    def square(x:String):Int={
      val xInt = x.toInt
      xInt*xInt
    }
    println(square("6"))
  }

}
