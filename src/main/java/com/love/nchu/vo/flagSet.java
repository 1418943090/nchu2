package com.love.nchu.vo;

import org.springframework.stereotype.Component;

@Component
public class flagSet {

    private static boolean paperSet = false;
    protected  flagSet(){

    }

    public  boolean isPaperSet() {
        return paperSet;
    }

    public static void setPaperSet(boolean paperSet) {
        flagSet.paperSet = paperSet;
    }
}
