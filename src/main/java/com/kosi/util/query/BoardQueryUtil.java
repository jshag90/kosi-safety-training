package com.kosi.util.query;

public class BoardQueryUtil {

    public static String insertNotice(){
        String insertNotice = "INSERT INTO notice_board (content, created_at, title, updated_at, user_id) " +
                "VALUES ( :content, :createdAt, :title, :updateAt, :userId)";
        return insertNotice;
    }

    public static String insertFaqType(){
        return "INSERT INTO faq_type(faq_type_text) VALUES(:faqTypeText)";
    }

}
