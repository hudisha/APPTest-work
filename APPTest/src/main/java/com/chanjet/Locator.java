package com.chanjet;

/**
 * @author yuxin
 *
 */
public class Locator {

    private String address; // 定位地址
    private int waitSec; //等待时间
    private ByType byType;//定位方式

    /**
     * 定位方式 枚举
     */
    public enum ByType{
        by, xpath, linkText, id, name, className,partLinkText,selector,tagName,
    }

    public Locator(){}

    public Locator(String address){
        this.address = address;
        this.waitSec = 3;
        this.byType = ByType.xpath;
    }

    public Locator(String address, int waitSec){
        this.address = address;
        this.waitSec = waitSec;
        this.byType = ByType.xpath;
    }

    public Locator(String address, int waitSec, ByType byType){
        this.address = address;
        this.waitSec = waitSec;
        this.byType = byType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getWaitSec() {
        return waitSec;
    }

    public void setWaitSec(int waitSec) {
        this.waitSec = waitSec;
    }

    public ByType getByType() {
        return byType;
    }

    public void setByType(ByType byType) {
        this.byType = byType;
    }

}
