package com.blog.ex;

public class friendDTO {
	private String Idme;
	private String idfriend;

	public friendDTO() {
		super();
	}

	public friendDTO(String idme, String idfriend) {
		super();
		this.Idme = idme;
		this.idfriend = idfriend;
	}

	public String getIdme() {
		return Idme;
	}

	public void setIdme(String idme) {
		Idme = idme;
	}

	public String getidfriendr() {
		return idfriend;
	}

	public void setidfriend(String idfriend) {
		this.idfriend = idfriend;
	}

} // end class
