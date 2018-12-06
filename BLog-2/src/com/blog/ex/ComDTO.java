package com.blog.ex;

public class ComDTO {
	private int Idpost_com;
	private String writecom;
	private String post_com;
	private String Com_story;
	private String Com_name;
	private String Com_date;

	public ComDTO() {
		super();
	}

	public ComDTO(String writecom, String post_com, String com_story, String Com_name) {
		super();
		this.writecom = writecom;
		this.post_com = post_com;
		this.Com_story = com_story;
		this.Com_name = Com_name;
	}

	public int getIdpost_com() {
		return Idpost_com;
	}

	public void setIdpost_com(int idpost_com) {
		Idpost_com = idpost_com;
	}

	public String getWritecom() {
		return writecom;
	}

	public void setWritecom(String writecom) {
		this.writecom = writecom;
	}

	public String getPost_com() {
		return post_com;
	}

	public void setPost_com(String post_com) {
		this.post_com = post_com;
	}

	public String getCom_story() {
		return Com_story;
	}

	public void setCom_story(String com_story) {
		Com_story = com_story;
	}

	public String getCom_date() {
		return Com_date;
	}

	public void setCom_date(String com_date) {
		Com_date = com_date;
	}

	public String getCom_name() {
		return Com_name;
	}

	public void setCom_name(String com_name) {
		Com_name = com_name;
	}

}
