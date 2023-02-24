/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import java.util.regex.Pattern;

/**
 *
 * @author linhc
 */
public class Constant {
    
    public static final String USER_FILE = "User.DAT";
    public static final String ROLE_FILE = "Role.DAT";
    public static final String OPERATINGFEE_FILE = "OperatingFee.DAT";
    public static final String EVENT_FILE = "Event.DAT";
    public static final String BUDGET_FILE = "Budget.DAT";
    public static final String USER_CUR_FILE ="User_Cur.DAT";
    
    public static Pattern regexPassword = Pattern.compile("^(?=.*[a-z])(?=.*[0-9])[A-Za-z0-9@$!%*?&]{8,}$");
    public static Pattern regexEmail = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
}
