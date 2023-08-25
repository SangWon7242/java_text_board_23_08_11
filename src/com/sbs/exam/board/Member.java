package com.sbs.exam.board;

public class Member {
  public int id;
  public String loginId;
  public String loginPw;
  public String name;

  public Member(int id, String loginId, String loginPw, String name) {
    this.id = id;
    this.loginId = loginId;
    this.loginPw = loginPw;
    this.name = name;
  }

  @Override
  public String toString() {
    return "Member{" +
        "id=" + id +
        ", loginId='" + loginId + '\'' +
        ", loginPw='" + loginPw + '\'' +
        ", name='" + name + '\'' +
        '}';
  }
}
