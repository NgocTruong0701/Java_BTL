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
public class EventController {

    // 1 đối tượng Event nhằm chứa dữ liệu Event để sử lý
    private Event event = new Event();
    // 1 đối tượng FileController để ta có thể thao tác với tệp
    private FileController fileController = new FileController();

    // Các phương thức khởi tạo có tham và không tham số
    public EventController() {
    }

    public EventController(Event event, FileController fileController) {
        this.event = event;
        this.fileController = fileController;
    }

    // Method get Events từ File 
    public List<Event> readEventsFromFile(String fileName) throws IOException, Exception {
        fileController.OpenFileToRead(fileName);
        List<Event> listEvents = new ArrayList<>();

        while (fileController.scanner.hasNext()) {
            String data = fileController.scanner.nextLine();
            String[] arrData = data.split("\\|");
            listEvents.add(new Event(Long.parseLong(arrData[0]), arrData[1], arrData[2], arrData[3], Integer.parseInt(arrData[4]), arrData[5]));
        }

        fileController.CloseFileAfterRead(fileName);

        return listEvents;
    }

    // Method ghi Event vào file
    public List<Event> writeEventToFile(List<Event> listEvent, String fileName) throws IOException, Exception {
        fileController.OpenFileToWrite(fileName);
        for (Event e : listEvent) {
            fileController.getPrintWriter().println(e.getId() + "|" + e.getNameEvent() + "|" + e.getStartDay() + "|" + e.getEndDay() + "|" + e.getNumberOfStudent() + "|" + e.getAddress());
        }

        fileController.CloseFileAfterWrite();
        return listEvent;
    }

    public void closeEventAfterRead(String file) {
        fileController.CloseFileAfterWrite();
    }

    // Get ra list Event trong File Event
    public ArrayList<Event> getListEvents() throws IOException, Exception {
        ArrayList<Event> listEvent = (ArrayList<Event>) readEventsFromFile(Constant.EVENT_FILE);
        return listEvent;
    }

    // set list OperatingFee cho event có idEvent trùng với idEvent bên OpeatingFee
    public void setListOperatingFee(Event e) throws IOException {
        // 1 đối tượng OperatingFeeController để ta có thể thao tác với dữ liệu phí hoạt động
        OperatingFeeController operatingFeeController = new OperatingFeeController();
        ArrayList<OperatingFee> listOperatingFee = operatingFeeController.getListCost();
        ArrayList<OperatingFee> listCostOfEvent = new ArrayList<>();
        for (OperatingFee fee : listOperatingFee) {
            if (fee.getIdEvent() == e.getId()) {
                listCostOfEvent.add(fee);
            }
        }
        e.setOperatingFees(listCostOfEvent);
    }

    // Get Cost Total của Event đó
    public long getCostTotal(Event e) {
        long totalCost = 0;

        ArrayList<OperatingFee> dsFee = (ArrayList<OperatingFee>) e.getOperatingFees();
        for (OperatingFee fee : dsFee) {
            totalCost += fee.getMoney();
        }
        return totalCost;
    }

    public Event getEvent(long idEvent) throws Exception {
//        System.out.println(idEvent);
        ArrayList<Event> listEvent = (ArrayList<Event>) readEventsFromFile(Constant.EVENT_FILE);
        for (Event e : listEvent) {
            if (e.getId() == idEvent) {
                return e;
            }
        }
        return null;
    }

    public Boolean createEvent(String nameEvent, String startDay, String endDay, Integer numberOfStudent, String address) throws Exception {
        List<Event> events = readEventsFromFile(Constant.EVENT_FILE);
        Integer id = events.size();
        long idnew = id + 1;
        Event event = new Event(idnew, nameEvent, startDay, endDay, numberOfStudent, address);

        events.add(event);
        writeEventToFile(events, Constant.EVENT_FILE);
        return true;
    }
    
    public Boolean repairEvent(long idEvent, String nameEvent, String startDay, String endDay, Integer numberOfStudent, String address) throws Exception {
        List<Event> events = readEventsFromFile(Constant.EVENT_FILE);
        
        for (int i=0; i<events.size(); i++) {
            if (events.get(i).getId().equals(idEvent)) {
                Event event = events.get(i);
                event.setAddress(address);
                event.setEndDay(endDay);
                event.setNameEvent(nameEvent);
                event.setNumberOfStudent(numberOfStudent);
                event.setStartDay(startDay);
                
                writeEventToFile(events, Constant.EVENT_FILE);
                return true;
            }
        }
        
        return false;
    }
    
}
