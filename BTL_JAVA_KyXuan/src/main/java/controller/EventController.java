/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Event;

/**
 *
 * @author linhc
 */
public class EventController {
    // 1 đối tượng Event nhằm chứa dữ liệu Event để sử lý
    private Event event = new Event();
    // 1 đối tượng FileController để ta có thể thao tác với tệp
    private FileController fileController = new FileController();

    // Các phương thức khởi tạo có đối và không đối
    public EventController() {
    }
    
    public EventController(Event event, FileController fileController){
        this.event = event;
        this.fileController = fileController;
    }
    
    // Method get Events từ File 
    public List<Event> readEventsFromFile(String fileName) throws IOException, ParseException{
        fileController.OpenFileToRead(fileName);
        List<Event> listEvents = new ArrayList<>();
        // Định nghĩa chuỗi qua đối tượng Date
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        while(fileController.scanner.hasNext()){
            String data = fileController.scanner.nextLine();
            String[] arrData = data.split("\\|");
            listEvents.add(new Event(Long.parseLong(arrData[0]), arrData[1], format.parse(arrData[2]),format.parse(arrData[3]), Integer.parseInt(arrData[4]), arrData[5]));
        }
        
        fileController.CloseFileAfterRead(fileName);
        
        return listEvents;
    }
    
}
