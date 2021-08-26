package com.javarush.task.pro.task09.task0912;

/* 
Проверка URL-адреса
*/

public class Solution {
    public static void main(String[] args) {
        String[] urls = {"https://javarush.ru", "https://google.com", "http://wikipedia.org", "facebook.com", "https://instagram", "codegym.cc"};
        for (String url : urls) {
            String protocol = checkProtocol(url);
            String domain = checkDomain(url);

            System.out.println("У URL-адреса - " + url + ", сетевой протокол - " + protocol + ", домен - " + domain);
        }
    }

    public static String checkProtocol(String url) {
        if (!(url.startsWith("http")) && !(url.startsWith("https")))
            return "неизвестный";

        int index = url.indexOf(":");

        return url.substring(0, index);
    }

    public static String checkDomain(String url) {
        if (!(url.endsWith("com")) && !(url.endsWith("ru")) && !(url.endsWith("net")) && !(url.endsWith("org")))
            return "неизвестный";

        int index = url.lastIndexOf(".");

        return url.substring(index + 1, url.length());
    }
}
