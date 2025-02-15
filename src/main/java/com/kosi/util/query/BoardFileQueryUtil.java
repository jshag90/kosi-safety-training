package com.kosi.util.query;

public class BoardFileQueryUtil {

    public static String insertBoardFile(){
        return "INSERT INTO upload_files(file_name, file_re_name, post_idx, upload_file_type) " +
                "VALUES(:fileName, :fileReName, :postIdx, :uploadFileType)";
    }

}
