package pagesInit;

public enum PageType {
    MOBILE("mobile"),
    WEB("web");

    private final String desc;
    PageType(String desc) { this.desc = desc; }
    public String getDesc() { return desc; }
}
