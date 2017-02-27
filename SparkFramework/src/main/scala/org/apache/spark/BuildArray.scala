package org.apache.spark

/**
  * Created by qiudeyang on 19/07/16.
  */

abstract class Foo{
     val x:Int
     var y:Long
}
//class Bar extends Foo{
//  override def x:Int = 42
//  override def y:Long = 84L
//}

object BuildArray {
  def main(args: Array[String]) {
//    val a = ArrayBuffer(1,2,3,4,5)
//    val b = Array(6,7,8)
//    val c = b.toList
//    println(c)
//    var bar = new Bar()
//    println(bar.x)
//    println(bar.y)
    def divRem(a:Int,b:Int) =(a/b,a%b)
    val (q,r) = divRem(20,6)
    println(q)
    println(r)
  }


}
