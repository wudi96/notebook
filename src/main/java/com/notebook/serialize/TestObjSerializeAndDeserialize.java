package com.notebook.serialize;

import java.io.*;
import java.text.MessageFormat;

/**
 * 序列化
 * @author wudi
 */
public class TestObjSerializeAndDeserialize {

    public static void main(String[] args) throws Exception {
        byte[] bytes = serializePerson();

        Person p = deserializePerson(bytes);

        System.out.println(
                MessageFormat.format("name={0},age={1},sex={2}", p.getName(), p.getAge(), p.getSex()));
    }

    private static Person deserializePerson(byte[] bytes) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
        return (Person) ois.readObject();
    }

    private static byte[] serializePerson() throws IOException {
        Person person = new Person();
        person.setName("ga");
        person.setAge(25);
        person.setSex("男");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(outputStream);
        oo.writeObject(person);
        byte[] bytes = outputStream.toByteArray();
        oo.close();
        return bytes;
    }
}
