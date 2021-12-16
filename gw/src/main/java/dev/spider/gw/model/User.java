package dev.spider.gw.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author spider
 */
public class User implements Serializable {
    private String userName;
    private String phone;
    private String address;
    private String email;
    private String netAddress;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNetAddress() {
        return netAddress;
    }

    public void setNetAddress(String netAddress) {
        this.netAddress = netAddress;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", netAddress='" + netAddress + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) && Objects.equals(phone, user.phone) && Objects.equals(address, user.address) && Objects.equals(email, user.email) && Objects.equals(netAddress, user.netAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, phone, address, email, netAddress);
    }
}
