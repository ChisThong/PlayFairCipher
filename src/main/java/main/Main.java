/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import view.playfairview; // Nhập khẩu lớp view.playfairview

public class Main {
    public static void main(String[] args) {
        // Thiết lập Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // Sử dụng Look and Feel của hệ thống
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace(); // In ra lỗi nếu có
        }

        // Khởi chạy giao diện người dùng Playfair Cipher
        new playfairview(); // Khởi tạo giao diện Playfair
    }
}
