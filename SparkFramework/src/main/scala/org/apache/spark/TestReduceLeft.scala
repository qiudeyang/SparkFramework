package org.apache.spark

/**
  * Created by qiudeyang on 28/07/16.
  */
object TestReduceLeft {
  def main(args: Array[String]) {
    println(List(1,2,3).reduceLeft(_ + _))
  }


}
