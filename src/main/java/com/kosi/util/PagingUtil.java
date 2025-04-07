package com.kosi.util;

public class PagingUtil {

    public static Integer getOffset(Integer page, Integer pageSize){
        return (page - 1) * pageSize;
    }

    public static Integer getLastPage(Integer totalCount, Integer pageSize){
        return(totalCount / pageSize) + 1;
    }

}
