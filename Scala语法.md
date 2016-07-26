import  math._
作用是导入scala.math包下所有类和对象。以scala.开头的包可以省略，类似于java中的.*

val  a = 1
打印：print(a)或者println(a)  
 //这里跟java相同，双引号里面加字符串，print不换行，println打印完追加一个换行符
printf( )具有C风格，格式化输出，%s,%d,%f

1.to(5)会产生Range(1,2,3,4,5)

val声明出来的为常量，不能再次赋值；可以省略类型，scala会自动推导。

var声明出来的为变量，可以再次赋值；可以省略类型，scala会自动推导。


## 控制结构与函数
在java语言中，表达式表示值，语句表示执行动作。 例如：表达式1+1表示值；thread.start()表示执行动作。
所有语法结构都有值，那怕是不存在用Unit类型.Unit类表示“无有用值”，写做()。

if/else语句
有值，为跟在if或else之后的表达式的值。
val s = if(x > 0) 1 else 0   
没有switch语句

语句终止
一行一条语句，行尾不需要分号
一行多条语句，需要使用分号把它们隔开
较长语句分两行时，在第一行尾部不要用语句结束的符号结束，通常使用一个操作符。如：
　　val s = "sssssssssssssssssssssssssss" + "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" + //告诉解析器这里不是语句的结尾
　　　　"ccc" + "dd"


## 块表达式和赋值
{}块包含一系列表达式，其结果是块中最后一个表达式的值。

例：val s = {1+2; 2+3} //s的值为5

注意：赋值语句的返回值是Unit类型，写作()

readLine("tips")：从控制台读取一行，"tips"为输出提示

## 循环

while循环

while (n > 0){

　　// TODO

}

for循环

for (i <- 1 to n) {         //这里包括1和n

　　// TODO

}

for的参数表达式说明：让变量i遍历<-右边表达式的所有值。
通常在遍历时使用util，until返回一个不包括上限的区间。例：0 until 2，表示Range(0, 1)，类似于python
注：scala没有类似java的for(;;)型for语句

### for高级特性：
循环体带yield，即for推导：

　　val z = for (i <- 0 until 3) yield i;    //yield带过滤功能

　　z的值为Vector(0, 1, 2)

多个生成器、守卫、变量定义：

　　for (i <- 0 until 5; j <- 3 until ;) { println(i, j)}

//多个生成器

　　for (i <- 0 until 5; from = 4 - i; j <- from until 5) {}

//变量定义

　　for (i <- 0 until 5; from = 4 - i; j <- from until 5 if i !=
  j) {}

 //每个生成器可以带一个守卫，注意if前没有分号没有break和continue

需要时怎么办？

1、使用变量控制

2、外层嵌套函数，使用return进行break;

3、使用异常机制，抛出异常与捕获异常控制。

scala提供的：

import scala.util.control.Breaks.break

import scala.util.control.Breaks.breakable

breakable {

   for (i <- 0 until 2) {

   println(i)

   if (i == 1) break;

   }
}

## 函数
定义函数只需要给出函数名、参数和函数体

def 函数名[(参数名:参数类型，…)][: 返回类型]= {   

   //这里参数名：参数类型与Java相反，有点跟python像

  // function body
}

注：[]表示可选，只要函数不是递归的就不需要指定函数的返回类型；scala会自动推断函数的返回类型。

函数体最后一个表达式的值就是函数的返回值，当然也可以用return显式返回。

如果函数没有参数，括号可以省略，但调用时也不能带括号。
默认参数和带名参数

def test(str: String, left: String = "[", right: String = "]") =
left + str + right     //注意这里是类型在前面，形参在后面

test("hello") // 返回[hello]

test("hello", right = ">>")

test("hello", left = "<", right = ">")

test("hello", right = ">")

变长参数：

def sum(args: Int*) = {

 var result: Int = 0

 for (arg <- args) result += arg

 result

}

可以使用合意多的参数来调用该函数

sum(1, 2, 3)

如果有了一个值的序列，不能直接调用，得转化

val s = sum(1 to 10: *) // *将1 to 10当作参数序列处理。

val s = sum(1 to 10) // 会报错

此语法的用处是递归

def recursiveSum(args: Int*): Int = {

 if (args.length == 0) 0

 else args.head + recursiveSum(args.tail: _*)
}

args.head表示args的首个元素，而tail表示所有其它的元素。
过程：

没有返回值的函数，函数体前不带“=”。
lazy:

lazy val words = Source.fromFile("readme2.txt").mkString

函数第一次调用时加载

也有额外开销，每次访问懒值，都会调用一个方法以线程安全的方式检查值是否已经初始化

异常

方法不用带类似throw IOException的签名

try{

} catch {

case : IOException => println("xx") //

不需要使用异常对象，可以使用来代替异常名。

　　case e: Exception => e.printStatckTrace()   

//这里类似于对象的声明

} finally {

}


scala中的match..case语句

Scala通过case语句提供了形式简单、功能强大的模式匹配功能。

scala中的match语句用来在一个列表中选择某一个分支来执行分支的语句块，类
似于其他语言中的swtich..case语句。

简单的例子：

var myVar = "theValue";

myVar match {

   case "someValue"   => println(myVar + " 1");

   case "thisValue"   => println(myVar + " 2");

   case "theValue"    => println(myVar + " 3");

   case "doubleValue" => println(myVar + " 4");
}

和java的switch语句的不同在于：它不需要break语句，每个case语句之后到下

一个语句结束之前就是该case的代码块。它不需要break但是每个case都有一个隐

含的break存在，不会贯穿到下一个case语句里面去执行

另外一个和java

switch语句不同在于，scala的match语句可以比较n多种类型，不局限于基础类型。

另外match语句不但可以执行一个语句块，还可以返回一个值，如下例子

var myVar = "theValue";

var myResult =

    myVar match {

       case "someValue"   => myVar + " A";

       case "thisValue"   => myVar + " B";

       case "theValue"    => myVar + " C";

       case "doubleValue" => myVar + " D";
    }

println(myResult);

注意：myResult的值并非等于myVar的值，而是等于case语句返回的值

注意，当一个类被生成为case class 时，scala会默认作如下工作：

（1）如果参数不加var/val修改，默认为val。

（2）自动创建伴生对象，实现apply方法，方便了我们在创建对象时不适用new

（3）实现自己的toString、hashCode、copy和equals方法


### array
定长数组：

val nums = new Array[Int](10)

变长数组：

val b = new ArrayBuffer[Int] 或 ArrayBuffer[Int]()

b  +=1

b += (2, 3, 4)

b  ++= Array(5, 6, 7, 8)

b trimEnd(3)

b.insert(2, 3)

b.insert(2, 3, 4, 5)

b.remove(2)

b.remove(2, 4)

b.toArray

遍历

for (i <- 0 until b.length)

　　println(b(i))                          

//注意这里是b(i)，小括号而不是中括号

for (i <- (0 until b.lenght).reverse)

　　println(b(i))

for (element <- b)

　　println(element)

数组转换：

val a = Array(1, 2, 3, 4)

val b = for (e <- a) yield e * 2

val b = a.map(_ * 2)

val c = for (e <- a if e % 2 == 0) yield 2*e

val c = a.filter(%2==0).map(2*)或者val c = a.filter(x => x%2 == 0).map(y => y *2)

### 常用方法：（后面不需要加括号，看成属性）

sum：必须是数值型类型

min

max

sorted：

   scala.util.Sorting.quickSort(a)

mkString

toString


## Map和Tuple
Map

构造Map

### 不可变：

val map = Map("sa" -> 1, "s" -> 2) map("sa") = 3 // error

val emptyMap = new scala.collection.immutable.HashMap[String,
Int]

### 可变：

val map2 = scala.collection.mutable.Map("sa" -> 2) map2("sa") = 3

val emptyMap = new scala.collection.mutable.HashMap[String, Int]

注：->用来创建元组， "sa" -> 1即("sa", 1)

初始化完全可以 val map = Map(("sa", 1), ("s", 2))

获取Map中的值：

如果map中不包含请求中使用的key值，则抛异常。NoSuchElementException
　　map("sa") // 类似于java中的map.get("sa")

要检查map中是否包含某个key，使用contains方法。
　　val sa = if (map2.contains("sa3")) map2("sa3") else 0;

快捷的方式：

val sa2 = map.getOrElse("sa2", 0)

一次得到是否包含key，并获取值：

　　val sa3 = map.get("sa3"); // Option类型， 　　

println(sa3.isEmpty)

更新Map中的值：

添加或更新：

　　map("sa") = 3

添加或更新多个：

　　map += ("aa" -> 4, "bb" -> 5)

移除某个key和对应的值：

　　map -= "aa"

不可变的map也可以使用+和-操作，但是会生成新的map

　　var map = Map("aa" -> 1)     //注意这里是("aa" ->

1)而不是("aa",1)

　　map = map + ("bb" -> 2)

　　map += ("cc" -> 2)

　　map -= "aa"           //这里只需要减key值


### 迭代map：
for ((k, v) <- map) {

  // TODO

}

所有key：
　　map.keySet

所有值：
　　map.values

反转：
　　map2 = for((k, v) <- map) yield (v, k)

已排序Map：


按key排序：
　　SortedMap

按添加顺序：
　　LinkedHashMap

###  元组Tuple：

`不同类型值的集合`

//注意这是不同类型值的集合，下表从1开始，与数组刚好相反

　　val tp = (1, "ss", 2.0)

访问数值：

　tp._1

　tp._2

　tp._3

　　下标从1开始

通过模式匹配获取元组：

　　val (first, second, third) = set

可以用于函数返回多个值的时候

#### 拉链操作

val arrkey = Array(1, 3, 5)

val arrValue = Array("a", "b", "c")

val tupleArr = arrkey.zip(arrValue) // tupleArr为Array((1,a), (3,b), (5,c))

val map = tupleArr.toMap


immutable（不可变的）

引入包中多个成员
　　import java.awt.{Color, Font}

引入类并重命名
　　import java.util.{HashMap => JavaHashMap} 　　import scala.collection.mutable._

隐藏某个引入的类
　　import java.util.{HashMap => , } // 引入java.util下的所有成员，但HashMap不引入


class

简单类和无参方法：

1 class Counter {

2   private var value = 0; //必须初始化字段

3   def increment() = value += 1 //方法默认是公有的

4   def current = value

5 }

　　使用：

1     val counter1 = new Counter // 或 new Counter()

2     // 类定义方法是带了()，调用时可带，也可不带

3     counter1.increment

4     counter1.increment()

5     println(counter1.current)

6     println(counter1.current()) // error：

　　类定义方法时没带()，调用时也不能带
　　推荐的风格是：改值的方法带()；取值的方法不带()，定义时不带便可强制取值风格。

Setter和Getter

1 class Person {

2   var age = 0;

3 }

　　对于属性age隐式的生成了age()和age_=(age: Int)方法。

　如果属性带private，则生成的方法也带private；如果属性不带private，则生成的方法为public。如果属性为val，则只生成get方法。

如果禁用生成隐式的方法，使用private[this]声明。这样只能访问实例自身的属性，不能访问其它实例的属性，即对象私有成员。

注：不能实现只有Setter，没有Getter的属性。想实现，只能用其它名称了。
　　显式定义age()和age_=(age: Int)方法：

1 class Person {

2   private var _age = 0; // 变成私有并改名

3

4   def age = this._age;

5   def age_=(_age: Int) {

6     this._age = _age;

7   }

8 }

　　生成的java代码实际上生成了四个方法：显式定义的两个，改名后变量隐式生成的（private）
　　为什么要改名？不改名无法区分方法名和属性名，而且出现方法定义两次错误

主构造方法：

　　和类定义交织在一起：

  参数被编译成字体；

  主构造方法会执行类定义中的所有语句；

  构造方法中参数不带val也不带var，只有被一个以上方法调用时才会提升为字段

  。类似于private[this] val效果

1 class Person(@BeanProperty var age: Int) {
2 }

会生成一个带age:Int参数的构造函数；


  age()和age_=(age:Int)方法

  getAge()和setAge(age:Int)方法

一个类如果没有显式定义主构造方法，会隐式生成一个无参不做任何操作的主构造方法。

辅助构造方法：

辅助构造方法的名称为this;

辅助构造方法每一行必须是调用构造方法（主构造方法或其它辅助构造方法）

def this(age:Int) {

　　this()

   _age = age
}

### 嵌套类：

内部类与外部类实例相关联

1 class Network {

 2   class Member(val name: String) {

 3     val contacts = new ArrayBuffer[Member]

 4   }

 5   private val members = new ArrayBuffer[Member]

 6   def join(name: String) = {

 7     val m = new Member(name)

 8     members += m

 9     m

10   }

11 }

使用：

1     val chatter = new Network

2     val myFace = new Network

3     val fred = chatter.join("fred")

4     val wilma = chatter.join("wilma")

5     fred.contacts += wilma

6     val ngy = myFace.join("hongdao");

7     fred.contacts += ngy; // error

解析：

val
fred和wilma的类型为chatter.Member；ngy的类型为myFace.Memeber；

fred.contacts的实际类型为ArrayBuffer[chatter.Member]； 　　

所以fred.contacts可以添加wilma，不可以添加ngy。


内部类访问外部实例：

    外部类.this

    外部类实例别名

1 class Network(val name: String) { outter => //外部类实例别名

2   class Member(val name: String) {

3     val contacts = new ArrayBuffer[Network#Member]

4     def out = println(Network.this.name)

5     def out2 = println(outter.name)

6   }

7 }


Unit  表示无值，和其他语言中void等同

Scala的Unit结果类型指的是函数没有返回有用的值。

Scala的Unit类型比较接近Java的void类型，而且实际上Java里每一个返回void的方法都被映射为Scala里返回Unit的方法。因此结果类型为Unit的方法，仅仅是为了它们的副作用而运行

scala> 1 + 1

res0: Int = 2

res0是解释器自动创建的变量名称，用来指代表达式的计算结果。它是Int类型，值为2。
Scala中（几乎）一切都是表达式。


## 继承
继承类

scala继承类和java一样用关键字extends，子类可以定义超类没有的字段和方法，或重写超类的方法。

声明类为final它就不能被继承，还可以将方法或字段声明为final，以确保它们不会被重写。 和Java不同，Java中final声明常量，类似Scala中的val。
重写方法

Scala中重写非抽象方法必须使用override修饰符；

Scala中调用超类的方法和Java一样用super；

类型检查和转换

p是String类或String子类（貌似没子类）的实例

if (p.isInstanceOf[String])

{ 　　val s = p.asInstanceOf[String] }

如果p是null，返回false

如果p不是String，p.asInstanceOf[String]将抛异常。

p是String类的实例

if (p.getClass() == classOf[String])

 { 　　val s = p.asInstanceOf[String] }

### 模式匹配：

p match

{ 　　case s: String => // String do 　　case _ => //not String do }

受保护的字段和方法

可以将字段或方法声明为protected，这样成员可以被任意子类看到，但不能从其它位置看到。

访问对象仅限当前对象protected[this], 类似于private[this]
超类的构造

只有主构造方法才能调用超类的构造方法，辅构造方法永远不可能调用超类的构造方法；

调用超类的构造方法也和类定义交织在一起：

class Employee(val name: String, age: Int) extends Person(age) {

}

重写字段
匿名子类：

def meet(p: Person { def greeting: String }) = {

}

val alien = new Person(2) {

def greeting = "Greetings!"

}

meet(alien)

抽象类：

和Java一样：

　　使用abstract标记类为抽象类；

　　不能实例化；

和Java不一样：

　　抽象方法没有定义方法体，不需要abstract标记；

子类重写超类的抽象方法时，不需要使用override关键字
抽象字段：

指没有初始值的字段

子类重写超类的字段时，不需要使用override关键字

构造顺序和提前定义：

class Creature {
  val range = 10
  val env = new Array[Int](range)
}
class Ant extends Creature {
  override val range = 2
}

object TestAnt extends App {
  val ant = new Ant()
  println(ant.env.length) // 结果为0
  println(ant.range) // 结果为2
}

因为先构造Creature；构造env时调用range()，而Ant没有实例化返回0；然后构造Ant。

解决方法：

提前定义

class Ant extends {
override val range = 2
} with Creature {
}
