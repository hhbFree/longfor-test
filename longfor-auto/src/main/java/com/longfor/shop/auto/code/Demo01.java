package com.longfor.shop.auto.code;

import org.apache.tomcat.util.http.fileupload.MultipartStream;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Demo01 {

    public static void main(String[] args) {
        BigDecimal a=new BigDecimal(1);
        BigDecimal b=new BigDecimal(0);
        BigDecimal c=new BigDecimal(-1);
        System.out.println(a.compareTo(BigDecimal.ZERO)<1);
        System.out.println(b.compareTo(BigDecimal.ZERO)<1);
        System.out.println(c.compareTo(BigDecimal.ZERO)<1);
    }

    public static void test(List<Person> personList,String id){
        for (Person person : personList) {
            id=id+","+person.getId();
            System.out.println(id);
            if (person.getPersonList()!=null){

                test(person.getPersonList(),id);
            }
        }
    }


    static class Person{
        private String id;
        private String name;

        private List<Person> personList=new ArrayList<>();


        public Person(String id, String name, List<Person> personList) {
            this.id = id;
            this.name = name;
            this.personList = personList;
        }

        public Person() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Person> getPersonList() {
            return personList;
        }

        public void setPersonList(List<Person> personList) {
            this.personList = personList;
        }
    }
}
