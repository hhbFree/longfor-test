package com.longfor.shopping.plugin.callback.demo.ano;

import org.springframework.stereotype.Service;


@Service
public class MyService {

  private WorkListener listener;

  public void setWorkListener(WorkListener workListener) {
    this.listener = workListener;
  }

  public void work(String name) {
    listener.onStart(name);
  }

}
