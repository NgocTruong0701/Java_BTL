/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author linhc
 */
public class Budget {
    private Long id;
    private Long budget;

    public Budget(Long id, Long budget) {
        this.id = id;
        this.budget = budget;
    }

    public Budget() {
        id = budget = (long)0;
    }

    public Long getId() {
        return id;
    }

    public Long getBudget() {
        return budget;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }
    

}
