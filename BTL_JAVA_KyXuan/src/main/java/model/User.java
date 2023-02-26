/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author linhc
 */
public class User {
    private Long id;
    private String maSV;
    private String fullName;
    private String khoa;
    private String lop;
    private String password;
    private String email;
    private Integer status; // 0 là rảnh, 1 là bận, 2 là đã được chọn đi sự kiện
    private Integer check; // 1 là thành viên của đội, 0 là sinh viên bình thường
    private Long idRole; // 1 là người dùng, 2 là admin
    private Long idEvent;

    public User() {
    }

    public User(Long id, String maSV, String fullName, String khoa, String lop, String password, String email, Integer status, Integer check, Long idRole, Long idEvent) {
        this.id = id;
        this.maSV = maSV;
        this.fullName = fullName;
        this.khoa = khoa;
        this.lop = lop;
        this.password = password;
        this.email = email;
        this.status = status;
        this.check = check;
        this.idRole = idRole;
        this.idEvent = idEvent;
    }

    
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCheck() {
        return check;
    }

    public void setCheck(Integer check) {
        this.check = check;
    }

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public Long getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Long idEvent) {
        this.idEvent = idEvent;
    }
    
    
    
    
}
