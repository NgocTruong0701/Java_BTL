/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import common.Constant;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import view.Login;

/**
 *
 * @author linhc
 */
public class UserController {

    private User user = new User();
    private FileController fileController = new FileController();

    public UserController() {
    }

    public UserController(User user, FileController fileController) {
        this.user = user;
        this.fileController = fileController;
    }

    public List<User> readUsersFromFile(String filename) throws IOException {
        fileController.OpenFileToRead(filename);
        List<User> users = new ArrayList<>();

        while (fileController.scanner.hasNext()) {
            String data = fileController.scanner.nextLine();
            String[] a = data.split("\\|");
            users.add(new User(Long.parseLong(a[0]), a[1], a[2], a[3], a[4], a[5], a[6], Integer.parseInt(a[7]), Integer.parseInt(a[8]), Long.parseLong(a[9]), Long.parseLong(a[10])));
        }

        fileController.CloseFileAfterRead(filename);

        return users;
    }

    public List<User> writeUsersToFile(List<User> users, String filename) throws IOException {
        fileController.OpenFileToWrite(filename);
        for (User user : users) {
            fileController.getPrintWriter().println(user.getId() + "|" + user.getMaSV() + "|" + user.getFullName() + "|" + user.getKhoa() + "|" + user.getLop() + "|" + user.getPassword() + "|" + user.getEmail() + "|" + user.getStatus() + "|" + user.getCheck() + "|" + user.getIdRole() + "|" + user.getIdEvent());
        }

        fileController.CloseFileAfterWrite();
        return users;
    }

    public void closeUsersAfterRead(String file) {
        fileController.CloseFileAfterWrite();
    }

    public Long logIn(String maSV, String password) throws IOException {
        List<User> users = readUsersFromFile("User.DAT");
        User cur_user = new User();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getMaSV().equalsIgnoreCase(maSV) && users.get(i).getPassword().equalsIgnoreCase(password)) {
                cur_user = users.get(i);
                List<User> user_cur = new ArrayList<>();
                user_cur.add(users.get(i));
                writeUsersToFile(user_cur, Constant.USER_CUR_FILE);
                return cur_user.getIdRole();
            }
        }

        return 0l;
    }

    public User getUse(String maSV, String password) throws IOException {
        List<User> users = readUsersFromFile("User.DAT");
        User cur_user = new User();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getMaSV().equalsIgnoreCase(maSV) && users.get(i).getPassword().equalsIgnoreCase(password)) {
                cur_user = users.get(i);
            }
        }
        return cur_user;
    }

    public User getUse(String maSV) throws IOException {
        List<User> users = readUsersFromFile(Constant.USER_FILE);
        User cur_user = new User();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getMaSV().equals(maSV)) {
                cur_user = users.get(i);
            }
        }
        return cur_user;
    }

    public boolean repairInformatin(String maSV, String tenSV, String khoa, String lop, String password, String email, int status) throws IOException {
        List<User> users = readUsersFromFile("User.DAT");
        User cur_user = new User();
        int check = -1;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getMaSV().equalsIgnoreCase(maSV)) {
                cur_user = users.get(i);
                check = 1;
            }
        }
        if (check == 1) {
            cur_user.setFullName(tenSV);
            cur_user.setKhoa(khoa);
            cur_user.setLop(lop);
            cur_user.setPassword(password);
            cur_user.setEmail(email);
            cur_user.setStatus(status);
            writeUsersToFile(users, "User.DAT");
            System.out.println(status);
            return true;
        }
        return false;
    }

    public boolean repairInformatin(String maSV, String tenSV, String khoa, String lop, String password, String email, int status, int checks, long idRole) throws IOException {
        List<User> users = readUsersFromFile("User.DAT");
        User cur_user = new User();
        int check = -1;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getMaSV().equalsIgnoreCase(maSV)) {
                cur_user = users.get(i);
                check = 1;
            }
        }
        if (check == 1) {
            cur_user.setFullName(tenSV);
            cur_user.setKhoa(khoa);
            cur_user.setLop(lop);
            cur_user.setPassword(password);
            cur_user.setEmail(email);
            cur_user.setStatus(status);
            cur_user.setCheck(checks);
            cur_user.setIdRole(idRole);
            writeUsersToFile(users, "User.DAT");
            return true;
        }
        return false;
    }

    public ArrayList<User> getListUsers() throws IOException {
        ArrayList<User> users = (ArrayList< User>) readUsersFromFile(Constant.USER_FILE);
        return users;
    }

    public String getStatus(int status) {
        String tt = "";
        switch (status) {
            case 0:
                tt += "Rảnh";
                break;
            case 1:
                tt += "Bận";
                break;
            case 2:
                tt += "Có nhiệm vụ";
                break;
            default:
                tt += "Rảnh";
                break;
        }
        return tt;
    }

    public String getCheck(int check) {
        String c = "";
        switch (check) {
            case 0:
                c += "TV chưa chính thức";
                break;
            case 1:
                c += "TV chính thức";
                break;
            default:
                c += "TV chưa chính thức";
                break;
        }
        return c;
    }

    public boolean addUser(String maSV, String hoTen, String khoa, String lop, String password, String email, int status, int check, long idRole) throws IOException {
        List<User> users = readUsersFromFile(Constant.USER_FILE);
        for (User u : users) {
            if (u.getMaSV().equalsIgnoreCase(maSV)) {
                return false;
            }
        }
        long id = users.size() + 1;
        long idEvent = 0;
        User newUser = new User(id, maSV, hoTen, khoa, lop, password, email, status, check, idRole, idEvent);
        System.out.println(newUser.toString());
        users.add(newUser);
        writeUsersToFile(users, Constant.USER_FILE);
        return true;
    }
    
    public boolean removeUser(String maSV) throws IOException{
        List<User> users = readUsersFromFile(Constant.USER_FILE);
        Iterator<User>iterator = users.iterator();
        while(iterator.hasNext()){
            User user = iterator.next();
            if(user.getMaSV().equalsIgnoreCase(maSV)){
                iterator.remove();
            }
        }
        for(int i = 0 ;i < users.size();i++){
            users.get(i).setId((long)i);
        }
        writeUsersToFile(users, Constant.USER_FILE);
        return true;
    }
}
