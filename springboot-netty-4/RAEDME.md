# netty 4 基础到提升

##步骤
  1. 创建服务器
  2. 自定义initializer
  3. 自定义handler

## 需求

### 1、聊天程序

* 建立连接
* 消息广播
### 2、心跳机制（定时）
* 集群之间的长链接
* 节点之间 最终最终一致性。
* 节点间通信：
* 例如  手机 飞行模式或者强制关机 是不会调用此方法
     *  针对这种情况必须有心跳机制
### 3、RPC
### 4、请求http


# webSocket （伴随着h5提出）
## http协议
* 无状态的（cookie和Session解决这些问题）
* http1.0 只能客户端发起请求
* http1.1 keeplive  客户端和服务器端可以保持一点时间的连接（建立短暂链接）

问题：<hr>
    1. http 无法基于浏览器聊天<br/>
    2. 服务器端的推技术（http协议禁止推技术）（解决方案：轮训、commit（无法实时消息推送））<br/>
    * 轮训，大部分的请求没有结果，浪费资源 <br/>
    * 头信息占带宽
## websocket（html5 技术）
1. 长链接
2. 节省网络带宽（只发送有用的信息）
3. 构建在http之上的协议（websocket基于http 升级）
4. 需要服务端对websocket支持
5. 缺少心跳机制，就是无法感知传输连接
    
## 需求

###1、index.html 实现websocket 

## RPC 

### 简介 RMI（Remote method invocation，远程方法调用）---只针对java

client：（反序列化 字节码）---》调用服务端方法（字节码）  stub（桩）<br/>
server：（反序列化 字节码）---》字节码返回客户端   skeleton（骨架）<br/>

序列化和反徐序列化 ：编码和解码 <br/>

### RPC (Remote Procedure Call:远程过程调用) --- 跨语言的---基于Socket

1. 定义接口说明文件：描述了对象（结构体）、对象成员、接口方法等一系列信息
2. 通过RPC框架所提供的编译器，将接口说明文件编译成具体语言文件
3. 在客户端和服务端分别引入RPC 编译所生成的文件，即可以向调用本地方法一样调用远端方法。

应用场景：<br/>
    内网服务间调用建议使用RPC 调用，调高效率。
优势：<br/>
    高效的远程调用
    跨平台，跨语言调用

### WebService  ---基于hTTP 效率低一点


## protobuf (序列化框架，平台中立，语言中立)

### 安装 配置
1、下载安装配置环境（https://github.com/protocolbuffers/protobuf）
2、官网 https://developers.google.cn/protocol-buffers/ 

### 生成类（不要修改）
```shell
--坚决不要修改生成的文件
$ protoc --java_out=src/main/java src/main/resources/protobuf/Student.proto
```
### 注意
1、handler的名称定义不能重名
### 当传递的消息类型有多重时，如何区分消息类型（ pipeline.addLast("protobufDecoder",new ProtobufDecoder(MyDataInfo.Person.getDefaultInstance()));）
1、自定义消息类型（自定义解码器）--较为复杂，主要是netty的自定义协议
2、嵌套在最外层消息类型，通过枚举判断

## Thrift （RPC 间调用）

### 简介
官网 http://thrift.apache.org/

使用Socket 通信

### 生成类（客户端发启请求-TCP -服务端返回结果-TCP-客户端接收结果）

```shell
-- 生成类 data.thrift(接口描述语言)
$ thrift --gen java src/main/resources/thrift/data.thrift
```
### 实例 service and Client (选型，并发量非常大)

使用场景：<br/>
    * 大数据
    * 异构语言的平台调用
TCompactProtocol（压缩协议） ->TFramedTransport（以帧传输方式，非阻塞）->THsHaServer（半同步半异步的服务模型）
### 原理介绍
#### 架构图
![thrift-架构图](./images/thrift-架构图.jpg)

#### 网络七层协议
![7层网络协议](./images/7层网络协议.jpg)

#### Thrift 传输协议
![thrift-协议](images/thrift-协议.jpg)

#### Thrift 传输方式
![thrift-传输方式](./images/thrift-传输方式.jpg)

#### Thrift 服务模型
![thrift-服务模型](./images/thrift-服务模型.jpg)

### 实战 python  客户端 ，java 服务端 （官网：https://www.python.org/）
1. 生成 python 代码
```sbtshell
$ thrift --gen py src/main/resources/thrift/data-py.thrift
```
//TODO 18  28秒







