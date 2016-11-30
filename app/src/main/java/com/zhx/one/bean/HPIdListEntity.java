package com.zhx.one.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Author   :zhx
 * Create at 2016/11/30
 * Description: 首页的10个文章页面ID
 */
public class HPIdListEntity implements Serializable{

    /**
     * res : 0
     * data : ["1544","1540","1542","1541","1539","1534","1537","1536","1533","1535"]
     */

    private int res;
    private List<String> data;

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
