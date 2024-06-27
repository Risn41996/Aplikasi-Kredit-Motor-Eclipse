package com.example.project_kreditmotor_farihahamaliafikriafridaerviana;


public class Kredit extends Koneksi{
	private long id;
	Server server = new Server();
	
	String SERVER = server.urlDatabase1();
	String URL = "http://" + SERVER +"/jskreditmotor/tbkredit.php";
	String url = "";
	String response = "";
	
	public String tampil_query_kredit(){
		try{
			url = URL + "?operasi=query_kredit";
			System.out.println("URL tampil_query_kredit: " + url);
			response = call(url);
		}catch(Exception e){
		}
		return response;
	}
	
	public String tampilKreditorbyIdNama(){
		try{
			url = URL + "?operasi=select_by_idnama";
			System.out.println("URL Tampil Kreditor By Idnama: " + url);
			response = call(url);
		}catch(Exception e){
		}
		return response;
	}
	public String simpan_kredit(String idkreditor,String kdmotor,String hrgtunai,
			String dp,String hrgkredit,String bunga,String lama,String
			totalkredit,String angsuran){
		try{
			url = URL + "?operasi=simpan_kredit&idkreditor=" + idkreditor + "&kdmotor=" +
		kdmotor + "&hrgtunai=" + hrgtunai + "&dp=" + dp + "&hrgkredit=" + hrgkredit +
		"&bunga=" + bunga + "&lama=" + lama + "&totalkredit=" + totalkredit + "&angsuran=" + angsuran;
			System.out.println("URL Simpan Kredit : " + url);
			response = call(url);
		}catch(Exception e){
		}
		return response;
	}
	
	//hapus kreditor
		public String hapus_kredit(int invoice){
			try{
				url = URL + "?operasi=hapus_kredit&invoice=" + invoice;
				System.out.println("URL Hapus Kredit: " + url);
				response = call(url);
			}catch(Exception e){
			}
			return response;
		}
		
		public long getId() {
			// TODO Auto-generated method stub
			return id;
			//return null;
			}
	
}
