package org.blog.entiy;

import java.io.Serializable;
import java.util.Objects;

public class record  implements Serializable {
    private int id;
    private String address;
    private String username;
    private String title;

    @Override
    public String toString() {
        return "record{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", name='" + username + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public record(int id, String address, String name,String title) {
        this.id = id;
        this.address = address;
        this.title=title;
        this.username = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        record record = (record) o;
        return id == record.id &&
                Objects.equals(address, record.address) &&
                Objects.equals(username, record.username) &&
                Objects.equals(title, record.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, username, title);
    }
}
