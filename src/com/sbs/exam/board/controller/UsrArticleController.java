package com.sbs.exam.board.controller;

import com.sbs.exam.board.Article;
import com.sbs.exam.board.Rq;
import com.sbs.exam.board.Util;
import com.sbs.exam.board.container.Container;

import java.util.ArrayList;
import java.util.List;

public class UsrArticleController {
  private int articlesLastId;
  private List<Article> articles;

  public UsrArticleController() {
    articlesLastId = 0;
    articles = new ArrayList<>();

    makeTestData();

    if (articles.size() > 0) {
      articlesLastId = articles.get(articles.size() - 1).id;
    }
  }

  public void makeTestData() {
    for (int i = 1; i <= 100; i++) {
      articles.add(new Article(i, "제목" + i, "내용" + i));
    }
  }

  public void actionWrite() {
    System.out.println("== 게시물 등록 ==");
    System.out.printf("제목 : ");
    String title = Container.sc.nextLine();

    if(title.length() == 0) {
      System.out.println("제목을 입력해주세요.");
      return;
    }

    System.out.printf("내용 : ");
    String content = Container.sc.nextLine();

    if(content.length() == 0) {
      System.out.println("내용을 입력해주세요.");
      return;
    }

    int id = ++articlesLastId;

    Article article = new Article(id, title, content);

    articles.add(article);

    System.out.println("생성 된 게시물 객체 : " + article);
    System.out.printf("%d번 게시물이 등록되었습니다.\n", article.id);
  }

  public void showDetail(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if(id == 0) {
      System.out.println("id를 올바를게 입력해주세요.");
      return;
    }

    // 게시물이 비어있거나, 입력한 id가 articles에 size를 넘어선 경우
    if (articles.isEmpty() || id > articles.size()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    Article foundArticle = null;

    for (Article article : articles) {
      if (article.id == id) {
        foundArticle = article;
        break;
      }
    }

    if (foundArticle == null) {
      System.out.println("해당 게시물은 존재하지 않습니다.");
      return;
    }

    System.out.println("-- 게시물 상세보기 --");
    System.out.printf("번호 : %d\n", foundArticle.id);
    System.out.printf("제목 : %s\n", foundArticle.title);
    System.out.printf("내용 : %s\n", foundArticle.content);
  }

  public void actionModify(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if(id == 0) {
      System.out.println("id를 올바를게 입력해주세요.");
      return;
    }

    if (articles.isEmpty() || id > articles.size()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    Article foundArticle = null;

    for (Article article : articles) {
      if (article.id == id) {
        foundArticle = article;
        break;
      }
    }

    if (foundArticle == null) {
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

  public void actionDelete(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if(id == 0) {
      System.out.println("id를 올바를게 입력해주세요.");
      return;
    }

    if (articles.isEmpty() || id > articles.size()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    Article foundArticle = null;

    for (Article article : articles) {
      if (article.id == id) {
        foundArticle = article;
        break;
      }
    }

    if (foundArticle == null) {
      System.out.println("해당 게시물은 존재하지 않습니다.");
      return;
    }

    System.out.printf("%d번 게시물이 삭제 되었습니다.\n", foundArticle.id);

    articles.remove(foundArticle);
  }

  public void showList(Rq rq) {
    String searchKeyword = rq.getParam("searchKeyword", "");

    // 검색 시작
    List<Article> filteredArticles = articles;

    if (searchKeyword.length() > 0) {

      filteredArticles = new ArrayList<>();

      for (Article article : articles) {
        boolean matched = article.title.contains(searchKeyword) || article.content.contains(searchKeyword);

        if (matched) {
          filteredArticles.add(article);
        }
      }
    }
    // 검색 끝

    List<Article> sortedArticles = filteredArticles; // 검색어가 없으면 filteredArticles는 articles랑 똑같다.

    String orderBy = rq.getParam("orderBy", "idDesc");
    boolean orderByIdDesc = orderBy.equals("idDesc");

    if (orderByIdDesc) {
      sortedArticles = Util.reverseList(sortedArticles);
    }

    System.out.println("-- 게시물 리스트 --");
    System.out.println("-------------------");
    System.out.println("번호 / 제목");
    System.out.println("-------------------");

    for (Article article : sortedArticles) {
      System.out.printf("%d / %s\n", article.id, article.title);
    }
  }
}
