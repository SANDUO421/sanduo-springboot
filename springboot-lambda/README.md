# stream 参考
1. [IBM的参考](https://www.ibm.com/developerworks/cn/java/j-lo-java8streamapi/)

```
总之，Stream 的特性可以归纳为：

不是数据结构
它没有内部存储，它只是用操作管道从 source（数据结构、数组、generator function、IO channel）抓取数据。
它也绝不修改自己所封装的底层数据结构的数据。例如 Stream 的 filter 操作会产生一个不包含被过滤元素的新 Stream，而不是从 source 删除那些元素。
所有 Stream 的操作必须以 lambda 表达式为参数
不支持索引访问
你可以请求第一个元素，但无法请求第二个，第三个，或最后一个。不过请参阅下一项。
很容易生成数组或者 List
惰性化
很多 Stream 操作是向后延迟的，一直到它弄清楚了最后需要多少数据才会开始。
Intermediate 操作永远是惰性化的。
并行能力
当一个 Stream 是并行化的，就不需要再写多线程代码，所有对它的操作会自动并行进行的。
可以是无限的
集合有固定大小，Stream 则不必。limit(n) 和 findFirst() 这类的 short-circuiting 操作可以对无限的 Stream 进行运算并很快完成。
```
### 参考

1. [Oracle Java 8 官方文档](java.util.stream package) 。
2. 一篇教程：Java 8 Tutorials, Resources, Books and Examples to learn Lambdas, Stream API and Functional Interfaces。
3. 关于这篇 Lambda 和 Stream 更多介绍的教程。
4. 访问 developerWorks Java 专区，了解关于信息管理的更多信息，获取技术文档、how-to 文章、培训、下载、产品信息以及其他资源。


