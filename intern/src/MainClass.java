import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

// Kelas MainClass turunan dari kelas AcakKata
public class MainClass extends AcakKata implements ActionListener, KeyListener
{

    // Deklarasi variabel 
    private JPanel PanelMain; // untuk panel
    private JButton Btn1; // menampilkan tombol 1
    private JButton Btn2; // menampilkan tombol 2
    private JButton Btn3; // menampilkan tombol 3
    private JButton Btn4; // menampilkan tombol 4
    private JButton Btn5; // menampilkan tombol 5
    private JButton Btn6; // menampilkan tombol 6
    private JButton Btn7; // menampilkan tombol 7
    private JButton Btn8; // menampilkan tombol 8
    private JButton Btn9; // menampilkan tombol 9
    private JButton Btn10; // menampilkan tombol 10
    private JButton BtnInfo; // menampilkan tombol info game
    private JButton BtnTemp; // tombol kosong 
    private JButton BtnGanti; // tombol ganti 
    private JLabel LblCek; // menampilkan acak huruf
    private JLabel LblLevel; // menampilkan level sekarang
    private JLabel LblHealth; // menampilkan health sekarang
    private JLabel LblRand; // menampilakan label penggeseran
    private JLabel Line1, Line2; // menampilakan garis
    
    // konstruktor MainClass
    public MainClass(int level, int health, String title){
        // memanggil konstruktor super class
        super(level, health); 
        PanelMain = new JPanel();
        Btn1 = new JButton();
        Btn2 = new JButton();
        Btn3 = new JButton();
        Btn4 = new JButton();
        Btn5 = new JButton();
        Btn6 = new JButton();
        Btn7 = new JButton();
        Btn8 = new JButton();
        Btn9 = new JButton();
        Btn10 = new JButton();
        BtnInfo = new JButton();
        BtnTemp = new JButton();
        BtnGanti = new JButton();
        Line1 = new JLabel();
        Line2 = new JLabel();
        LblCek = new JLabel();
        LblRand = new JLabel();
        LblLevel = new JLabel();
        LblHealth = new JLabel();
        
        // method initFrameComponents untuk menampilkan frame dan komponen game
        initFrameComponents(title);
    }
    
    // method initFrameComponents()
    public void initFrameComponents(String title){
        JFrame frame = new JFrame();
        // memberi judul game
        frame.setTitle(title);
        // keluar dari game
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // set ukuran sesuai konstanta WIDTH dan HEIGHT
        frame.setSize(WIDTH, HEIGHT);
        // terlihat
        frame.setVisible(true);
        // tidak bisa di ubah ukuran windownya
        frame.setResizable(false);
        // set posisi game ke tengah window
        frame.setLocationRelativeTo(null);
        // frame di masukkan ke kontainer
        frame.getContentPane().setLayout(null);
        
        // set warna background
        PanelMain.setBackground(new Color(153, 153, 255));
        // set Layout = null
        PanelMain.setLayout(null);
        // set ukuran Panel Main
        PanelMain.setBounds(0, 0, WIDTH, HEIGHT);
        // tambahkan Panel Main ke kontainer
        frame.getContentPane().add(PanelMain);
        

        //layout
        PanelMain.add(LblLevel); // menambahkan label level
        PanelMain.add(LblHealth); // menambahkan label health
        PanelMain.add(Line1);  // menambahkan garis
        PanelMain.add(LblCek);  // menambahkan label pengacakan huruf
        PanelMain.add(LblRand); // menambahkan penggeseran huruf
        PanelMain.add(Line2); // menambahkan garis
        PanelMain.add(BtnInfo); // menambahkan tombol info game
        PanelMain.add(BtnGanti); // menambahkan tombol ganti kata acak
        for(int i = 0; i < 10; i++){// perulangan untuk menampilakan tombol 1 - 10
            switchBut(i); // memanggil method switchBut
            PanelMain.add(BtnTemp); // menambahkan hasil perulangan  ke panel
        }
        
        // set text tombol info
        BtnInfo.setText("Info");
        // set ukuran dan posisi tombol
        BtnInfo.setBounds(210, 185, 100, 30);
        // tambahkan Action Listener
        BtnInfo.addActionListener(this);
        
        // letak dan ukuran komponen
        BtnGanti.setText("[   Ganti   ]");
        BtnGanti.setBounds(410, 185, 90, 30);
        BtnGanti.addActionListener(this);
        
        LblLevel.setFont(new Font("Tahoma", 1, 15));
        LblLevel.setBounds(10, 10, 100, 30);
        
        LblHealth.setFont(new Font("Tahoma", 1, 15));
        LblHealth.setBounds(410, 10, 100, 30);
        
        Line1.setText(getLine()); // menampilkan garis
        Line1.setFont(new Font("Tahoma", 1, 15));
        Line1.setBounds(10, 40, WIDTH, 10);
        
        Line2.setText(getLine()); // menampilkan garis
        Line2.setFont(new Font("Tahoma", 1, 15));
        Line2.setBounds(10, 175, WIDTH, 10);
        
        LblCek.setFont(new Font("Tahoma", 1, 20));
        LblCek.setBounds(25, 135, 200, 40);
        
        LblRand.setFont(new Font("Tahoma", 1, 15));
        LblRand.setBounds(10, 190, 200, 20);

        // memanggil method buttonComponent
        // untuk set letak dan lain-lain
        buttonComponent();
        // memanggil method initComponent
        // untuk memberi nilai
        initComponents();
    }
    
    // method buttonComponent
    // untuk menambahkan tombol 1 - 10 dan aksinya
    public void buttonComponent(){
        int xx = 90;
        int yy = 30;
        int idx = 0;
        
        for(int y = 60; y <= 100; y += 40){
            for(int x = 10; x <= 410; x += 100, idx++){
                switchBut(idx);
                BtnTemp.setBounds(x, y, xx, yy);
                BtnTemp.addActionListener(this);
            }
        }
    }
    
    // method initComponents
    // method ini akan selalu dipanggil
    // jika naik level, salah tombol dan lain-lain
    public void initComponents(){
        cekChoice();
        setComponent();
    }

    // method cekChoice() untuk mengecek pilihan
    // method ini akan dieksekusi jika game selesai
    public void cekChoice(){
        // jika level == 10 atau health == 0 maka
        if((getLevel() > kata.length*2) || (getHealth() < 1)){
            // lakukan perulangan jika user memilih opsi cancel atau close
            for(int cek = JOptionPane.CANCEL_OPTION;
            cek == JOptionPane.CANCEL_OPTION || cek == JOptionPane.CLOSED_OPTION;){
                cek = JOptionPane.showConfirmDialog(null, "Restart?", 
                        "Game Selesai", JOptionPane.YES_NO_CANCEL_OPTION);
                    // jika opsi ya maka
                    if(cek == JOptionPane.YES_OPTION){
                        setLevel(1); // set level = 1
                        setHealth(3); // set health = 3
                        setCek(true); // set peringatan = true
                        setCheatLevel(true); // set cheat level = true
                        setCheatHealth(true); // set cheat health = true
                    // jika opsi tidak maka
                    } else if(cek == JOptionPane.NO_OPTION){
                        // tampilkan pesan
                        JOptionPane.showMessageDialog(null, "Terima kasih telah bermain :)", 
                                "Acak Kata Game", JOptionPane.INFORMATION_MESSAGE);
                        // keluar dari game
                        System.exit(0);
                    }
            }
        }
    }
    
    // method setComponent()
    public void setComponent(){
        int levelIdx = 1;
        
        // jika level > 5 maka
        if(getLevel() > 5){
            // jika peringatan belum ditampilkan maka
            if(isCek()){
                JOptionPane.showMessageDialog(null, "Untuk level 6 ke atas ditambah penyandian huruf !!");
                setCek(false);
            }
            
            // lakukan penyandian huruf
            levelIdx = 6;
            setCurrIdx(1000);
            LblRand.setText("Geser ke "+ getMovePos() + " " + getRandIdx());
        // jika tidak label penyandian = null
        } else {
            LblRand.setText(null);
        }
        
        // mengambil array di level sekarang
        setCurrIdx(kata[getLevel() - levelIdx]);
        // mengacak kata yang akan ditampilkan
        setKataAcak(kata[getLevel() - levelIdx][getCurrIdx()]);
        // mengacak posisi huruf
        setAcakHuruf(getKataAcak());
        // penggeseran huruf
        setCurrIdx(getKataAcak());
        
        // menampilkan level sekarang
        LblLevel.setText("Level : " + String.valueOf(getLevel()));
        // menampilkan health sekarang
        LblHealth.setText("Health : " + String.valueOf(getHealth()));
        // tampilkan huruf acak
        LblCek.setText(getAcakHuruf());
        
        // menampilkan kata acak di tombol untuk level sekarang
        for(int i = 0; i < 10; i++){
            switchBut(i);
            BtnTemp.setText(getArrAcak()[i]);
        }
    }    
    
    // method switchBut akan mengecek tombol yang ditekan
    public void switchBut(int i){
        switch(i){
            case 0: BtnTemp = Btn1; break;
            case 1: BtnTemp = Btn2; break;
            case 2: BtnTemp = Btn3; break;
            case 3: BtnTemp = Btn4; break;
            case 4: BtnTemp = Btn5; break;
            case 5: BtnTemp = Btn6; break;
            case 6: BtnTemp = Btn7; break;
            case 7: BtnTemp = Btn8; break;
            case 8: BtnTemp = Btn9; break;
            case 9: BtnTemp = Btn10; break;
        }
    }

    // method test untuk aksi jika tombol 1 - 10 ditekan
    public void test(JButton btn){
        // jika tombol == kata yang di acak maka
        if(btn.getText().equals(getKataAcak())){
            // tampilkan pesan benar
            JOptionPane.showMessageDialog(null, "Tebakan Benar"
            ,"Acak Kata Game", JOptionPane.INFORMATION_MESSAGE);
            // level naik 1
            setLevel(getLevel() + 1);
            // panggil method initComponents
            initComponents();
        // jika tidak sama maka
        } else {
            // tampilkan pesan salah dan tampilakan kata yang benar
            JOptionPane.showMessageDialog(null, "Tebakan Salah\n" 
            + "--------------------------------------\nKata Acak : " + getKataAcak()
            , "Acak Kata Game", JOptionPane.ERROR_MESSAGE);
            // health - 1
            setHealth(getHealth() - 1);
            // panggil method initComponents
            initComponents();
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent evt){
        for(int i = 0; i < 10; i++){
            // method switchBut akan mengecek tombol yang ditekan
            // gunakan perulangan untuk mengecek 1 per 1
            // hasil tombol yang ditekan dimisalkan BtnTemp
            switchBut(i);
            // pengecekan tombol
            if(evt.getSource() == BtnTemp)
                // panggil method test
                test(BtnTemp);
        }
        
        // Jika tombol info ditekan
        // tampilkan informasi game
        if(evt.getSource() == BtnInfo) {
            JOptionPane.showMessageDialog(null, "Permainan ini terdapat health dimana health digunakan untuk sebagai kesempatan bermain.\n"+
            "Dimana setiap pemain diberikan 3 health.\n" + 
            "Untuk setiap tebakan hanya diberikan satu kali menjawab.\n" +
            "Selamat bermain :)", "Acak Kata Game", JOptionPane.INFORMATION_MESSAGE);
        }
        
        // Jika tombol ganti ditekan
        // initComponents
        if(evt.getSource() == BtnGanti){
            initComponents();
        }
    }
    
    @Override
    public void keyPressed(KeyEvent evt) {
         
    }

    @Override public void keyReleased(KeyEvent evt) {}
    @Override public void keyTyped(KeyEvent evt) {}
    
    public static void main(String[] args){
        // jika diperlukan set menjadi true
        JFrame.setDefaultLookAndFeelDecorated(false);
        // memberi nilai awal konstruktor
        new MainClass(1, 3, "Acak Kata Game");
    }
}