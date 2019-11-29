package com.spring.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * 测试 protobuf
 * @author 三多
 * @Time 2019/9/17
 */
public class ProtobufTest {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        //编码字节对象-输入
        DataInfo.Student stu = DataInfo.Student.newBuilder().setName("sanduo")
                .setAge(18)
                .setAddress("西安").build();
        byte[] stu2ByteArray = stu.toByteArray();
        //解析字节对象-输出 （可以跨平台，跨语言反序列化）
        DataInfo.Student student = DataInfo.Student.parseFrom(stu2ByteArray);
        System.out.println(student);
        System.out.println(student.getName());
        System.out.println(student.getAge());
        System.out.println(student.getAddress());

    }
}
