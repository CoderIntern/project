package com.daily.dailytest.protostuff;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> Date             :2018/4/12 </p>
 * <p> Module           : </p>
 * <p> Description      : </p>
 * <p> Remark           : </p>
 *
 * @author yangdejun
 * @version 1.0
 * <p>--------------------------------------------------------------</p>
 * <p>修改历史</p>
 * <p>    序号    日期    修改人    修改原因    </p>
 * <p>    1                                     </p>
 */
public class ProtostuffTest {

    public static class Person {

        int id;

        String name;

        public Person() {
        }

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        Person p1 = new Person(124443423, "ajkldfjklajdkfjklajdklfjakljdkfl");
        Person p2 = new Person(124443423, "ajkldfjklajdkfjklajdklfjakljdkfl");
        //单个序列化
        byte[] arr = ProtoStuffSerializerUtil.serialize(p1);
        //单个反序列化
        Person result = ProtoStuffSerializerUtil.deserialize(arr, Person.class);

        //对象集合序列化
        List<Person> personList = new ArrayList<>();
        personList.add(p1);
        personList.add(p2);
        byte[] bytes = ProtoStuffSerializerUtil.serializeList(personList);

        //对象集合反序列化
        List<Person> persons = ProtoStuffSerializerUtil.deserializeList(bytes, Person.class);
    }
}
