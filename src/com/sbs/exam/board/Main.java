package com.sbs.exam.board;

import com.sbs.exam.board.container.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
  static int articlesLastId;
  static List<Article> articles;

  static {
    articlesLastId = 0;
    articles = new ArrayList<>();
  }

  static void makeTestData() {
    for (int i = 1; i <= 100; i++) {
      articles.add(new Article(i, "제목" + i, "내용" + i));
    }
  }

  public static void main(String[] args) {

    Scanner sc = Container.sc;

    makeTestData();

    if (articles.size() > 0) {
      articlesLastId = articles.get(articles.size() - 1).id;
    }

    System.out.println("== 게시판 v 0.1 ==");
    System.out.println("== 프로그램 시작 ==");

    while (true) {
      System.out.printf("명령) ");
      String cmd = sc.nextLine();

      Rq rq = new Rq(cmd);

      if (rq.getUrlPath().equals("exit")) {
        break;
      } else if (rq.getUrlPath().equals("/usr/article/write")) {
        actionUsrArticleWrite();
      } else if (rq.getUrlPath().equals("/usr/article/detail")) {
        actionUsrArticleDetail(rq);
      } else if (rq.getUrlPath().equals("/usr/article/modify")) {
        actionUsrArticleModify(rq);
      } else if (rq.getUrlPath().equals("/usr/article/delete")) {
        actionUsrArticleDelete(rq);
      } else if (rq.getUrlPath().equals("/usr/article/list")) {
        actionUsrArticleList(rq);
      } else {
        System.out.println("잘못 된 명령어 입니다.");
      }
    }

    System.out.println("== 프로그램 종료 ==");

    sc.close();
  }

  private static void actionUsrArticleWrite() {
    System.out.println("== 게시물 등록 ==");
    System.out.printf("제목 : ");
    String title = Container.sc.nextLine();
    System.out.printf("내용 : ");
    String content = Container.sc.nextLine();
    int id = ++articlesLastId;

    Article article = new Article(id, title, content);

    articles.add(article);

    System.out.println("생성 된 게시물 객체 : " + article);
    System.out.printf("%d번 게시물이 등록되었습니다.\n", article.id);
  }

  private static void actionUsrArticleDetail(Rq rq) {
    Map<String, String> params = rq.getParams();

    if (params.containsKey("id") == false) {
      System.out.println("id를 입력해주세요.");
      return;
    }

    int id = 0;

    try {
      id = Integer.parseInt(params.get("id"));
    } catch (NumberFormatException e) {
      System.out.println("id를 정수 형태로 입력해주세요.");
      return;
    }

    // 게시물이 비어있거나, 입력한 id가 articles에 size를 넘어선 경우
    if (articles.isEmpty() || id > articles.size()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    Article foundArticle = null;

    for(Article article : articles) {
      if(article.id == id) {
        foundArticle = article;
        break;
      }
    }

    if(foundArticle == null) {
      System.out.println("해당 게시물은 존재하지 않습니다.");
      return;
    }

    System.out.println("-- 게시물 상세보기 --");
    System.out.printf("번호 : %d\n", foundArticle.id);
    System.out.printf("제목 : %s\n", foundArticle.title);
    System.out.printf("내용 : %s\n", foundArticle.content);
  }

  private static void actionUsrArticleModify(Rq rq) {
    Map<String, String> params = rq.getParams();

    if (params.containsKey("id") == false) {
      System.out.println("id를 입력해주세요.");
      return;
    }

    int id = 0;

    try {
      id = Integer.parseInt(params.get("id"));
    } catch (NumberFormatException e) {
      System.out.println("id를 정수 형태로 입력해주세요.");
      return;
    }

    if (articles.isEmpty() || id > articles.size()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    Article foundArticle = null;

    for(Article article : articles) {
      if(article.id == id) {
        foundArticle = article;
        break;
      }
    }

    if(foundArticle == null) {
      System.out.println("해당 게시물은 존재하지 않습니다.");
      return;
    }

    System.out.printf("-- %d번 게시물 수정 --\n", foundArticle.id);
    System.out.printf("새 제목 : ");
    foundArticle.title = Container.sc.nextLine();
    System.out.printf("새 내용 : ");
    foundArticle.content = Container.sc.nextLine();

    System.out.printf("%d번 게시물이 수정 되었습니다.\n", foundArticle.id);
  }

  private static void actionUsrArticleDelete(Rq rq) {
    Map<String, String> params = rq.getParams();

    if (params.containsKey("id") == false) {
      System.out.println("id를 입력해주세요.");
      return;
    }

    int id = 0;

    try {
      id = Integer.parseInt(params.get("id"));
    } catch (NumberFormatException e) {
      System.out.println("id를 정수 형태로 입력해주세요.");
      return;
    }

    if (articles.isEmpty() || id > articles.size()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    Article foundArticle = null;

    for(Article article : articles) {
      if(article.id == id) {
        foundArticle = article;
        break;
      }
    }

    if(foundArticle == null) {
      System.out.println("해당 게시물은 존재하지 않습니다.");
      return;
    }

    System.out.printf("%d번 게시물이 삭제 되었습니다.\n", foundArticle.id);

    articles.remove(foundArticle);
  }


  private static void actionUsrArticleList(Rq rq) {
    System.out.println("-- 게시물 리스트 --");
    System.out.println("-------------------");
    System.out.println("번호 / 제목");
    System.out.println("-------------------");

    Map<String, String> params = rq.getParams();

    // 검색 시작
    List<Article> filteredArticles = articles;

    if (params.containsKey("searchKeyword")) {
      String searchKeyword = params.get("searchKeyword");

      filteredArticles = new ArrayList<>();

      for (Article article : articles) {
        boolean matched = article.title.contains(searchKeyword) || article.content.contains(searchKeyword);

        if (matched) {
          filteredArticles.add(article);
        }
      }
    }

    List<Article> sortedArticles = filteredArticles; // 검색어가 없으면 filteredArticles는 articles랑 똑같다.

    boolean orderByIdDesc = true;

    if (params.containsKey("orderBy") && params.get("orderBy").equals("idAsc")) {
      orderByIdDesc = false;
    }

    if (orderByIdDesc) {
      sortedArticles = Util.reverseList(sortedArticles);
    }

    for (Article article : sortedArticles) {
      System.out.printf("%d / %s\n", article.id, article.title);
    }
  }
}





