package com.java.learn.language;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;

public class LanguageDetect {
    public static void main(String[] args) throws LangDetectException {
        DetectorFactory.loadProfile("profiles");
        Detector detect;
        try {
            detect = DetectorFactory.create();
            detect.append("Twój kod potwierdzający to 498071 ");
            System.out.println(detect.detect());
        } catch (LangDetectException e) {
            e.printStackTrace();
        }

        try {
            detect = DetectorFactory.create();
            detect.append("打扫打扫撒 ");
            System.out.println(detect.detect());
        } catch (LangDetectException e) {
            e.printStackTrace();
        }
    }
}
