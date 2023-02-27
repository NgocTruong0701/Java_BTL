/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import common.Constant;
import controller.UserController;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JDialog;
import javax.swing.JLabel;
import model.Noti;
import model.User;

/**
 *
 * @author linhc
 */
public class HomeStudent extends javax.swing.JFrame {

    /**
     * Creates new form HomeStudent
     */
    // Tạo 1 userController để có thể sử lý logic liên quan đến dữ liệu của User
    private UserController control = new UserController();
    // Tạo 1 đối tượng Notification để có thể dùng hiển thị các thông báo khi thực hiện 1 điều gì đó. Tối ưu sử dụng lại code
    private Noti noti = new Noti(this);
    // 2 Biến giúp lưu thông tin sau khi đăng nhập. Để có thể lấy ra được thông tin trong file đưa lên màn hình.
    private String maSV;
    private String password;
    // đối tượng user để lưu dữ liệu user đó nhằm hiển thị lên màn hình
    User model = new User();

    // Phương thức khởi tạo 2 tham số mã sinh viên và password được dùng khi đăng nhập sẽ gửi mã sinh viên và password qua
    public HomeStudent(String maSV, String password) {
        initComponents();
        this.maSV = maSV;
        this.password = password;
        this.showInfor();
        showNoti();
    }
    
    // 1 JDiaLog để hiển thị thông báo
    private void showNoti(){
        try{
            HashMap<String, String> listDateInterview = control.readDateInterViewFromFile();
            String date = listDateInterview.get(maSV);
            if(date != null){
                JDialog mess = new JDialog(this, "Thông báo");
                mess.add(new JLabel("Thời gian phỏng vấn của bạn: " + date));
                mess.setSize(300,300);
                mess.setLocationRelativeTo(this);
                setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                mess.setVisible(true);
            }
        }catch(IOException e){
            noti.showNotiError("Có lỗi:" + e.toString());
        }
    }

    // Phương thức hiển thị thông tin sinh viên lên màn hình khi View HomeStudent được khởi tạo
    private void showInfor() {
        // Lấy user thông qua controller
        try {
            model = control.getUse(maSV, password);
        } catch (IOException e) {
            noti.showNotiError("Loi " + e.toString());
        }
        // Hiển thị thông tin user đó lên màn hình.
        txtMaSV.setText(model.getMaSV());
        txtHoTen.setText(model.getFullName());
        txtKhoa.setText(model.getKhoa());
        txtLop.setText(model.getLop());
        txtPassword.setText(model.getPassword());
        txtEmail.setText(model.getEmail());

        if (null == model.getStatus()) {
            rdoBusy.setSelected(true);
        } else {
            switch ((int) model.getStatus()) {
                case 0:
                    rdoFree.setSelected(true);
                    break;
                case 1:
                    rdoBusy.setSelected(true);
                    break;
                case 2:
                    rdoHaveTask.setSelected(true);
                    break;
                default:
                    rdoBusy.setSelected(true);
                    break;
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

        rdoGroupStatus = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        txtMaSV = new javax.swing.JTextField();
        txtKhoa = new javax.swing.JTextField();
        txtLop = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        txtEmail = new javax.swing.JTextField();
        rdoBusy = new javax.swing.JRadioButton();
        rdoFree = new javax.swing.JRadioButton();
        rdoHaveTask = new javax.swing.JRadioButton();
        btnRepair = new javax.swing.JButton();

        rdoGroupStatus.add(rdoBusy);
        rdoGroupStatus.add(rdoFree);
        rdoGroupStatus.add(rdoHaveTask);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Thông tin sinh viên");

        jLabel1.setText("Mã sinh viên");

        jLabel2.setText("Họ và tên");

        jLabel3.setText("Khoa");

        jLabel4.setText("Lớp");

        jLabel5.setText("Password");

        jLabel6.setText("Email");

        jLabel7.setText("Tình trạng");

        txtMaSV.setEditable(false);

        rdoBusy.setText("Bận");

        rdoFree.setText("Rảnh");

        rdoHaveTask.setText("Có nhiệm vụ");
        rdoHaveTask.setEnabled(false);

        btnRepair.setText("Sửa");
        btnRepair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRepairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLop, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(rdoFree, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(rdoBusy, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(rdoHaveTask, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(59, 59, 59))
            .addGroup(layout.createSequentialGroup()
                .addGap(318, 318, 318)
                .addComponent(btnRepair)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5)
                    .addComponent(txtMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(txtKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoBusy))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoFree)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoHaveTask)))
                .addGap(76, 76, 76)
                .addComponent(btnRepair)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Bắt sự kiện click vào nút sửa
    private void btnRepairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRepairActionPerformed
        // TODO add your handling code here:
        // Lấy thông tin các trường text
        String maSV = txtMaSV.getText();
        String tenSV = txtHoTen.getText();
        String khoa = txtKhoa.getText();
        String lop = txtLop.getText();
        String password = new String(txtPassword.getPassword());
        String email = txtEmail.getText();

        // Kiểm tra xem radiobutton nào đang được sellected
        int status = -1;
        if (rdoFree.isSelected()) {
            status = 0;
        } else if (rdoBusy.isSelected()) {
            status = 1;
        } else if (rdoHaveTask.isSelected()) {
            status = 2;
        }

        // Kiểm tra rằng buộc dữ liệu của password và email (Check Validate) ở đây sẽ check xem 
        // mật khẩu hợp lệ phải chứa ít nhất một chữ cái thường, một số, có ít nhất 8 ký tự và chỉ bao gồm các ký tự được liệt kê
        // Email hợp lệ có dạng abc@xyz.com
        if (Constant.regexPassword.matcher(password).matches()) {
            if (Constant.regexEmail.matcher(email).matches()) {
                try {
                    boolean check = control.repairInformatin(maSV, tenSV, khoa, lop, password, email, status);
                    if (check) {
                        // Hiện thi dialogBox thông tin 
                        noti.showNotiInformation("Thay đổi thông tin thành công!!!");
                    } else {
                        // Hiển thị DialogBox báo lỗi
                        noti.showNotiError("Thay đổi thông tin không thành công!!!");
                    }
                } catch (IOException e) {
                    noti.showNotiError("Co loi: " + e.toString());
                }
            }
            else{
                // Hiển thị DialogBox lỗi
                noti.showNotiError("Email không hợp lệ");
            }
        }
        else{
            // Hiển thị dialogbox lỗi
            noti.showNotiError("mật khẩu hợp lệ phải chứa ít nhất một chữ cái thường, một số, có ít nhất 8 ký tự");
        }
    }//GEN-LAST:event_btnRepairActionPerformed

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
            java.util.logging.Logger.getLogger(HomeStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new HomeStudent().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRepair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JRadioButton rdoBusy;
    private javax.swing.JRadioButton rdoFree;
    private javax.swing.ButtonGroup rdoGroupStatus;
    private javax.swing.JRadioButton rdoHaveTask;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtKhoa;
    private javax.swing.JTextField txtLop;
    private javax.swing.JTextField txtMaSV;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
