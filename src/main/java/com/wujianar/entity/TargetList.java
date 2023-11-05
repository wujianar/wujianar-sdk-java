package com.wujianar.entity;

public class TargetList {
    private int page;
    private int size;
    private int count;
    private int totalPage;
    private TargetSimple[] items;

    public TargetList() {
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public TargetSimple[] getItems() {
        return items;
    }

    public void setItems(TargetSimple[] items) {
        this.items = items;
    }
}
