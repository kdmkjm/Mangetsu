package com.test.ex;

public class MemberDTO1 {
	private int idpost;
	private String title;
	private String story;
	private String picfile_post;
	private String postdate;
	private String manipulation_read;
	private String writepost;
	
	public MemberDTO1() {
		super();
	}

	public MemberDTO1(String title, String story, String picfile_post, String manipulation_read, String writepost) {
		super();

		this.title = title;
		this.story = story;
		this.picfile_post = picfile_post;
		this.manipulation_read = manipulation_read;
		this.writepost = writepost;

	}

	public int getIdpost() {
		return idpost;
	}

	public void setIdpost(int idpost) {
		this.idpost = idpost;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}

	public String getPicfile_post() {
		return picfile_post;
	}

	public void setPicfile_post(String picfile_post) {
		this.picfile_post = picfile_post;
	}

	public String getManipulation_read() {
		return manipulation_read;
	}

	public void setManipulation_read(String manipulation_read) {
		this.manipulation_read = manipulation_read;
	}

	public String getWritepost() {
		return writepost;
	}

	public void setWritepost(String writepost) {
		this.writepost = writepost;
	}

	public String getPostdate() {
		return postdate;
	}

	public void setPostdate(String postdate) {
		this.postdate = postdate;
	}

	
	
} // end class
