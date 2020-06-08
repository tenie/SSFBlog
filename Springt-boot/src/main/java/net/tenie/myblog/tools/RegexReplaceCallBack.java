package net.tenie.myblog.tools;

/**
* 定义正则表达示替换的回调

*/

public interface RegexReplaceCallBack {

    public String replace(String matcherGroup);
}
