/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.playfairview;


public class PlayfairAction implements ActionListener {
    private playfairview Playfairview; // Tham chiếu đến lớp Playfairview

    // Constructor nhận vào đối tượng Playfairview
    public PlayfairAction(playfairview Playfairview) {
        this.Playfairview = Playfairview; // Gán tham chiếu Playfairview
    }
    
    // Phương thức xử lý sự kiện khi nút được nhấn
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); // Lấy lệnh hành động từ sự kiện
        
        // Kiểm tra lệnh và thực hiện hành động tương ứng
        if (command.equals("Set Keyword")) { // Nếu lệnh là "Set Keyword"
            this.Playfairview.setdCipherTextFiedl(this.Playfairview.Set()); // Cài đặt văn bản mã hóa
        } else if (command.equals("Encrypt")) { // Nếu lệnh là "Encrypt"
            this.Playfairview.seteCipherTextField(this.Playfairview.Encrypt()); // Thực hiện mã hóa
        } else if (command.equals("Decrypt")) { // Nếu lệnh là "Decrypt"
            this.Playfairview.setdPlainTextField(this.Playfairview.Decrypt()); // Thực hiện giải mã
        }
    }
    
}
