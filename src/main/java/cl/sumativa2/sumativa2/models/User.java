package cl.sumativa2.sumativa2.models;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "userName")
    private String userName;

    @Column(name = "u_password")
    private String password;

    @Column(name = "rol")
    private String rol;

    @Column(name = "address_1")
    private String dispatchAddress1;

    @Column(name = "address_2")
    private String dispatchAddress2;

    @Column(name = "address_3")
    private String dispatchAddress3;

    public User(Long id, String email, String userName, String password, String rol, String dispatchAddress1, String dispatchAddress2, String dispatchAddress3) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.rol = rol;
        this.dispatchAddress1 = dispatchAddress1;
        this.dispatchAddress2 = dispatchAddress2;
        this.dispatchAddress3 = dispatchAddress3;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getDispatchAddress1() {
        return dispatchAddress1;
    }

    public void setDispatchAddress1(String dispatchAddress1) {
        this.dispatchAddress1 = dispatchAddress1;
    }

    public String getDispatchAddress2() {
        return dispatchAddress2;
    }

    public void setDispatchAddress2(String dispatchAddress2) {
        this.dispatchAddress2 = dispatchAddress2;
    }

    public String getDispatchAddress3() {
        return dispatchAddress3;
    }

    public void setDispatchAddress3(String dispatchAddress3) {
        this.dispatchAddress3 = dispatchAddress3;
    }
}
