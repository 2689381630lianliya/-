package org.blog.entiy;

import java.io.Serializable;

public class user  implements Serializable {

    private Integer id;
    private String username;
    private String password;
    private String sex;
    private  Integer address_id;
    private String address;
    private  Integer money;
    private int amount;
    private String sign;



    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getAddress() {
        return address;
    }

    public user() {

    }

    public user(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public user(String username, String password, String sex, Integer address_id, String address, Integer money) {
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.address_id = address_id;
        this.address = address;
        this.money = money;
    }

    public user(String username, String password, String sex, String address) {
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.address = address;
    }

    public user(Integer id, String username, String password, String sex, Integer address_id, String address, Integer money) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.address_id = address_id;
        this.address = address;
        this.money = money;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Integer address_id) {
        this.address_id = address_id;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", address_id=" + address_id +
                ", address='" + address + '\'' +
                ", money=" + money +
                ", amount=" + amount +
                '}';
    }
}
