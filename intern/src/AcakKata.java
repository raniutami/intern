import java.util.Random;

public class AcakKata 
{
    // Deklarasi dan inisialisasi variabel
    // rand dari kelas Random untuk variabel pengacakan
    Random rand = new Random();
    // konstanta WIDTH dan HEIGHT untuk ukuran layar game
    // konstanta LINE untuk mencetak garis
    public static final int WIDTH = 520, HEIGHT = 250, LINE = 82;
    // cek sebagai penanda peringatan jika level > 5
    // chLevel untuk penanda jika cheat level sudah digunakan
    // chHealth untuk penanda jika cheat health sudah digunakan
    protected boolean cek = true, chLevel = true, chHealth = true;
    // char charAcak[] untuk menyimpan pengacakan huruf 
    protected char[] charAcak;
    // idxA dan idxB untuk indeks acak
    // idx untuk menyimpan indeks kata yang akan diacak
    // idxRand untuk menyimpan banyak penggeseran huruf
    // pos untuk meggeser huruf ke kiri atau ke kanan
    // level untuk menyimpan level sekarang
    // health untuk menyimpan nyawa sekarang
    protected int idxA, idxB, idx, idxRand, pos, level, health;
    // strAcak untuk menyimpan kata acak
    protected String strAcak;
    // String arrAcak[] untuk menyimpan pengacakan posisi array kata
    protected String[] arrAcak;
    // cheat level dan health
    protected String cheatLevel = "level ups";
    protected String cheatHealth = "super boy";  
    
    // level 1 dan 6 : "data", "pointer", "struct", "queue", "stack", "list", "string", "array", "null", "memory"
    // level 2 dan 7 : "acak","kaca","batu","buta","buku", "kubu","dalang","ladang","pura","rupa"
    // level 3 dan 8 : "otak","kota","okta","ruas","rusa", "arus","busa","sabu","buas","usab"
    // level 4 dan 9 : "kasur","rusak","sukar","kuras","ruas", "arus","rusa","suar","kitab","bakti"
    // level 5 dan 10 : "rusak", "rakus", "kuras", "sukar", "kasur", "tutorial ", "trial out", "ratu lito", "tali tour", "roti laut"
    protected String[][] kata = {{"data", "pointer", "struct", "queue", "stack",
        "list", "string", "array", "null", "memory"},
        {"acak","kaca","batu","buta","buku",
        "kubu","dalang","ladang","pura","rupa"},
        {"otak","kota","okta","ruas","rusa",
        "arus","busa","sabu","buas","usab"},
        {"kasur","rusak","sukar","kuras","ruas",
        "arus","rusa","suar","kitab","bakti"},
        {"rusak", "rakus", "kuras", "sukar", "kasur",
        "tutorial ", "trial out", "ratu lito", "tali tour", "roti laut"}};
    
    
    // konstruktor kelas Anagram
    // memberi nilai awal level dan health
    public AcakKata(int level, int health){
        this.level = level;
        this.health = health;
    }
    
    // mengambil nilai index kata acak
    protected int getCurrIdx(){
        return idx;
    }
    
    // mencari kata yang akan diacak
    // simpan index array di var idx
    protected void setCurrIdx(String[] str){
        // memanggil method setArrAcak() untuk mengacak posisi kata
        setArrAcak(str);
        // pengacakan dimulai dari 0-10
        idx = rand.nextInt(str.length);
    }
    
    // method setArrAcak() untuk mengacak posisi kata 
    private void setArrAcak(String[] str){
        // simpan array untuk level sekarang di array arrAcak
        arrAcak = str;
        // memanggil method tukar
        tukar();
    }
    
    // memberikan array untuk level sekarang di array arrAcak
    protected String[] getArrAcak(){
        return arrAcak;
    }
    
    // memberikan banyak penggeseran huruf
    protected int getRandIdx(){
        return idxRand;
    }
    
    // method setCurrIdx untuk banyak penggeseran huruf
    protected void setCurrIdx(String str){
        // simpan hasil pengacakan di var idxRand
        idxRand = rand.nextInt(str.length());
    }
    
    // megambil posisi penggeseran
    protected String getMovePos(){
        // jika pos == 1
        // geser ke kiri
        if(pos == 1)
            return "kiri";
        // jika pos == 0
        // geser ke kanan
        else
            return "kanan";
    }
    
    // method setCurrIdx untuk menentukan arah penggeseran
    protected void setCurrIdx(int pos){
        // jika level > 7 maka
        // mengacak nilai pos kemudian dicari sisanya
        // jika sisa 1 maka akan ke kiri
        // jika sisa 0 maka akan ke kanan
        if(getLevel() > 7)
            this.pos = rand.nextInt(pos) % 2;
        // level <= 7 maka
        // sisa pembagian 0
        // dan akan geser ke kanan
        else
            this.pos = pos % 2;
    }
    
    // method setKataAcak untuk kata acak sekarang
    protected void setKataAcak(String strAcak){
        this.strAcak = strAcak;
    }
    
    // mengambil kata acak sekarang
    protected String getKataAcak(){
        return strAcak;
    }
    
    // mengambil hasil dari proses pengacakan huruf 
    protected String getAcakHuruf(){
        return String.valueOf(charAcak);
    }
    
    // mengacak huruf di kata acak sekarang
    protected void setAcakHuruf(String str){
        // charAcak memiliki panjang array sesuai panjang str
        charAcak = new char[str.length()];
        // array intAcak untuk menyimpan sementara banyak indeks penggeseran huruf
        int[] intAcak = new int[str.length()];
        
        // tambahan untuk mengecek method-method
        System.out.println("Level    : " + getLevel() + ", Health : " + getHealth());
        System.out.println("chLevel  : " + isCheatLevel() + ", chHealth : " + isCheatHealth());
        System.out.println("Rand idx : " + getRandIdx() + ", Rand pos : " + getMovePos());
        System.out.print("Array    : ");
        for(int i = 0; i < arrAcak.length; i++)
            System.out.print(arrAcak[i] + "   ");
        System.out.println();
        for(int i = 97; i < 123; i++)
            System.out.printf("%c ", i);
        System.out.println("\n");
        
        // perulangan untuk inisialisasi str ke array charAcak
        for(int i = 0; i < charAcak.length; i++){
            // Jika level sekarang > 5 maka
            // ditambah penyandian huruf
            //////////////////
            // Lihat ASCII
            // for(int i = 0; i < 255; i++){
            //    System.out.printf("%d : %c\n", i, i);
            // }

            if(getLevel() > 5){
                // Jika penggeseran ke kiri maka
                if(getMovePos().equals("kiri")){
                    // jika < 97(a) maka
                    // 123 dikurangi (a(97) dikurangi
                    // (ubah huruf sekarang ke integer
                    // dikurangi banyak penggeseran huruf))
                    if(((int)str.charAt(i) - getRandIdx()) < 97){
                        intAcak[i] = 123 - (97 - ((int)str.charAt(i) - getRandIdx()));
                    // jika tidak maka
                    // ubah huruf sekarang ke integer
                    // dikurangi banyak penggeseran huruf
                    } else {
                        intAcak[i] = (int)str.charAt(i) - getRandIdx();
                    }
                // jika penggeseran ke kanan maka
                } else {
                    // jika > 122(z) maka
                    // ubah huruf sekarang ke integer
                    // ditambah banyak penggeseran huruf
                    // dikurangi z(122) ditambah 96
                    if(((int)str.charAt(i) + getRandIdx()) > 122){
                        intAcak[i] = ((int)str.charAt(i) + getRandIdx() - 122) + 96;
                    // jika tidak maka
                    // ubah huruf sekarang ke integer
                    // ditambah banyak penggeseran huruf
                    } else {
                        intAcak[i] = (int)str.charAt(i) + getRandIdx();
                    }    
                }
                
                // jika bukan spasi maka ubah hasil penggeseran ke char
                if(str.charAt(i) != ' ')
                    charAcak[i] = (char)intAcak[i];
                // jika tidak maka huruf sekarang = spasi
                else
                    charAcak[i] = str.charAt(i);
                
            // Jika level <= 5 maka
            // inisialisasi huruf ke charAcak
            } else{
                charAcak[i] = str.charAt(i);
            }
        }
        
        // perulangan untuk pengacakan huruf
        // perulangan sebanyak panjang kata sekarang
        for(int i = 0; i < charAcak.length; i++){
            // mengacak index dari 0 - panjang kata sekarang
            // menyimpan dalam idxA
            idxA = rand.nextInt(charAcak.length);
            // mengacak index dari 0 - panjang kata sekarang
            // menyimpan dalam idxB
            idxB = rand.nextInt(charAcak.length);
            
            // memanggil method tukar
            tukar(idxA, idxB);
        }
    }
    
    // mengambil nilai level sekarang
    protected int getLevel(){
        return level;
    }
    
    // mengeset level sekarang
    protected void setLevel(int level){
        this.level =  level;
    }
    
    // mengambil nilai health sekarang
    protected int getHealth(){
        return health;
    }
    
    // mengeset health sekarang
    protected void setHealth(int health){
        this.health =  health;
    }
    
    // mencetak garis
    protected String getLine(){
        String strLine = "";
        for(int i = 0; i < LINE; i++)
            strLine += "-";
        return strLine;
    }
    
    // mengambil nilai cek
    protected boolean isCek() {
        return cek;
    }
    
    // mengeset cek
    protected void setCek(boolean cek) {
        this.cek = cek;
    }
    
    // mengambil nilai chLevel
    protected boolean isCheatLevel() {
        return chLevel;
    }

    // mengeset chLevel
    protected void setCheatLevel(boolean chLevel) {
        this.chLevel = chLevel;
    }

    // mengambil nilai chHealth
    protected boolean isCheatHealth() {
        return chHealth;
    }

    // mengeset chHealth
    protected void setCheatHealth(boolean chHealth) {
        this.chHealth = chHealth;
    }

    // method tukar() untuk menukar posisi kata
    private void tukar(){
        // perulangan untuk menukar posisi kata
        for(int i = 0; i < arrAcak.length; i++){
            // sama seperti pengacakan huruf
            // dengan bantuan variabel kosong untuk menukar nilai
            String temp = ""; // variabel untuk menukar nilai
            idxA = rand.nextInt(arrAcak.length);
            idxB = rand.nextInt(arrAcak.length);
            
            // var temp = kata di idxA
            // kata di idxA = kata di idxB
            // kata di idxB = var temp
            temp = temp.concat(arrAcak[idxA]);
            arrAcak[idxA] = arrAcak[idxB];
            arrAcak[idxB] = temp;
        }
    }
    
    // method tukar() untuk menukar posisi kata
    private void tukar(int a, int b){
        char c;
        
        // var c = huruf di idxA
        // huruf di idxA = huruf di idxB
        // huruf di idxB = var temp
        c = charAcak[a];
        charAcak[a] = charAcak[b];
        charAcak[b] = c;
    }
}