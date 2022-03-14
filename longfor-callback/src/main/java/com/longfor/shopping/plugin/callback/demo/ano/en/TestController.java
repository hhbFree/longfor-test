package com.longfor.shopping.plugin.callback.demo.ano.en;

import org.springframework.stereotype.Component;

@Component
public class TestController {
  @WorkListener
  public void listen(String name) {
    System.out.println("Start work for " + name + " !");
  }
}