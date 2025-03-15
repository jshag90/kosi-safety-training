package com.kosi.util.query;

public class UserQueryUtil {


    public static String insertUser(){
       return "INSERT INTO user (" +
                                                "  activated" +
                                                ", password" +
                                                ", username" +
                                                ", agree_personal_info_collection" +
                                                ", agree_personal_info_third_party" +
                                                ", birthday" +
                                                ", email" +
                                                ", phone_number" +
                                                ", name" +
                                                ", company_name" +
                                                ", company_number) " +
                "VALUES ( :activated, :password, :userName, :agreePersonalInfoCollection, :agreePersonalInfoThirdPart" +
                ", :birthday" +
                ", :email" +
                ", :phoneNumber" +
                ", :name" +
                ", :companyName" +
                ", :companyNumber" +
                ")";
    }

    public static String insertAuthorityQuery(){
        return "INSERT INTO user_authority (user_id, authority_name) VALUES (:userId, :authorityName)";
    }

}
