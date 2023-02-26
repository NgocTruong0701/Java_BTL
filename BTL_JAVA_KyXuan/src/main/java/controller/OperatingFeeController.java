/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import common.Constant;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Event;
import model.OperatingFee;

/**
 *
 * @author linhc
 */
public class OperatingFeeController {
    // 1 Đối tượng OperatingFee nhằm chứa dữ liệu để sử lý
    private OperatingFee fee = new OperatingFee();
    // 1 đối tượng FileController để sử lý với file
    private FileController fileController = new FileController();
    // 1 đối tượng EventController để sửa lý liên quan đến Event
    private EventController eventController = new EventController();
    
    // Phương thức khởi tạo không và có đối
    public OperatingFeeController() {
    }
    
    public OperatingFeeController(OperatingFee fee, FileController filecontroller){
        this.fee = fee;
        this.fileController = filecontroller;
    }
    
    // Phương thức get Fees từ File 
    public List<OperatingFee> readOperatingFeeFromFile(String fileName) throws IOException{
        fileController.OpenFileToRead(fileName);
        List<OperatingFee> listFee = new ArrayList<OperatingFee>();
        
        while(fileController.scanner.hasNext()){
            String data = fileController.scanner.nextLine();
            String[] a = data.split("\\|");
            listFee.add(new OperatingFee(Long.parseLong(a[0]), a[1], Long.parseLong(a[2]), Long.parseLong(a[3])));
        }
        
        fileController.CloseFileAfterRead(fileName);
        
        return listFee;
    }
    
    // 
    public List<OperatingFee> writeOperatingFeeToFile(List<OperatingFee> listFee, String filename) throws IOException{
        fileController.OpenFileToWrite(filename);
        for(OperatingFee fee : listFee){
            fileController.getPrintWriter().println(fee.getId() + "|" + fee.getNameFee() + "|" + fee.getMoney() + "|" + fee.getIdEvent());
        }
        
        fileController.CloseFileAfterWrite();
        return listFee;
    }
    
    public void closeOperatingFeeAfterRead(String file){
        fileController.CloseFileAfterRead(file);
    }
    
    // Get ra list Operating Fee từ File Opearting;
    public ArrayList<OperatingFee> getListCost() throws IOException{
        ArrayList<OperatingFee> listCost = (ArrayList<OperatingFee>) readOperatingFeeFromFile(Constant.OPERATINGFEE_FILE);
        return listCost;
    }
    
    // Add Operating Fee
    public boolean addOperatingFee(String nameCost, long cost, String nameEvent) throws Exception{
        ArrayList<OperatingFee> listOperatingFee =(ArrayList<OperatingFee>) readOperatingFeeFromFile(Constant.OPERATINGFEE_FILE);
        ArrayList<Event> listEvent = (ArrayList< Event>)eventController.readEventsFromFile(Constant.EVENT_FILE);
        long idOF = listOperatingFee.size() + 1;
        long idEvent = -1;
        for(Event event : listEvent){
            if(event.getNameEvent().equalsIgnoreCase(nameEvent)){
                idEvent = event.getId();
            }
        }
        OperatingFee of = new OperatingFee(idOF, nameCost, cost, idEvent);
        listOperatingFee.add(of);
        
        // ghi vao file
        writeOperatingFeeToFile(listOperatingFee, Constant.OPERATINGFEE_FILE);
        return true;
    }
    
    public OperatingFee getOperatingFee(long idOF) throws Exception{
        ArrayList<OperatingFee> listOFs = getListCost();
        for(OperatingFee of : listOFs){
            if(of.getId() == idOF){
                return of;
            }
        }
        return null;
    }
    
    public boolean repairOperatingFee(long iDOF, String nameCost, long cost, String nameEvent) throws Exception{
        ArrayList<OperatingFee> listOperatingFee = getListCost();
        ArrayList<Event> listEvent = eventController.getListEvents();
        long idEvent = -1;
        for(Event e : listEvent){
            if(e.getNameEvent().equalsIgnoreCase(nameEvent)){
                idEvent = e.getId();
            }
        }
        for(OperatingFee fee : listOperatingFee){
            if(fee.getId() == iDOF){
                fee.setNameFee(nameCost);
                fee.setMoney(cost);
                fee.setIdEvent(idEvent);
                writeOperatingFeeToFile(listOperatingFee, Constant.OPERATINGFEE_FILE);
                return true;
            }
        }
        
        return false;
    }
}
