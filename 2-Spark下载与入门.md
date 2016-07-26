## Spark核心概念

   从上层来看，每个Spark应用都由一个驱动器程序（driver program）来发起集群上的各种并行操作。对于Spark-shell而言，实际的驱动器程序就是Spark shell本身，你只需要输入想要运行的操作就可以了。
驱动器程序通过一个SparkContext对象来访问Spark。这个对象代表对计算集群的一个连接。shell启动时已经自动创建了一个SparkContext对象，是一个叫做sc的变量。输入sc以后：

res0: org.apache.spark.SparkContext = org.apache.spark.SparkContext@1c95c2b0

一旦有了SparkContext，你就可以用它来创建RDD。比如，我们调用sc.textFile( )来创建一个代表文件中各行文本的RDD
要执行这些操作，驱动器程序一般要管理多个执行器（executor）节点。比如，如果我们是在集群上运行count（）操作，那么不同的节点会统计文件的不同部分的行数。如果是在本地模式上运行，则所有的工作会在单个节点上执行。

其实Spark API最神奇的地方就在于像filter这样基于函数的操作也会在集群上并行执行。也就是说，Spark会自动将函数（比如line.contains("Python")）发到各个执行节点上，这样，你就可以在单一的驱动器程序中编程，并且让代码自动运行在多个节点上。

在Python中，你可以把应用写成Python脚本，但是需要使用Spark自带的bin/spark-submit脚本来运行。spark-submit脚本会帮我们引入Python程序的Spark依赖，这个脚本为Spark的Python API配置好了运行环境。

## 初始化SparkContext
导入Spark包并且创建SparkContext。可以通过先创建一个SparkConf对象来配置你的应用，然后基于这个SparkConf创建一个SparkContext对象。

import  org.apache.spark.SparkConf

import  org.apache.spark.SparkContext

import  org.apache.spark.SparkContext._

//本机调试时需要加`setMaster("local")`，集群上测试时不需要

val conf = new SparkConf( ).setMaster("local").setAppName("wordcount")

val  sc = new SparkContext(conf)

关闭Spark可以调用SparkContext的stop()方法，或者直接退出应用（比如通过System.exit(0)或者sys.exit()）

## Scala版本的wordcount程序

//创建一个Scala版本的Spark Context

val conf = new SparkConf( ).setMaster("local").setAppName("wordcount")

val  sc = new SparkContext(conf)

//读取我们的输入数据

val input = sc.textFile("inputFile")

//把它切分成一个个单词

val words = input.flatMap(line => line.split(" "))

//转换成键值对并计数

val count = words.map(word => (word,1)).reduceByKey{(case x,y) => x+ y}

//将统计出来的单词总数存入一个文本文件，引发求值

count.saveAsTextFile("outputFile")

## Spark编程的核心概念

通过一个驱动器程序创建一个SparkContext和一系列RDD，然后进行并行操作
