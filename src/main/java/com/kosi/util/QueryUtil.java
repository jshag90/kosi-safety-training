package com.kosi.util;

public class QueryUtil {

    public static String insertUser(){
        String inserUserQuery = "INSERT INTO user (username, password, nickname, activated) VALUES (:username, :password, :nickname, :activated)";
        return inserUserQuery;
    }

    public static String insertAuthorityQuery(){
        String insertUserAuthorityQuery = "INSERT INTO user_authority (user_id, authority_name) VALUES (:userId, :authorityName)";
        return insertUserAuthorityQuery;
    }

}
