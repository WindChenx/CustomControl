package com.wind.a7_lianxiren;

public class Person {
    private String name;
    private String pinyin;

    public Person(String name) {
        this.name = name;
        this.pinyin = PinYinUtils.getPinYin(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getName() {
        return name;
    }

    public String getPinyin() {
        return pinyin;
    }
}
