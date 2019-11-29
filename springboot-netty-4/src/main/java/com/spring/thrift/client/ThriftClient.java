package com.spring.thrift.client;

import com.spring.thrift.Person;
import com.spring.thrift.PersonService;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * @author 三多
 * @Time 2019/9/18
 */
public class ThriftClient {
    public static void main(String[] args) {
        //封装IO流
        TTransport transport = new TFramedTransport(new TSocket("localhost", 9999), 600);
        TProtocol protocol = new TCompactProtocol(transport);
        PersonService.Client client = new PersonService.Client(protocol);

        try {
            //打开socket
            transport.open();
            //接收服务端的传出数据
            Person person = client.getPersonByUsername("张三");
            System.out.println(person.getUsername() + "-" + person.getAge() + "-" + person.isMarried());
            //给服务端传输数据
            Person person1 = new Person();
            person1.setUsername("李四").setAge(19).setMarried(true);
            //就如调用本地方法一般。
            client.savePerson(person1);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            transport.close();
        }

    }
}
