package model;

public class CustomerCall {
    public int detailId;
    public String name;
    public String phone;
    public int isCalled;

    public CustomerCall(int detailId, String name, String phone, int isCalled) {
        this.detailId = detailId;
        this.name     = name;
        this.phone    = phone;
        this.isCalled = isCalled;
    }
}
