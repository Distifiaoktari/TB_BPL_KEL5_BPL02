package tb_bpl_kel5_bpl02;

public class Gudang {
	  private String sku;
	    private String nama;
	    private Integer stok;
	    private Integer harga_beli;
	    private Integer harga_jual;
	    private String noresi;
	    private Integer jumlah;
	    String date;

	    public Gudang() {

	    }

	    public String getsku() {
	        return sku;
	    }

	    public void setsku(String sku) {
	        this.sku = sku;
	    }

	    public String getnama() {
	        return nama;
	    }

	    public void setnama(String nama) {
	        this.nama = nama;
	    }
	    public Integer getstok() {
	        return stok;
	    }

	    public void setstok(Integer stok) {
	        this.stok = stok;
	    }
	    public Integer getharga_beli() {
	        return harga_beli;
	    }

	    public void setharga_beli(Integer harga_beli) {
	        this.harga_beli = harga_beli;
	    }
	    public Integer getharga_jual() {
	        return harga_jual;
	    }

	    public void setharga_jual(Integer harga_jual) {
	        this.harga_jual = harga_jual;
	    }

	    public void setnoresi(String noresi) {
			this.noresi = noresi;
		}
		public String getnoresi() {
			return noresi;
		}
		public void setjumlah(Integer jumlah) {
			this.jumlah = jumlah;
		}
		public Integer getjumlah() {
			return jumlah;
		}
	    

	    public Gudang(String nama, Integer stok, Integer harga_beli,Integer harga_jual) {
	        this.setnama(nama);
	        this.setstok(stok);
	        this.setharga_beli(harga_beli);
	        this.setharga_jual(harga_jual);
	        
	    }

	    public Gudang(String sku, String nama, Integer stok, Integer harga_beli,Integer harga_jual) {
	        this.setsku(sku);
	        this.setnama(nama);
	        this.setstok(stok);
	        this.setharga_beli(harga_beli);
	        this.setharga_jual(harga_jual);
	    }
	    public Gudang(String sku, String noresi, Integer jumlah) {
	    	this.setsku(sku);
	    	this.setnoresi(noresi);
	    	this.setjumlah(jumlah);
	    }
	    
	    public Gudang(Integer stok) {
	        
	        this.setstok(stok);
	       
	        
	    }
	

}
