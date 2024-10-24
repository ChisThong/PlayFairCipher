/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Point;

public class PlayfairModel {
    private String[][] table;  // Bảng 5x5 để lưu trữ các ký tự mã hóa
    private String keyword;    // Từ khóa để xây dựng bảng mã hóa
    private String text;       // Văn bản cần mã hóa hoặc giải mã

    // Getter cho bảng mã hóa
    public String[][] getTable() {
        return this.table;
    }

    // Getter cho từ khóa
    public String getKeyword() {
        return this.keyword;
    }

    // Setter cho từ khóa, chuyển đổi thành chữ hoa và loại bỏ các ký tự không phải chữ cái
    public void setKeyword(String keyword) {
        this.keyword = keyword.toUpperCase().replaceAll("[^A-Z]", "");
        setTable();  // Gọi phương thức để thiết lập lại bảng mã hóa
    }

    // Getter cho văn bản cần mã hóa/giải mã
    public String getText() {
        return this.text;
    }

    // Setter cho văn bản, chuyển đổi thành chữ hoa và loại bỏ các ký tự không phải chữ cái
    public void setText(String text) {
        this.text = text.toUpperCase().replaceAll("[^A-Z]", "");
    }

    // Phương thức thiết lập bảng mã hóa 5x5 từ từ khóa
    public void setTable() {
        table = new String[5][5];  // Tạo bảng mã hóa 5x5
        String key = keyword + "ABCDEFGHIKLMNOPQRSTUVWXYZ"; // Bỏ 'J' theo quy tắc Playfair
        boolean[] used = new boolean[26]; // Mảng để theo dõi ký tự đã sử dụng

        int index = 0;
        for (char c : key.toCharArray()) {
            if (c == 'J') c = 'I'; // Chuyển 'J' thành 'I' trong bảng mã hóa
            int pos = c - 'A'; // Tính chỉ số của ký tự trong bảng chữ cái
            if (!used[pos]) { // Nếu ký tự chưa được sử dụng
                used[pos] = true; // Đánh dấu ký tự đã sử dụng
                table[index / 5][index % 5] = String.valueOf(c); // Thêm ký tự vào bảng
                index++; // Tăng chỉ số
            }
        }
    }

    // Phương thức để mã hóa văn bản đã cho
    public String cipherPlaintext() {
        int length = text.length() / 2 + text.length() % 2; // Tính số cặp ký tự
        StringBuilder modifiedText = new StringBuilder(text); // Tạo một StringBuilder cho văn bản đã chỉnh sửa

        // Thêm 'X' nếu có ký tự trùng nhau
        for (int i = 0; i < length - 1; i++) {
            if (modifiedText.charAt(2 * i) == modifiedText.charAt(2 * i + 1)) {
                modifiedText.insert(2 * i + 1, 'X'); // Chèn 'X' vào giữa
                length = modifiedText.length() / 2 + modifiedText.length() % 2; // Cập nhật độ dài
            }
        }

        String[] digraphs = new String[length]; // Mảng chứa các cặp ký tự
        for (int j = 0; j < length; j++) {
            if (j == (length - 1) && modifiedText.length() / 2 == (length - 1))
                modifiedText.append("X"); // Nếu còn một ký tự lẻ, thêm 'X'
            digraphs[j] = modifiedText.charAt(2 * j) + "" + modifiedText.charAt(2 * j + 1); // Tạo cặp ký tự
        }

        StringBuilder out = new StringBuilder(); // Tạo một StringBuilder để lưu trữ kết quả
        String[] encDigraphs = encodeDigraph(digraphs); // Mã hóa các cặp ký tự
        for (String s : encDigraphs) {
            out.append(s); // Thêm kết quả vào StringBuilder
        }
        return out.toString(); // Trả về chuỗi đã mã hóa
    }

    // Phương thức để mã hóa các cặp ký tự
    private String[] encodeDigraph(String[] di) {
        String[] enc = new String[di.length]; // Mảng để lưu trữ kết quả mã hóa
        for (int i = 0; i < di.length; i++) {
            char a = di[i].charAt(0); // Ký tự đầu tiên của cặp
            char b = di[i].charAt(1); // Ký tự thứ hai của cặp
            Point p1 = getPoint(a); // Lấy vị trí của ký tự 'a' trong bảng
            Point p2 = getPoint(b); // Lấy vị trí của ký tự 'b' trong bảng

            // Kiểm tra các trường hợp vị trí của ký tự
            if (p1.x == p2.x) { // Cùng hàng
                enc[i] = table[p1.x][(p1.y + 1) % 5] + "" + table[p2.x][(p2.y + 1) % 5]; // Dịch phải
            } else if (p1.y == p2.y) { // Cùng cột
                enc[i] = table[(p1.x + 1) % 5][p1.y] + "" + table[(p2.x + 1) % 5][p2.y]; // Dịch xuống
            } else { // Khác hàng, khác cột
                enc[i] = table[p1.x][p2.y] + "" + table[p2.x][p1.y]; // Hoán đổi cột
            }
        }
        return enc; // Trả về mảng đã mã hóa
    }

    // Phương thức để giải mã chuỗi đã mã hóa
    public String decode(String out) {
        StringBuilder decoded = new StringBuilder(); // Tạo StringBuilder để lưu trữ kết quả giải mã
        for (int i = 0; i < out.length() / 2; i++) {
            char a = out.charAt(2 * i); // Ký tự đầu tiên của cặp
            char b = out.charAt(2 * i + 1); // Ký tự thứ hai của cặp
            Point p1 = getPoint(a); // Lấy vị trí của ký tự 'a' trong bảng
            Point p2 = getPoint(b); // Lấy vị trí của ký tự 'b' trong bảng

            // Kiểm tra các trường hợp vị trí của ký tự
            if (p1.x == p2.x) { // Cùng hàng
                decoded.append(table[p1.x][(p1.y + 4) % 5]); // Dịch trái
                decoded.append(table[p2.x][(p2.y + 4) % 5]); // Dịch trái
            } else if (p1.y == p2.y) { // Cùng cột
                decoded.append(table[(p1.x + 4) % 5][p1.y]); // Dịch lên
                decoded.append(table[(p2.x + 4) % 5][p2.y]); // Dịch lên
            } else { // Khác hàng, khác cột
                decoded.append(table[p1.x][p2.y]); // Hoán đổi cột
                decoded.append(table[p2.x][p1.y]); // Hoán đổi cột
            }
        }
        return decoded.toString(); // Trả về chuỗi đã giải mã
    }

    // Phương thức để lấy tọa độ (vị trí) của một ký tự trong bảng mã hóa
    private Point getPoint(char c) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (c == table[i][j].charAt(0)) { // Kiểm tra nếu ký tự là một phần của bảng
                    return new Point(i, j); // Trả về tọa độ nếu tìm thấy
                }
            }
        }
        return null; // Trả về null nếu ký tự không tìm thấy
    }
}
