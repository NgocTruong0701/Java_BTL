/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author linhc
 */
public class OperatingFee {
    private Long id;
    private String nameFee;
    private Long money;
    private Long idEvent;

    public OperatingFee() {
    }

    public OperatingFee(Long id, String nameFee, Long money, Long idEvent) {
        this.id = id;
        this.nameFee = nameFee;
        this.money = money;
        this.idEvent = idEvent;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameFee() {
        return nameFee;
    }

    public void setNameFee(String nameFee) {
        this.nameFee = nameFee;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Long getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Long idEvent) {
        this.idEvent = idEvent;
    }
    
}
