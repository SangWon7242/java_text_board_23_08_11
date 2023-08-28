package com.sbs.exam.board.session;

import java.util.HashMap;
import java.util.Map;

public class Session {
  private Map<String, Object> sessionStore;

  public Session() {
    sessionStore = new HashMap<>();
  }

  public Object getAttribute(String key) {
    return sessionStore.get(key);
  }

  public void setAttribute(String key, Object value) {
    sessionStore.put(key, value);
  }

  public void removeAttribute(String key) {
    sessionStore.remove(key);
  }

  public boolean hasAttribute(String key) {
    return sessionStore.containsKey(key);
  }
}
