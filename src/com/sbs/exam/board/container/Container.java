package com.sbs.exam.board.container;

import com.sbs.exam.board.controller.UsrArticleController;
import com.sbs.exam.board.controller.UsrMemberController;
import com.sbs.exam.board.session.Session;

import java.util.Scanner;

public class Container {
  public static Scanner sc;
  public static Session session;

  public static UsrArticleController usrArticleController;
  public static UsrMemberController usrMemberController;

  static {
    sc = new Scanner(System.in);
    session = new Session();

    usrArticleController = new UsrArticleController();
    usrMemberController = new UsrMemberController();
  }

  public static Session getSession() {
    return session;
  }
}
