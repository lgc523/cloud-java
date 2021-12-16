package dev.spider.gw.entity.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author spider
 */
public class UserDTO implements Serializable {
    private String name;
    private String email;
    private String address;
    private String telephone;
    private String gitHub;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getGitHub() {
        return gitHub;
    }

    public void setGitHub(String gitHub) {
        this.gitHub = gitHub;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", gitHub='" + gitHub + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(name, userDTO.name) && Objects.equals(email, userDTO.email) && Objects.equals(address, userDTO.address) && Objects.equals(telephone, userDTO.telephone) && Objects.equals(gitHub, userDTO.gitHub);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, address, telephone, gitHub);
    }
}
