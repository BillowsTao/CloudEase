package com.qtu404.common;

import java.util.HashMap;

public class WordMap {
    private static HashMap<String, String> translate = new HashMap<String, String>();

    static {

    }

    public static String translate(String word) {
        return translate.get(word);
    }


    public static void stateTranslate(String stateCdoe) {
        switch (stateCdoe) {
            case "success_09":
                //添加回复成功
                break;

            case "error_09":
                //添加回复失败
                break;

            case "success_08":
                //修改头像成功
                break;

            case "error_08":
                //修改头像失败
                break;

            case "success_07":
                //取消点赞成功
                break;

            case "error_07":
                //取消点赞失败
                break;

            case "success_06":
                //点赞成功
                break;

            case "error_06":
                //点赞失败
                break;

            case "success_05":
                //登陆成功
                break;
            case "success_04":
                //创建推文成功
                break;

            case "success_03":
                //取消关注成功
                break;

            case "success_02":
                //关注成功
                break;

            case "success_01":
                //添加用户成功
                break;

            case "error_01":
                //添加失败
                break;

            case "error_02":
                //关注失败
                break;

            case "error_03":
                //取消关注失败
                break;

            case "error_04":
                //创建推文失败
                break;

            case "error_05":
                //登录失败
                break;

            case "error":
                //未知错误
                break;

            default:
                //未知错误
                break;
        }

    }

}
