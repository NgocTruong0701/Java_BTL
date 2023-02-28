/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.UserController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import model.Noti;
import model.User;

/**
 *
 * @author Lê Ngọc Trường
 */
public class SetDateInterview extends javax.swing.JFrame {

    // Tạo 1 User Controller để thao tác với dữ liệu User
    UserController userController = new UserController();
    // Tạo 1 đối tượng Notification để có thể dùng hiển thị các thông báo khi thực hiện 1 điều gì đó. Tối ưu sử dụng lại code
    Noti noti = new Noti(this);
    // Lưu thông tin danh sách User
    ArrayList<User> listUser;
    // 2 biến lưu mã sinh viên của admin và password admin sau khi thoát hiển thị lại
    private String maSVAdmin;
    private String passwordAdmin;
    // Tao DefaultTableModel để đổ dữ liệu vào Table
    DefaultTableModel dmodel = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            // chỉ được xem, không được sửa row and column nào hết
            return false;
        }
    };

    /**
     * Creates new form SetDateInterview
     */
    public SetDateInterview() {
        initComponents();
        showInfor();
        pack();
    }

    public SetDateInterview(String maSVAD, String passwordAD) {
        initComponents();
        this.maSVAdmin = maSVAD;
        this.passwordAdmin = passwordAD;
        showInfor();
        pack();
    }

    // Method show Information when Frame create 
    private void showInfor() {
        // Lấy ra danh sách user chưa chính thức
        try {
            listUser = userController.getListUsersCCT();
        } catch (IOException e) {
            noti.showNotiError("Có lỗi: " + e.toString());
        }

        // Add các cột cho DTM
        dmodel.addColumn("Mã sinh viên");
        dmodel.addColumn("Họ tên");
        dmodel.addColumn("Khoa");
        dmodel.addColumn("Lớp");
        dmodel.addColumn("Ngày phỏng vấn");
        dmodel.addColumn("Email");
        dmodel.addColumn("Trạng thái");
        dmodel.addColumn("Thông tin");
        
        // lấy HashMap thời gian phỏng vấn của user
        HashMap<String, String> listUserDate = new HashMap<>();
        try{
            listUserDate = userController.readDateInterViewFromFile();
        }catch(IOException e){
            noti.showNotiError("Có lỗi: " + e.toString());
        }

        // Add các dòng cho DTM, tuy nhiên các user phải ở trạng thái rảnh (Status = 0)
        for (User user : listUser) {
            if (user.getStatus() == 0) {
                Object[] row = {user.getMaSV(), user.getFullName(), user.getKhoa(), user.getLop(),listUserDate.get(user.getMaSV()), user.getEmail(), userController.getStatus(user.getStatus()), userController.getCheck(user.getCheck())};
                dmodel.addRow(row);
            }
        }
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableUser = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtNgayPV = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnExits = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Lên lịch phỏng vấn");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách viên chưa chính thức"));

        tableUser.setModel(dmodel);
        jScrollPane1.setViewportView(tableUser);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel1.setText("Ngày phỏng vấn");

        btnSave.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        btnSave.setText("Lưu");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnExits.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        btnExits.setText("Thoát");
        btnExits.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 374, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNgayPV, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(362, 362, 362))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(btnExits, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(347, 347, 347))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayPV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExits, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        // Lấy index của người dùng chọn dòng nào trong bảng.
        int index = tableUser.getSelectedRow();
        // Lấy ra model của bảng đang dùng
        DefaultTableModel model = (DefaultTableModel) tableUser.getModel();
        if (index != -1) {
            String maSV = (String) model.getValueAt(index, 0);
            String date = txtNgayPV.getText().trim();

            try {
                if (userController.writeDateInterView(maSV, date)) {
                    noti.showNotiInformation("Đã thêm lịch phỏng vấn cho thành viên " + maSV);
                    new SetDateInterview(maSV, passwordAdmin).setVisible(true);
                    this.setVisible(false);
                }
            } catch (Exception e) {
                noti.showNotiError("Thêm lịch phỏng vấn thất bại");
                System.out.println(e.toString());
            }
        }
        else{
            noti.showNotiError("Chọn thành viên muốn thêm thời gian phỏng vấn");
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnExitsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitsActionPerformed
        // TODO add your handling code here:
        new HomeAdmin(maSVAdmin, passwordAdmin).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnExitsActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SetDateInterview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SetDateInterview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SetDateInterview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SetDateInterview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SetDateInterview().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExits;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableUser;
    private javax.swing.JTextField txtNgayPV;
    // End of variables declaration//GEN-END:variables
}
