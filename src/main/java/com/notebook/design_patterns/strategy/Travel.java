package com.notebook.design_patterns.strategy;

/**
 * @author luorigong
 */
public class Travel {

  public static void main(String[] args) {
    Person person = new Person(new CarStrategy());
    System.out.println(person.choose());

    Person personB = new Person(new TrainStrategy());
    System.out.println(personB.choose());
  }

}
