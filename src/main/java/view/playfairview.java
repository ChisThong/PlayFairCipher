package view;

import controller.PlayfairAction;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import model.PlayfairModel;

// Lớp PlayfairView đại diện cho giao diện người dùng của thuật toán mã hóa Playfair
public class playfairview extends JFrame {
    private PlayfairModel playfairModel; // Mô hình Playfair
    private JPanel playfairPanel; // Bảng chứa các thành phần giao diện
    private JTextField keywordTextField; // Ô nhập từ khóa
    private JTextField ePlainTextField; // Ô nhập văn bản gốc để mã hóa
    private JTextField eCipherTextField; // Ô xuất văn bản đã mã hóa
    private JTextField dCipherTextField; // Ô nhập văn bản đã mã hóa để giải mã
    private JTextField dPlainTextField; // Ô xuất văn bản đã giải mã

    // Constructor khởi tạo
    public playfairview() throws HeadlessException {
        this.playfairModel = new PlayfairModel(); // Khởi tạo mô hình Playfair
        this.init(); // Gọi phương thức khởi tạo giao diện
    }

    // Phương thức khởi tạo giao diện
    public void init() {
        this.setTitle("Playfair Encrypt and Decrypt"); // Thiết lập tiêu đề cửa sổ
        this.setSize(500, 300); // Thiết lập kích thước cửa sổ
        this.setLocationRelativeTo(null); // Đặt vị trí cửa sổ ở giữa màn hình
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Đóng ứng dụng khi cửa sổ đóng
        Border border = BorderFactory.createLineBorder(Color.gray); // Tạo đường viền cho các panel
        PlayfairAction playfairAction = new PlayfairAction(this); // Khởi tạo hành động cho Playfair
        
        // Tạo bảng chứa các thành phần giao diện
        playfairPanel = new JPanel();
        playfairPanel.setBorder(new EmptyBorder(5, 5, 5, 5)); // Đặt lề cho bảng
        this.setContentPane(playfairPanel); // Đặt bảng làm nội dung cho cửa sổ
        playfairPanel.setLayout(new GridLayout(0, 1, 0, 0)); // Thiết lập layout cho bảng
        
        // Tạo panel chính
        JPanel mainPanel = new JPanel();
        playfairPanel.add(mainPanel);
        mainPanel.setLayout(new BorderLayout(10, 10)); // Thiết lập layout cho panel chính
        
        // Tạo panel cho tiêu đề
        JPanel namePanel = new JPanel();
        namePanel.setBorder(border); // Đặt đường viền cho panel
        mainPanel.add(namePanel, BorderLayout.NORTH); // Thêm vào panel chính
        JLabel playfairCipherLabel = new JLabel("Playfair Cipher"); // Nhãn tiêu đề
        namePanel.add(playfairCipherLabel);

        // Tạo panel biên
        JPanel borderPanel = new JPanel();
        borderPanel.setBorder(border); // Đặt đường viền cho panel biên
        mainPanel.add(borderPanel, BorderLayout.CENTER); // Thêm vào panel chính
        borderPanel.setLayout(new BorderLayout(0, 0)); // Thiết lập layout cho panel biên

        // Tạo panel nhập từ khóa
        JPanel keyGenPanel = new JPanel();
        borderPanel.add(keyGenPanel, BorderLayout.NORTH); // Thêm vào panel biên

        JLabel keywordLabel = new JLabel("Keyword"); // Nhãn cho từ khóa
        keyGenPanel.add(keywordLabel);

        keywordTextField = new JTextField(10); // Ô nhập từ khóa
        keyGenPanel.add(keywordTextField);

        // Tạo panel cho mã hóa
        JPanel cipherPanel = new JPanel();
        borderPanel.add(cipherPanel, BorderLayout.CENTER);
        cipherPanel.setLayout(new GridLayout(0, 2, 10, 10)); // Thiết lập layout cho panel mã hóa

        // Panel mã hóa
        JPanel encryptPanel = new JPanel();
        cipherPanel.add(encryptPanel);
        encryptPanel.setLayout(new BorderLayout(10, 10)); // Thiết lập layout cho panel mã hóa

        JLabel eLabel = new JLabel("Encrypt"); // Nhãn cho phần mã hóa
        eLabel.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa nhãn
        encryptPanel.add(eLabel, BorderLayout.NORTH); // Thêm vào panel mã hóa

        // Panel cho văn bản mã hóa
        JPanel eTextPanel = new JPanel();
        encryptPanel.add(eTextPanel, BorderLayout.CENTER); // Thêm vào panel mã hóa
        eTextPanel.setLayout(new GridLayout(2, 2, 10, 10)); // Thiết lập layout cho panel văn bản mã hóa

        JLabel ePlainLabel = new JLabel("Plain Text"); // Nhãn cho văn bản gốc
        ePlainLabel.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa nhãn
        eTextPanel.add(ePlainLabel); // Thêm vào panel văn bản mã hóa

        ePlainTextField = new JTextField(); // Ô nhập văn bản gốc
        ePlainTextField.setColumns(50); // Đặt số cột cho ô nhập
        eTextPanel.add(ePlainTextField); // Thêm vào panel văn bản mã hóa

        JLabel eCipherLabel = new JLabel("Cipher Text:"); // Nhãn cho văn bản đã mã hóa
        eCipherLabel.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa nhãn
        eTextPanel.add(eCipherLabel); // Thêm vào panel văn bản mã hóa

        eCipherTextField = new JTextField(); // Ô xuất văn bản đã mã hóa
        eCipherTextField.setColumns(50); // Đặt số cột cho ô xuất
        eTextPanel.add(eCipherTextField); // Thêm vào panel văn bản mã hóa

        JPanel eButtonPanel = new JPanel(); // Panel cho các nút
        encryptPanel.add(eButtonPanel, BorderLayout.SOUTH); // Thêm vào panel mã hóa

        JButton eEncryptButton = new JButton("Encrypt"); // Nút mã hóa
        eButtonPanel.add(eEncryptButton); // Thêm vào panel nút
        eEncryptButton.addActionListener(playfairAction); // Thêm hành động cho nút

        JButton eSetButton = new JButton("Set Keyword"); // Nút thiết lập từ khóa
        eButtonPanel.add(eSetButton); // Thêm vào panel nút
        eSetButton.addActionListener(playfairAction); // Thêm hành động cho nút

        // Panel giải mã
        JPanel decryptPanel = new JPanel();
        cipherPanel.add(decryptPanel); // Thêm vào panel mã hóa
        decryptPanel.setLayout(new BorderLayout(10, 10)); // Thiết lập layout cho panel giải mã

        JLabel dLabel = new JLabel("Decrypt"); // Nhãn cho phần giải mã
        dLabel.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa nhãn
        decryptPanel.add(dLabel, BorderLayout.NORTH); // Thêm vào panel giải mã

        JPanel dTextPanel = new JPanel(); // Panel cho văn bản giải mã
        decryptPanel.add(dTextPanel, BorderLayout.CENTER); // Thêm vào panel giải mã
        dTextPanel.setLayout(new GridLayout(2, 2, 10, 10)); // Thiết lập layout cho panel văn bản giải mã

        JLabel dCipherLabel = new JLabel("Cipher Text:"); // Nhãn cho văn bản đã mã hóa
        dCipherLabel.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa nhãn
        dTextPanel.add(dCipherLabel); // Thêm vào panel văn bản giải mã

        dCipherTextField = new JTextField(); // Ô nhập văn bản đã mã hóa
        dTextPanel.add(dCipherTextField); // Thêm vào panel văn bản giải mã
        dCipherTextField.setColumns(10); // Đặt số cột cho ô nhập

        JLabel dPlainLabel = new JLabel("Plain Text"); // Nhãn cho văn bản đã giải mã
        dPlainLabel.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa nhãn
        dTextPanel.add(dPlainLabel); // Thêm vào panel văn bản giải mã

        dPlainTextField = new JTextField(); // Ô xuất văn bản đã giải mã
        dPlainTextField.setColumns(10); // Đặt số cột cho ô xuất
        dTextPanel.add(dPlainTextField); // Thêm vào panel văn bản giải mã

        JPanel dButtonPanel = new JPanel(); // Panel cho các nút giải mã
        decryptPanel.add(dButtonPanel, BorderLayout.SOUTH); // Thêm vào panel giải mã

        JButton dDecryptButton = new JButton("Decrypt"); // Nút giải mã
        dButtonPanel.add(dDecryptButton); // Thêm vào panel nút
        dDecryptButton.addActionListener(playfairAction); // Thêm hành động cho nút

        this.setVisible(true); // Hiển thị cửa sổ
    }

    // Getter và Setter cho các thành phần giao diện
    public JPanel getplayfairPanel() {
        return playfairPanel;
    }

    public JTextField getKeywordTextField() {
        return keywordTextField;
    }

    public void setKeywordTextField(String str) {
        this.keywordTextField.setText(str); // Đặt giá trị cho ô nhập từ khóa
    }

    public JTextField getdCipherTextField() {
        return dCipherTextField;
    }

    public void setdCipherTextFiedl(String str) {
        this.dCipherTextField.setText(str); // Đặt giá trị cho ô nhập văn bản đã mã hóa
    }

    public PlayfairModel getplayfairModel() {
        return playfairModel;
    }

    public void setplayfairModel(PlayfairModel cipherModel) {
        this.playfairModel = cipherModel; // Đặt mô hình Playfair
    }

    public JTextField getePlainTextField() {
        return ePlainTextField;
    }

    public void setePlainTextField(JTextField ePlainTextField) {
        this.ePlainTextField = ePlainTextField; // Đặt ô nhập văn bản gốc
    }

    public JTextField geteCipherTextField() {
        return eCipherTextField;
    }

    public void seteCipherTextField(String str) {
        this.eCipherTextField.setText(str); // Đặt giá trị cho ô xuất văn bản đã mã hóa
    }

    public void setKeywordTextField(JTextField shiftKeyTextField) {
        this.keywordTextField = shiftKeyTextField; // Đặt ô nhập từ khóa
    }

    public JTextField getdPlainTextField() {
        return dPlainTextField;
    }

    public void setdPlainTextField(String str) {
        this.dPlainTextField.setText(str); // Đặt giá trị cho ô xuất văn bản đã giải mã
    }

    // Phương thức mã hóa
    public String Encrypt() {
        String Key = this.getKeywordTextField().getText().toUpperCase().replaceAll("[^A-Z]", ""); // Lấy từ khóa
        String plaintext = this.getePlainTextField().getText().toUpperCase().replaceAll("[^A-Z]", ""); // Lấy văn bản gốc
        this.playfairModel.setKeyword(Key); // Đặt từ khóa cho mô hình
        this.playfairModel.setText(plaintext); // Đặt văn bản gốc cho mô hình
        this.playfairModel.setTable(); // Thiết lập bảng mã
        String cipherText = this.playfairModel.cipherPlaintext(); // Mã hóa văn bản gốc
        return cipherText; // Trả về văn bản đã mã hóa
    }

    // Phương thức giải mã
    public String Decrypt() {
        String KeyWword = this.getKeywordTextField().getText().toUpperCase().replaceAll("[^A-Z]", ""); // Lấy từ khóa
        this.playfairModel.setKeyword(KeyWword); // Đặt từ khóa cho mô hình
        String Cipher = this.getdCipherTextField().getText(); // Lấy văn bản đã mã hóa
        String Plain = this.playfairModel.decode(Cipher); // Giải mã văn bản đã mã hóa
        return Plain; // Trả về văn bản đã giải mã
    }

    // Phương thức thiết lập văn bản đã mã hóa
    public String Set() {
        String cipher = this.geteCipherTextField().getText().toString(); // Lấy văn bản đã mã hóa
        return cipher; // Trả về văn bản đã mã hóa
    }
}