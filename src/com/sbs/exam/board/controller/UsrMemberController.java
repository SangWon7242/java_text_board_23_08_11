package com.sbs.exam.board.controller;

import com.sbs.exam.board.Member;
import com.sbs.exam.board.container.Container;

import java.util.ArrayList;
import java.util.List;

public class UsrMemberController {
  private int membersLastId;
  private List<Member> members;

  public UsrMemberController() {
    membersLastId = 0;
    members = new ArrayList<>();

    makeTestData();

    if (members.size() > 0) {
      membersLastId = members.get(members.size() - 1).id;
    }
  }

  public void makeTestData() {
    for (int i = 1; i <= 3; i++) {
      members.add(new Member(i, "user" + i, "1234", "회원" + i));
    }
  }

  public void actionJoin() {
    System.out.println("== 회원가입 ==");
    System.out.printf("회원 아이디 : ");
    String loginId = Container.sc.nextLine();

    if (loginId.length() == 0) {
      System.out.println("로그인 아이디를 입력해주세요.");
      return;
    }

    System.out.printf("비밀번호 입력 : ");
    String loginPw = Container.sc.nextLine();

    if (loginPw.length() == 0) {
      System.out.println("로그인 비밀번호를 입력해주세요.");
      return;
    }
    
    System.out.printf("비밀번호 확인 : ");
    String loginPwConfirm = Container.sc.nextLine();

    if (loginPwConfirm.length() == 0) {
      System.out.println("로그인 비밀번호 확인을 입력해주세요.");
      return;
    }

    if(loginPw.equals(loginPwConfirm) == false) {
      System.out.println("=== 안내 ===");
      System.out.println("비밀번호가 일치하지 않습니다.");
      System.out.println("비빌번호 확인을 다시 입력해주세요.");
      return;
    }

    System.out.printf("이름 : ");
    String name = Container.sc.nextLine();

    if (name.length() == 0) {
      System.out.println("이름을 입력해주세요.");
      return;
    }

    int id = ++membersLastId;

    Member member = new Member(id, loginId, loginPw, name);

    members.add(member);

    System.out.printf("\"%s\" 님 회원가입 되었습니다.\n", member.name);
  }
}
