package com.example.project_kreditmotor_farihahamaliafikriafridaerviana;

public class Kreditor extends Koneksi{
	private long id;
	Server server = new Server();
	
	String SERVER = server.urlDatabase1();
	String URL = "http://" + SERVER +"/jskreditmotor/tbkreditor.php";
	String url = "";
	String response = "";
	
	//Tampil Kreditor
	public String tampilKreditor(){
		try{
			url = URL + "?operasi=view";
			System.out.println("URL Tampil Kreditor: " + url);
			response = call(url);
		}catch(Exception e){
		}
		return response;
	}
	
	//Tampil Kreditor by id nama
	public String tampilKreditorbyIdNama(){
		try{
			url = URL + "?operasi=select_by_idnama";
			System.out.println("URL Tampil Kreditor By Idnama: " + url);
			response = call(url);
		}catch(Exception e){
		}
		return response;
	}
	
	//insert
	public String insertKreditor(String nama, String pekerjaan, String telp, String alamat){
		nama = nama.replace(" ", "%20");
		pekerjaan = pekerjaan.replace(" ", "%20");
		telp = telp.replace(" ", "%20");
		alamat = alamat.replace(" ", "%20");
		try{
			url = URL + "?operasi=insert&nama=" + nama + "&pekerjaan=" +
		pekerjaan + "&telp=" + telp + "&alamat=" + alamat;
			System.out.println("URL Insert Kreditor : " + url);
			response = call(url);
		}catch(Exception e){
		}
		return response;
	}
	
	//get kreditor by idkreditor
	public String getKreditorByIdkreditor(int idkreditor){
		try{
			url = URL + "?operasi=get_kreditor_by_idkreditor&idkreditor=" + idkreditor;
			System.out.println("URL Get Kreditor by idkreditor: " + url);
			response = call(url);
		}catch(Exception e){
		}
		return response;
	}
	
	//select by idkreditor
	public String select_by_Idkreditor(String idkreditor){
		try{
			url = URL + "?operasi=select_by_idkreditor&idkreditor=" + idkreditor;
			System.out.println("URL Select Kreditor by id: " + url);
			response = call(url);
		}catch(Exception e){
		}
		return response;
	}
	
	//update kreditor
	public String updateKreditor(String idkreditor, String nama, String pekerjaan, String telp, String alamat){
		nama = nama.replace(" ", "%20");
		pekerjaan = pekerjaan.replace(" ", "%20");
		telp = telp.replace(" ", "%20"); 
		alamat = alamat.replace(" ", "%20");
		try{
			url = URL + "?operasi=update&idkreditor=" + idkreditor + "&nama=" + nama +
					"&pekerjaan=" + pekerjaan + "&telp=" + telp + "&alamat=" + alamat;
			System.out.println("URL Update Kreditor: " + url);
			response = call(url);
		}catch(Exception e){
		}
		return response;
	}
	
	//hapus kreditor
	public String deleteKreditor(int idkreditor){
		try{
			url = URL + "?operasi=delete&idkreditor=" + idkreditor;
			System.out.println("URL Hapus Kreditor: " + url);
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