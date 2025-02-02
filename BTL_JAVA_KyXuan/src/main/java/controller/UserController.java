/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import common.Constant;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import model.User;

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
    
    public boolean writeDateInterView(String maSV, String dateInterview) throws IOException{
        HashMap<String, String> list = readDateInterViewFromFile();
        list.put(maSV, dateInterview);
        fileController.OpenFileToWrite(Constant.DATE_INTERVIEW);
        for(Map.Entry<String, String> entry : list.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            fileController.getPrintWriter().println(key + "|" + value);
        }
        fileController.CloseFileAfterWrite();
        return true;
    }
    
    public HashMap<String,String> readDateInterViewFromFile() throws IOException{
        HashMap<String, String> listStudentAndDate = new HashMap<>();
        fileController.OpenFileToRead(Constant.DATE_INTERVIEW);
        
        while(fileController.scanner.hasNext()){
            String data = fileController.scanner.nextLine();
            String[] a = data.split("\\|");
            
            listStudentAndDate.put(a[0], a[1]);
        }
        
        return listStudentAndDate;
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

        return (long)-1;
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
    
    // Get ra list User trong File User
    public ArrayList<User> getListUsers() throws IOException {
        ArrayList<User> users = (ArrayList< User>) readUsersFromFile(Constant.USER_FILE);
        return users;
    }
    
    // Get ra list User chưa chính thức
    public ArrayList<User> getListUsersCCT() throws IOException{
        // get ra list User
        ArrayList<User> listUsers = getListUsers();
        // ArrayList lưu user chưa chính thức
        ArrayList<User> listUsersCCT = new ArrayList<>();
        for(User user : listUsers){
            if(user.getCheck() == 0){
                listUsersCCT.add(user);
            }
        }
        
        return listUsersCCT;
    }
    
    // Lấy ra list User chưa chính thức và rảnh
    public ArrayList<User> getListUserFree() throws IOException{
        // get ra list User
        ArrayList<User> listUsers = getListUsers();
        // ArrayList lưu user chính thức và rảnh (ĐK : status = 0, check = 1)
        ArrayList<User> listUsersFree = new ArrayList<>();
        for(User user : listUsers){
            if(user.getStatus() == 0 && user.getCheck() == 1){
                listUsersFree.add(user);
            }
        }
        
        return listUsersFree;
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
        long id = users.size();
        long idEvent = 0;
        User newUser = new User(id, maSV, hoTen, khoa, lop, password, email, status, check, idRole, idEvent);
        System.out.println(newUser.toString());
        users.add(newUser);
        writeUsersToFile(users, Constant.USER_FILE);
        return true;
    }
    
    public boolean addUser(String maSV, String hoTen, String khoa, String lop, String password, String email) throws IOException{
        ArrayList<User> listUsers = getListUsers();
        for (User u : listUsers) {
            if (u.getMaSV().equalsIgnoreCase(maSV)) {
                return false;
            }
        }
        long id = listUsers.size() + 1;
        long idEvent = 0;
        long idRole = 1;
        int status = 0;
        int check = 0;
        User newUser = new User(id, maSV, hoTen , khoa, lop, password, email, status, check, idRole, idEvent);
        listUsers.add(newUser);
        writeUsersToFile(listUsers, Constant.USER_FILE);
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
    
    public ArrayList<Integer> getNumberUser() throws IOException{
        ArrayList<Integer> numberUser = new ArrayList<>();
        ArrayList<User> listUsers = getListUsers();
        numberUser.add(listUsers.size());
        int soTVChua = 0;
        int soTVCT = 0;
        for(User user : listUsers){
            if(user.getCheck() == 0){
                soTVChua++;
            }
            if(user.getCheck() == 1){
                soTVCT++;
            }
        }
        numberUser.add(soTVChua);
        numberUser.add(soTVCT);
        
        return numberUser;
    }
    
}
