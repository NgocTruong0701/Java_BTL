/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import common.Constant;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Budget;
import model.Event;

/**
 *
 * @author linhc
 */
public class BudgetController {

    // 1 đối tượng Budget để chứa dữ liệu 
    private Budget budget = new Budget();
    // 1 đối tượng FileController để ta có thể thao tác với tệp
    private FileController fileController = new FileController();
    // 1 EventController để thao tác với dữ liệu Event
    private EventController eventController = new EventController();

    // Các phương thức khởi tạo có tham và không tham số
    public BudgetController() {
    }

    public BudgetController(Budget budget, FileController fileController) {
        this.budget = budget;
        this.fileController = fileController;
    }

    // Lấy Bugets từ File
    public List<Budget> readBudgetFromFile(String fileName) throws IOException, Exception {
        fileController.OpenFileToRead(fileName);
        List<Budget> listBudget = new ArrayList<>();

        while (fileController.scanner.hasNext()) {
            String data = fileController.scanner.nextLine();
            String[] a = data.split("\\|");
            listBudget.add(new Budget(Long.parseLong(a[0]), Long.parseLong(a[1])));
        }

        fileController.CloseFileAfterRead(fileName);

        return listBudget;
    }

    // Ghi Budgets vào File
    public List<Budget> writeBudgetToFile(List<Budget> listBudget, String fileName) throws IOException, Exception {
        fileController.OpenFileToWrite(fileName);
        for (Budget bg : listBudget) {
            fileController.getPrintWriter().println(bg.getId() + "|" + bg.getBudget());
        }

        fileController.CloseFileAfterWrite();
        return listBudget;
    }

    // Ghi thông tin thống kê được vào file
    public boolean ghiDuLieu(String tongTV, String tvChua, String tvChinh, String soSK, String tongCP, String nganSach, ArrayList<Event> listEvent) throws IOException, Exception {
        fileController.OpenFileToWrite(Constant.STATSTICAL_FILE);

        fileController.getPrintWriter().println("Tong thanh vien: " + tongTV + "\tSo su kien doi: " + soSK);
        fileController.getPrintWriter().println("So thanh vien chinh thuc: " + tvChinh + "\tTong chi phi hoat dong: " + tongCP);
        fileController.getPrintWriter().println("So thanh vien chua chinh thuc: " + tvChua + "\tNgan sach doi: " + nganSach);
        fileController.getPrintWriter().println("");
        fileController.getPrintWriter().println("\t=======Danh sach su kien doi========");
        fileController.getPrintWriter().println(String.format("%-5s %-20s %-15s %-15s %-15s %-15s %-15s", "ID", "Ten su kien", "Ngay bat dau", "Ngay ket thuc", "So luong TV", "Dia chi", "Tong chi phi"));
        for (Event e : listEvent) {
            fileController.getPrintWriter().println(String.format("%-5s %-20s %-15s %-15s %-15s %-15s %-15s", e.getId() + "", e.getNameEvent(), e.getStartDay(), e.getEndDay(), e.getNumberOfStudent() + "", e.getAddress(), eventController.getCostTotal(e) + ""));
        }

        fileController.CloseFileAfterWrite();
        return true;
    }

    public void closeBugetAfterRead(String file) {
        fileController.CloseFileAfterRead(file);
    }

    public long getSumBudget() throws IOException, Exception {
        // Lấy ra danh sách buget
        ArrayList<Budget> listBudgets = (ArrayList< Budget>) readBudgetFromFile(Constant.BUDGET_FILE);

        // lay ra tong 
        int sumBudget = 0;
        for (Budget bg : listBudgets) {
            sumBudget += bg.getBudget();
        }

        return sumBudget;
    }

}
