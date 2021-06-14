package com.wujianar.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageList {
    private int page;
    private int size;
    private int count;
    private int totalPage;
    private ImageListItem[] items;

    public ImageList() {
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

    public ImageListItem[] getItems() {
        return items;
    }

    public void setItems(ImageListItem[] items) {
        this.items = items;
    }
}
