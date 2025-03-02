package com.kosi.util.query;

public class BoardFileQueryUtil {

    public static String insertBoardFile(){
        return "INSERT INTO upload_files(file_name, file_re_name, post_id, upload_file_type) " +
                "VALUES(:fileName, :fileReName, :postId, :uploadFileType)";
    }

    public static String insertFaqType(){
        return "INSERT INTO faq_type(faq_type_text) VALUES(:faqTypeText)";
    }

}
