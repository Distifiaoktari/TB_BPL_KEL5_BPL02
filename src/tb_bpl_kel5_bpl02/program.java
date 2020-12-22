package tb_bpl_kel5_bpl02;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;




public class program {
	 static Scanner scanner;
	    
	 static DataGudang dataGudang;
	 static KelolaUser kelolaUser;
	 static transaksipenjualan trp;
	 static DataUser dataUser = new DataUser();
	 static Laporan laporan;

	 public static int cek=2;
	
	 public static void main(String[] args) throws Exception  {
		 kelolaUser = new KelolaUser();
	     Integer option = 0;
	     scanner = new Scanner(System.in);
	     
	     do {
	    	 System.out.println(" ");
	    	 System.out.println(" =========================================== ");
	    	 System.out.println("|                MENU UTAMA                 |");
	    	 System.out.println(" =========================================== ");
	    	 System.out.println("| 1. Login                                  |");
	    	 System.out.println("| 2. Menu User                              |");
	    	 System.out.println(" =========================================== ");
	    	 System.out.print("\n Pilihan Anda (1/2)? ");
	    	 option = Integer.parseInt(scanner.nextLine());
	        
	        switch (option) {

	            case 1:
	            DataUser.login();
	            break;
	            
	            case 2:
	            user();
	            break;
	           
	            default:
	            System.out.println(" Input Tidak Valid");
	        }
	        Tunggu();
	        
	    } while (option != 0); 
	       
	 } 

	 
	 public static void menuUtama() throws Exception {
		 scanner = new Scanner(System.in);
	     dataGudang = new DataGudang();
	     kelolaUser = new KelolaUser();
	     Integer option = 0;
	         
	     do {
	    	 System.out.println(" ");
	    	 System.out.println(" =========================================== ");
	    	 System.out.println("|                DAFTAR PILIHAN             |");
	    	 System.out.println(" =========================================== ");
	    	 System.out.println("| 1. Data Master Barang                     |");
	    	 System.out.println("| 2. Restok                                 |");
	    	 System.out.println("| 3. Transaksi                              |");
	    	 System.out.println("| 4. Laporan                                |");
	    	 System.out.println("| 5. Logout                                 |");
	    	 System.out.println(" =========================================== ");
	    	 System.out.print("\n Pilihan Anda (1/2/3/4/5)? ");
	    	 option = Integer.parseInt(scanner.nextLine());
	        
	    	 switch (option) {
 
	         	case 1:
	            datamasterbarang();
	            break;
	            
	            case 2:
	            Restok();
	            break;
	            
	            case 3:
	            transaksi();
	            break;
	            
	            case 4:
	            laporan();
	            break;
	            
	            case 5:
	            kelolaUser.Logout();
	            break;
	            
	            default:
	            System.out.println(" Input Tidak Valid");
	        }
	        Tunggu();
	        
	    } while (option != 0); 
	       
	 }
	 
    
    
	private static void laporan() {
		scanner = new Scanner(System.in);
		laporan = new Laporan();
		Integer option = 0; 
		 do {
	        	System.out.println("====================================");
	            System.out.println("===============LAPORAN==============");
	            System.out.println("====================================");
	            System.out.println(" 1. Laporan perhari");
	            System.out.println(" 2. Laporan per bulan");
	            System.out.println(" 0. Keluar");
	            System.out.print("\nPilihan Anda (1/2/3/0)? ");
	            option = Integer.parseInt(scanner.nextLine());
	            System.out.println(" ");
	            
	            switch (option) {
	                case 1:
		                System.out.println(" >> LAPORAN PERHARI <<");
		                System.out.println(" "); 
		                laporan.laporanHari();
		                
	                break;
	                
	                case 2:
		           		System.out.println(" >> LAPORAN PERBULAN <<");	
		           		System.out.println(" ");
		           		laporan.laporanBulan();
	                break;
	                
	                case 0:
	                    break;
	                    
	                    default:
	                    System.out.println("Input tidak valid");
	                }
	                tunggu();  
	            } while (option != 0); 
	    	}	
		
	


	private static void transaksi() {
		// TODO Auto-generated method stub
		System.out.println(" >> TRANSAKSI BARANG <<");
		trp = new transaksipenjualan();
			trp.noresi();
			trp.insertall();
	}


	private static void Restok() throws SQLException {
		System.out.println(" ");  
		System.out.println(" ------------------------------------------- ");
		System.out.println("| >> RESTOK BARANG                          |");
		System.out.println(" ------------------------------------------- ");
		DataGudang.restok();
		System.out.println(" ------------------------------------------- ");
	}


	private static void user() throws Exception {
		// TODO Auto-generated method stub 
		scanner = new Scanner(System.in);
        kelolaUser = new KelolaUser();
        Integer option = 0;
		
        do {
        	System.out.println(" ");
        	System.out.println(" =========================================== ");
	    	System.out.println("|                KELOLA USER                |");
	    	System.out.println(" =========================================== ");
            System.out.println("| 1. Lihat User                             |");
            System.out.println("| 2. Tambah User                            |");
            System.out.println("| 3. Hapus User                             |");
            System.out.println("| 4. Edit User                              |");
            System.out.println("| 5. Cari User                              |");
            System.out.println("| 0. Kembali                                |");
            System.out.println(" =========================================== ");
            System.out.print("\n Pilihan Anda (1/2/3/4/5/0)? ");
            option = Integer.parseInt(scanner.nextLine());
            
            switch (option) {
            
                case 1:	
                System.out.println(" ");	
                System.out.println(" ------------------------------------------- ");
                System.out.println("| >> LIHAT USER                             |");
                System.out.println(" ------------------------------------------- ");
                System.out.println(" ");
                dataUser.lihatUser();
                System.out.println(" ------------------------------------------- ");
                break;
                
                case 2:
                System.out.println(" ");
                System.out.println(" ------------------------------------------- ");
                System.out.println("| >> SIGN UP                                |");
                System.out.println(" ------------------------------------------- ");	
           		dataUser.SignUp();
           		System.out.println(" ------------------------------------------- ");
                break;
                
                case 3:
                System.out.println(" ");
                System.out.println(" ------------------------------------------- ");
                System.out.println("| >> HAPUS USER                             |");
                System.out.println(" ------------------------------------------- ");
                dataUser.hapusUser();
                System.out.println(" ------------------------------------------- ");
                break;
                
                case 4:
                System.out.println(" ");
                System.out.println(" ------------------------------------------- ");
                System.out.println("| >> EDIT USER                              |");
                System.out.println(" ------------------------------------------- ");
                dataUser.editUser();
                System.out.println(" ------------------------------------------- ");
                break;
                
                case 5:
                System.out.println(" ");
                System.out.println(" ------------------------------------------- ");
                System.out.println("| >> CARI USER                              |");
                System.out.println(" ------------------------------------------- ");
                dataUser.cariUser();
                System.out.println(" ------------------------------------------- ");
                break;
                
                case 0:
                break;
                
                default:
                System.out.println(" Input Tidak Valid");
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
	    	System.out.println(" ");
        	System.out.println(" =========================================== ");
	    	System.out.println("|               MASTER BARANG               |");
	    	System.out.println(" =========================================== ");
	    	System.out.println("| 1. Lihat Barang                           |");
	    	System.out.println("| 2. Tambah Barang                          |");
	    	System.out.println("| 3. Hapus Barang                           |");
	    	System.out.println("| 4. Edit Barang                            |");
	    	System.out.println("| 5. Cari Barang                            |");
	    	System.out.println("| 0. Kembali                                |");
	    	System.out.println(" =========================================== ");
	    	System.out.print("\n Pilihan Anda (1/2/3/4/5/0)? ");
	    	option = Integer.parseInt(scanner.nextLine());
        
	    	switch (option) {
            
	    		case 1:
	    		System.out.println(" ");	
	    		System.out.println(" ------------------------------------------- ");
	            System.out.println("| >> LIHAT BARANG                           |");
	            System.out.println(" ------------------------------------------- ");
	            System.out.println(" ");
	    		lihatBarang();
	    		System.out.println(" ------------------------------------------- ");
	    		break;
            
	    		case 2:
	    		System.out.println(" ");
	    		System.out.println(" ------------------------------------------- ");
		        System.out.println("| >> TAMBAH BARANG                           |");
		        System.out.println(" ------------------------------------------- ");
	    		tambahBarang();
	    		System.out.println(" ------------------------------------------- ");
	    		break;
            
	    		case 3:
	    		System.out.println(" ");	
	    		System.out.println(" ------------------------------------------- ");
		        System.out.println("| >> HAPUS BARANG                           |");
		        System.out.println(" ------------------------------------------- ");
	    		hapusBarang();
	    		System.out.println(" ------------------------------------------- ");
	    		break;
            
	    		case 4:
	    		System.out.println(" ");	
	    		System.out.println(" ------------------------------------------- ");
		        System.out.println("| >> EDIT BARANG                            |");
		        System.out.println(" ------------------------------------------- ");		
	    		editBarang();
	    		System.out.println(" ------------------------------------------- ");
	    		break;
            
	    		case 5:
	    		System.out.println(" ");	
	    		System.out.println(" ------------------------------------------- ");
		        System.out.println("| >> CARI BARANG                            |");
		        System.out.println(" ------------------------------------------- ");
	    		cariBarang();
	    		System.out.println(" ------------------------------------------- ");
	    		break;
            
	    		case 0:
	    		break;
            
	    		default:
	    		System.out.println(" Input Tidak Valid");
	    	}
	    	tunggu();
	    } while (option != 0); 
	}
    
    private static void cariBarang() throws SQLException {
        
    	System.out.print(" Masukkan kata kunci (nama barang) : ");
        String keyword = scanner.nextLine();
        
        ArrayList<Gudang> listGudang = dataGudang.cari(keyword);
        
        for(Gudang gudang : listGudang){
        	System.out.println(" ");
            System.out.println(" Nama Barang : " + gudang.getnama());
            System.out.println(" StokBarang  : " + gudang.getstok());
            System.out.println(" Harga Beli  : " + gudang.getharga_beli());
            System.out.println(" Harga Jual  : " + gudang.getharga_jual());
            System.out.println(" ");
        }
    }
    
    private static void editBarang() {
        
            lihatBarang();
            System.out.print(" SKU barang yang akan diedit ? : ");
            String sku = scanner.nextLine();
            
            Gudang gudang = dataGudang.get(sku);
            
            System.out.print(" nama ["+gudang.getnama()+"]: ");
            String nama = scanner.nextLine();
        
            System.out.print(" stok ["+gudang.getstok()+"]: ");
            Integer stok = Integer.parseInt(scanner.nextLine());
            
            System.out.print(" harga beli ["+gudang.getharga_beli()+"]: ");
            Integer harga_beli = Integer.parseInt(scanner.nextLine());
            
            System.out.print(" harga jual ["+gudang.getharga_jual()+"]: ");
            Integer harga_jual = Integer.parseInt(scanner.nextLine());
            
            Gudang gudangUpdate = new Gudang(nama, stok, harga_beli, harga_jual);
            
            if(dataGudang.update(sku, gudangUpdate) > 0){
                System.out.println(" Berhasil Mengubah Data Barang!");
            }
        
    }
    
    private static void hapusBarang(){
        
        lihatBarang();
        
        System.out.print(" SKU barang yang akan dihapus ? : ");
        String sku = (scanner.nextLine());
        
        if(dataGudang.hapus(sku) > 0 ){
            System.out.println(" Barang Berhasil Dihapus!");
        }
           
    }
    
    private static void tambahBarang() {
        
        System.out.print(" SKU        : ");
        String sku = scanner.nextLine();
        
        System.out.print(" Nama       : ");
        String nama = scanner.nextLine();
        
        System.out.print(" Stok       : ");
        Integer stok = Integer.parseInt(scanner.nextLine());
        
        System.out.print(" Harga Beli : ");
        Integer harga_beli = Integer.parseInt(scanner.nextLine());
        
        System.out.print(" Harga Jual : ");
        Integer harga_jual = Integer.parseInt(scanner.nextLine());
        
        Gudang gudang = new Gudang(sku,nama, stok, harga_beli, harga_jual);
        
        if(dataGudang.tambah(gudang) > 0){
            System.out.println(" Barang Berhasil Ditambahkan!");
        }
        
    }
    
    private static void lihatBarang(){

        ArrayList<Gudang> listGudang = dataGudang.getAll();
        
        for(Gudang gudang : listGudang){
            System.out.println(" SKU Barang  : " + gudang.getsku());
            System.out.println(" Nama Barang : " + gudang.getnama());
            System.out.println(" Stok Barang : " + gudang.getstok());
            System.out.println(" Harga Jual  : " + gudang.getharga_jual());
            System.out.println(" ");
        } 
        
    }
        
    private static void tunggu(){
    	System.out.print("\n Tekan Enter Untuk Melanjutkan");
        scanner.nextLine();
    }
    
    private static void Tunggu(){
        System.out.print("\n Tekan Enter Untuk Kembali");
        scanner.nextLine();
    }

	

}
