package com.ldt;

public class TestString {

    public static void main(String[] args) {
        String token1 ="aasdasda65132as1dasda2122sd12";
        String token2 ="aashdiasdbsdajns21654a6s54656";
        String token3 ="asdakjahsduasaaaashbdahs48488";


        String allToken = token1+","+token2+","+token3+",";
        String str = allToken.replaceAll(token2+",","");
        System.out.println(str);

        if (allToken.indexOf(token1+",")!=-1) {
            System.out.println("contain");
        } else {
            System.out.println("no contain");
        }
    }
}
