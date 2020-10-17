# 源代码

```java
package com.masonluo.jike.homework.ex1;

/**
 * @author Masonluo
 * @date 2020-10-17 09:47
 */
public class Hello {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                sum += 2;
            } else {
                sum++;
            }
        }
        System.out.println(sum);
    }
}
```

# 字节码

```
Classfile /Users/user/Desktop/Java进阶课程/homework-code/JAVA-000/Week_01/src/main/java/com/masonluo/jike/homework/ex1/Hello.class
  Last modified 2020-10-17; size 537 bytes
  MD5 checksum 873eefd6d0d51c3a2285fe26ef4d5fca
  Compiled from "Hello.java"
public class com.masonluo.jike.homework.ex1.Hello
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #5.#17         // java/lang/Object."<init>":()V
   #2 = Fieldref           #18.#19        // java/lang/System.out:Ljava/io/PrintStream;
   #3 = Methodref          #20.#21        // java/io/PrintStream.println:(I)V
   #4 = Class              #22            // com/masonluo/jike/homework/ex1/Hello
   #5 = Class              #23            // java/lang/Object
   #6 = Utf8               <init>
   #7 = Utf8               ()V
   #8 = Utf8               Code
   #9 = Utf8               LineNumberTable
  #10 = Utf8               main
  #11 = Utf8               ([Ljava/lang/String;)V
  #12 = Utf8               StackMapTable
  #13 = Utf8               MethodParameters
  #14 = Utf8               args
  #15 = Utf8               SourceFile
  #16 = Utf8               Hello.java
  #17 = NameAndType        #6:#7          // "<init>":()V
  #18 = Class              #24            // java/lang/System
  #19 = NameAndType        #25:#26        // out:Ljava/io/PrintStream;
  #20 = Class              #27            // java/io/PrintStream
  #21 = NameAndType        #28:#29        // println:(I)V
  #22 = Utf8               com/masonluo/jike/homework/ex1/Hello
  #23 = Utf8               java/lang/Object
  #24 = Utf8               java/lang/System
  #25 = Utf8               out
  #26 = Utf8               Ljava/io/PrintStream;
  #27 = Utf8               java/io/PrintStream
  #28 = Utf8               println
  #29 = Utf8               (I)V
{
  public com.masonluo.jike.homework.ex1.Hello();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 7: 0

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=3, args_size=1
         0: iconst_0
         1: istore_1
         2: iconst_0
         3: istore_2
         4: iload_2
         5: bipush        10
         7: if_icmpge     31
        10: iload_2
        11: iconst_2
        12: irem
        13: ifne          22
        16: iinc          1, 2
        19: goto          25
        22: iinc          1, 1
        25: iinc          2, 1
        28: goto          4
        31: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
        34: iload_1
        35: invokevirtual #3                  // Method java/io/PrintStream.println:(I)V
        38: return
      LineNumberTable:
        line 9: 0
        line 10: 2
        line 11: 10
        line 12: 16
        line 14: 22
        line 10: 25
        line 17: 31
        line 18: 38
      StackMapTable: number_of_entries = 4
        frame_type = 253 /* append */
          offset_delta = 4
          locals = [ int, int ]
        frame_type = 17 /* same */
        frame_type = 2 /* same */
        frame_type = 250 /* chop */
          offset_delta = 5
    MethodParameters:
      Name                           Flags
      args
}
SourceFile: "Hello.java"

```

# 字节码分析

首先了解出现的字节码指令， 如下：

- iconst_x： 将int型x推送到栈顶
- istore_x： 将栈顶int型数值存放入x号局部变量表中
- iload_x：  将指定槽位x中的整形变量推送到栈顶
- bipush：  将单字节的常量值（-128 ～ 127）推送到栈顶，这里指的是10，也就是循环结束的终点
- if_icmpge：比较栈顶两int型数值的大小，当结果大于等于0的时候跳
- irem: 将栈顶两int型数值进行取模运算，并且将结果压入栈顶
- ifne：当栈顶int型数值不等于0时跳转
- iinc： 将指定int型变量增加指定值
- goto：跳转到指定位置继续执行
- getStatic：获取指定类的静态域，并将其值压入栈顶
- invokevirtual： 执行实例方法

主要字节码如上，那么可以解释这段字节码是如何运作的了，因为这句话，我们可以知道：

![image-20201017104018857](https://tva1.sinaimg.cn/large/007S8ZIlgy1gjs4s9tu5aj30da0103yh.jpg)

栈的**深度为2**，局部变量表的**槽位为3**，main函数拥有一个参数，那么表明**局部变量表的0号槽位应该是存放着String[]的引用**。

代码一开始，这四个字节码：

```
 0: iconst_0
 1: istore_1
 2: iconst_0
 3: istore_2
```

分别把两个int型0存入到局部变量表，那么此时局部变量表如下：

![image-20201017105533179](https://tva1.sinaimg.cn/large/007S8ZIlgy1gjs584hd0bj30pi07q3yr.jpg)

栈现在为空，如下：

![image-20201017105636484](https://tva1.sinaimg.cn/large/007S8ZIlgy1gjs5988bn7j306y09kjrc.jpg)

当执行这两个字节码的时候：

```
 4: iload_2
 5: bipush        10
```

iload_2，将局部变量表的2号槽位的值推送到栈顶，bipush将一个8位数推送到栈顶，这里这个数为10，那么这里就很明显表达了两件事：

- 2号槽位的值为一个计数器，也就是代码中的i；
- bipush将10推至栈顶，那么很明显，10就是循环结束条件。

此时，栈的内容如下：

![image-20201017110000185](https://tva1.sinaimg.cn/large/007S8ZIlgy1gjs5cr389cj308c08sglm.jpg)

这个时候，执行if_icmpge指令，这个指令将栈顶的两个数进行比较，如果值大于等于0，则跳转到指定行执行(**图上31表明如果条件达成，那么跳转到第31行执行**)，这个时候**会执行（0 - 10），最后结果小于0，那么不进行跳转**：

![image-20201017110134750](https://tva1.sinaimg.cn/large/007S8ZIlgy1gjs5ee78o2j305900ia9u.jpg)

此时执行了比较，两个数出栈，现在栈元素为：

![image-20201017110239039](https://tva1.sinaimg.cn/large/007S8ZIlgy1gjs5fieymjj307a07qwef.jpg)

上面一步不进行跳转，那么将会执行如下两条语句，也就是：

```
        10: iload_2
        11: iconst_2
```

现在栈结构如下：

![image-20201017110353690](https://tva1.sinaimg.cn/large/007S8ZIlgy1gjs5gt6araj306q07w0sp.jpg)

接下来执行irem指令，**将栈顶两个数进行取模，也就是 0 % 2运算**，并且将结果压入栈顶，这里注意，就是执行代码中的 **i % 2**，执行指令后，栈结构如下：

![image-20201017110549616](https://tva1.sinaimg.cn/large/007S8ZIlgy1gjs5itagzlj307c08e0sp.jpg)

然后执行**ifne**指令，也就是栈顶元素不等于0的时候，进行跳转，跳转到22行

![image-20201017110652605](https://tva1.sinaimg.cn/large/007S8ZIlgy1gjs5jwpiukj305h02g745.jpg)

现在栈顶元素等于0，那么不进行跳转，也就是执行下面的iinc指令，这里是将1号变量槽的值加2，那么现在局部变量表的值为：

![image-20201017111021473](https://tva1.sinaimg.cn/large/007S8ZIlgy1gjs5nivxr6j30l40fcmxm.jpg)

这里其实也就是表明，1号变量槽存放的是sum的值。

然后一个goto语句，跳转到25行执行iinc 2，1，将计数器的值加一，然后再执行goto，跳转到第4行进行执行，这里就是表达了一个循环，当计数器值大于等于10才退出循环。