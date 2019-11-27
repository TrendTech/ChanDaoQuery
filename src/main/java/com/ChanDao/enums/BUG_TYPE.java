package com.ChanDao.enums;

/**
 * Author ajiang
 * Created 2019/11/26 18:55
 */
public enum BUG_TYPE {
    ASSIGN("指派"),
    NUMBER("数量"),
    SEVERITY("严重级别"),
    REOPEN("重新激活"),
    LONGUNRESOLVED("最长未解决"),
    ALLUNRESOLVED("累计未解决");

    private String name;

    private BUG_TYPE(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
