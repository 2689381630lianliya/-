package org.blog.entiy;

import java.io.Serializable;

public class article implements Serializable {
    private  String name;
    private  Integer id;
    private String memory;
    private Integer award;
    private String title;
    private  Integer count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public Integer getAward() {
        return award;
    }

    public void setAward(Integer award) {
        this.award = award;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "article{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", memory='" + memory + '\'' +
                ", award=" + award +
                ", title='" + title + '\'' +
                ", count=" + count +
                '}';
    }
}
