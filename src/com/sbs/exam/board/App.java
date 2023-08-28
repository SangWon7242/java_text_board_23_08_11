package com.sbs.exam.board;

import com.sbs.exam.board.container.Container;

import java.util.Scanner;

public class App {

  public void run() {

    Scanner sc = Container.sc;


    System.out.println("== 게시판 v 0.1 ==");
    System.out.println("== 프로그램 시작 ==");

    while (true) {
      System.out.printf("명령) ");
      String cmd = sc.nextLine();

      Rq rq = new Rq(cmd);

      if (rq.getUrlPath().equals("exit")) {
        break;
      } else if (rq.getUrlPath().equals("/usr/article/write")) {
        Container.usrArticleController.actionWrite();
      } else if (rq.getUrlPath().equals("/usr/article/detail")) {
        Container.usrArticleController.showDetail(rq);
      } else if (rq.getUrlPath().equals("/usr/article/modify")) {
        Container.usrArticleController.actionModify(rq);
      } else if (rq.getUrlPath().equals("/usr/article/delete")) {
        Container.usrArticleController.actionDelete(rq);
      } else if (rq.getUrlPath().equals("/usr/article/list")) {
        Container.usrArticleController.showList(rq);
      } else if (rq.getUrlPath().equals("/usr/member/join")) {
        Container.usrMemberController.actionJoin();
      } else if (rq.getUrlPath().equals("/usr/member/login")) {
        Container.usrMemberController.actionLogin();
      } else {
        System.out.println("잘못 된 명령어 입니다.");
      }
    }

    System.out.println("== 프로그램 종료 ==");

    sc.close();
  }
}
