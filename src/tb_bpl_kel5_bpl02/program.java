package tb_bpl_kel5_bpl02;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class program {
	 static Scanner scanner;
	    
	 static DataGudang dataGudang;
	 static KelolaUser kelolaUser;
	 static DataUser dataUser = new DataUser();
	
	 public static void main(String[] args) throws Exception  {
		scanner = new Scanner(System.in);
        dataGudang = new DataGudang();
        kelolaUser = new KelolaUser();
        Integer option = 0;
         
       do {
    	System.out.println("==================");
        System.out.println("====MENU UTAMA====");
        System.out.println("==================");
        System.out.println(" 1. Login");
        System.out.println(" 2. User");
        System.out.println(" 3. data master barang");
        System.out.println(" 4. restok");
        System.out.println(" 5. transaksi");
        System.out.println(" 6. laporan");
        System.out.println(" 0. Keluar");
        System.out.print("\nPilihan Anda (1/2/3/4/5/0)? ");
        option = Integer.parseInt(scanner.nextLine());
        
        switch (option) {
            case 1:
            System.out.println(" >> LOG IN <<");
            System.out.println(" ");
            dataUser.login();
            break;
            case 2:
            user();
            break;
            case 3:
            datamasterbarang();
            break;
            case 4:
            Restok();
            break;
            case 5:
            transaksi();
            break;
            case 6:
            laporan();
            break;
            case 0:
            break;
            default:
            System.out.println("Input tidak valid");
        }
        Tunggu();
        
    } while (option != 0); }
    
    
  
	private static void laporan() {
		// TODO Auto-generated method stub
		
	}


	private static void transaksi() {
		// TODO Auto-generated method stub
		
	}


	private static void Restok() {
		  {
			  scanner = new Scanner(System.in);
		        dataGudang = new DataGudang();
		        
		        System.out.println(" >> RE-STOK BARANG <<");
		        
		        
		            lihatBarang();
		            System.out.print("sku barang yang akan direstok ? ");
		            String sku = scanner.nextLine();
		            
		            Gudang gudang = dataGudang.get(sku);
		            
		            System.out.println("nama ["+gudang.getnama()+"] ");
		           
		        
		            
		            System.out.print("stok ["+gudang.getstok()+"]: ");
		            Integer stok = Integer.parseInt(scanner.nextLine());
		            
		            System.out.println("harga beli ["+gudang.getharga_beli()+"]");
		           
		            
		            System.out.println("harga jual ["+gudang.getharga_jual()+"] ");
		          
		            
		            Gudang gudangUpdate = new Gudang(stok);
		            
		            if(dataGudang.restok(sku, gudangUpdate) > 0){
		                System.out.println("berhasil mengubah data!");
		            }
		        
		    }
		
	}


	private static void user() throws Exception {
		// TODO Auto-generated method stub 
		scanner = new Scanner(System.in);
        kelolaUser = new KelolaUser();
        Integer option = 0;
		
        do {
        	System.out.println("====================================");
            System.out.println("=============KELOLA USER============");
            System.out.println("====================================");
            System.out.println(" 1. Lihat User");
            System.out.println(" 2. Tambah User");
            System.out.println(" 3. Hapus User");
            System.out.println(" 4. Edit User");
            System.out.println(" 5. Cari User");
            System.out.println(" 0. Keluar");
            System.out.print("\nPilihan Anda (1/2/3/4/5/0)? ");
            option = Integer.parseInt(scanner.nextLine());
            System.out.println(" ");
            
            switch (option) {
                case 1:
                System.out.println(" >> LIHAT USER <<");
                System.out.println(" ");
                dataUser.lihatUser();
                break;
                
                case 2:
           		System.out.println(" >> SIGN UP <<");	
           		System.out.println(" ");
           		dataUser.SignUp();
                break;
                
                case 3:
                System.out.println(" >> HAPUS USER <<");
                System.out.println(" ");
                dataUser.hapusUser();
                break;
                
                case 4:
                System.out.println(" >> EDIT USER <<");
                System.out.println(" ");
                dataUser.editUser();
                break;
                
                case 5:
                System.out.println(" >> CARI USER <<");
                System.out.println(" ");
                dataUser.cariUser();
                break;
                
                case 0:
                break;
                
                default:
                System.out.println("Input tidak valid");
            }
            tunggu();  
        } while (option != 0); 
	}
	
	
	  private static void datamasterbarang() throws Exception {
			// TODO Auto-generated method stub
			
		  scanner = new Scanner(System.in);
	        dataGudang = new DataGudang();
	        Integer option = 0;

	do {
    	System.out.println("==========================");
        System.out.println("====DATA MASTER BARANG====");
        System.out.println("==========================");
        System.out.println(" 1. Lihat Barang");
        System.out.println(" 2. Tambah Barang");
        System.out.println(" 3. Hapus Barang");
        System.out.println(" 4. Edit Barang");
        System.out.println(" 5. Cari Barang");
        System.out.println(" 0. Keluar");
        System.out.print("\nPilihan Anda (1/2/3/4/5/0)? ");
        option = Integer.parseInt(scanner.nextLine());
        
        switch (option) {
            case 1:
            lihatBarang();
            break;
            case 2:
            tambahBarang();
            break;
            case 3:
            hapusBarang();
            break;
            case 4:
            editBarang();
            break;
            case 5:
            cariBarang();
            break;
            case 0:
            break;
            default:
            System.out.println("Input tidak valid");
        }
        tunggu();
        
    } while (option != 0); }
    
    private static void cariBarang() throws SQLException {
        System.out.println(" >> CARI BARANG <<");
        
        System.out.print("Masukkan kata kunci (nama barang) ");
        String keyword = scanner.nextLine();
        
        ArrayList<Gudang> listGudang = dataGudang.cari(keyword);
        
        for(Gudang gudang : listGudang){
            System.out.print(gudang.getnama());
            System.out.print("\t");
            System.out.print(gudang.getstok());
            System.out.print("\t");
            System.out.println(gudang.getharga_beli());
            System.out.print("\t");
            System.out.println(gudang.getharga_jual());
        }
    }
    
    private static void editBarang() {
        System.out.println(" >> EDIT BARANG <<");
        
        
            lihatBarang();
            System.out.print("sku barang yang akan diedit ? ");
            String sku = scanner.nextLine();
            
            Gudang gudang = dataGudang.get(sku);
            
            System.out.print("nama ["+gudang.getnama()+"]: ");
            String nama = scanner.nextLine();
        
            
            System.out.print("stok ["+gudang.getstok()+"]: ");
            Integer stok = Integer.parseInt(scanner.nextLine());
            
            System.out.print("harga beli ["+gudang.getharga_beli()+"]: ");
            Integer harga_beli = Integer.parseInt(scanner.nextLine());
            
            System.out.print("harga jual ["+gudang.getharga_jual()+"]: ");
            Integer harga_jual = Integer.parseInt(scanner.nextLine());
            
            Gudang gudangUpdate = new Gudang(nama, stok, harga_beli, harga_jual);
            
            if(dataGudang.update(sku, gudangUpdate) > 0){
                System.out.println("berhasil mengubah data!");
            }
        
    }
    
    private static void hapusBarang(){
        System.out.println(" >> HAPUS BARANG <<");
        
        lihatBarang();
        
        System.out.print("sku barang yang akan dihapus ? ");
        String sku = (scanner.nextLine());
        
        if(dataGudang.hapus(sku) > 0 ){
            System.out.println("barang berhasil di hapus!");
        }
        
        
        
    }
    
    private static void tambahBarang() {
        System.out.println(" >> TAMBAH BARANG <<");
        
        System.out.print("sku : ");
        String sku = scanner.nextLine();
        System.out.print("nama : ");
        String nama = scanner.nextLine();
        
        
        System.out.print("stok : ");
        Integer stok = Integer.parseInt(scanner.nextLine());
        
        System.out.print("harga_beli : ");
        Integer harga_beli = Integer.parseInt(scanner.nextLine());
        
        System.out.print("harga jual : ");
        Integer harga_jual = Integer.parseInt(scanner.nextLine());
        
        Gudang gudang = new Gudang(sku,nama, stok, harga_beli, harga_jual);
        
        if(dataGudang.tambah(gudang) > 0){
            System.out.println("barang berhasil ditambahkan!");
        }
        
    }
    
    private static void lihatBarang(){
        System.out.println(" >> LIHAT BARANG <<");
        ArrayList<Gudang> listGudang = dataGudang.getAll();
        
        for(Gudang gudang : listGudang){
            System.out.print(gudang.getsku());
            System.out.print("\t| ");
            System.out.print(gudang.getnama());
            System.out.print("\t| ");
            System.out.print(gudang.getstok());
            System.out.print("\t| ");
            System.out.println(gudang.getharga_jual());
            
        } }
        private static void tunggu(){
            System.out.print("\n\nTekan Enter untuk melanjutkan");
            scanner.nextLine();
    }
    
    private static void Tunggu(){
        System.out.print("\n\nTekan Enter untuk melanjutkan");
        scanner.nextLine();
    }

	

}
