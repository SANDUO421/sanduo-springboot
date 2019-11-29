package com.spring.thrift.server;

import com.spring.thrift.PersonService;
import com.spring.thrift.service.PersonServiceImpl;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;

/**
 * 服务端
 * @author 三多
 * @Time 2019/9/18
 */
public class ThriftServer {
    public static void main(String[] args) throws Exception{
        //ServerSocketChannel 的包装类
        TNonblockingServerSocket socket = new TNonblockingServerSocket(9999);
        //半同步，半阻塞的server 依赖TFramedTransport
        THsHaServer.Args arg = new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);
        PersonService.Processor<PersonServiceImpl> processor =
                new PersonService.Processor<>(new PersonServiceImpl());
        //客户端的协议和传输一定要相互一致
        // 协议 （应用层）
        arg.protocolFactory(new  TCompactProtocol.Factory());
        //传输 （传输层）
        arg.transportFactory(new TFramedTransport.Factory());
        // 网络IO 发送数据
        arg.processorFactory(new TProcessorFactory(processor));
        //半同步半异步服务器
        TServer server = new THsHaServer(arg);
        System.out.println("Thrift Server started !");
        //启动服务器
        server.serve();

    }
}
