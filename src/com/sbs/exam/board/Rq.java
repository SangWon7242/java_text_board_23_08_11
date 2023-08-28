package com.sbs.exam.board;

import com.sbs.exam.board.container.Container;
import com.sbs.exam.board.session.Session;

import java.util.Map;

public class Rq {
  private String url;
  private Map<String, String> params;
  private String urlPath;
  private Session session;

  Rq(String url) {
    this.url = url;
    urlPath = Util.getUrlPathFromUrl(this.url);
    params = Util.getParamsFromUrl(this.url);
    session = Container.getSession();
  }

  public Map<String, String> getParams() {
    return params;
  }

  public String getUrlPath() {
    return urlPath;
  }

  public int getIntParam(String paramName, int defaultValue) {
    if(params.containsKey(paramName) == false) {
      return defaultValue;
    }

    try {
      return Integer.parseInt(params.get(paramName));
    } catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  public String getParam(String paramName, String defaultValue) {
    if(params.containsKey(paramName) == false) {
      return defaultValue;
    }

    return params.get(paramName);
  }

  public void setSessionAttr(String key, Object value) {
    session.setAttribute(key, value);
  }

  public void removeSessionAttr(String key) {
    session.removeAttribute(key);
  }

  public boolean hasSessionAttr(String key) {
    return session.hasAttribute(key);
  }
}
