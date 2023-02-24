/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author mynam
 */
public class Noti {
    private Component frame;

    public Noti(Component frame) {
        this.frame = frame;
    }
    
    public void showNotiInformation(String noti){
        JOptionPane.showMessageDialog(frame, noti,"Thông tin", 1);
    }
    
    public void showNotiError(String noti){
        JOptionPane.showMessageDialog(frame, noti,"Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    
    // Yes = 0; No = 1    
    public int showOption(String noti, String title){
        return JOptionPane.showConfirmDialog(frame, noti, title,JOptionPane.YES_NO_OPTION);
    }
}