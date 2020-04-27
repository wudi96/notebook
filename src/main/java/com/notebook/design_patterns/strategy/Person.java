package com.notebook.design_patterns.strategy;

/**
 * @author luorigong
 */
class Person {

  private Strategy strategy;

  Person(Strategy strategy) {
    this.strategy = strategy;
  }

  String choose() {
    return strategy.strategy();
  }
}
