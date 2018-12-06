package com.blog.ex;

public class BlogDTO {
	private int idpost;
	private String title;
	private String story;
	private String postdate;
	private String manipulation_read;
	private String writepost;
	private String cnt_post;

	public BlogDTO() {
		super();
	}

	public BlogDTO(String title, String story, String manipulation_read, String writepost) {
		super();

		this.title = title;
		this.story = story;
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

	public String getCnt_post() {
		return cnt_post;
	}

	public void setCnt_post(String cnt_post) {
		this.cnt_post = cnt_post;
	}
	
	

} // end class
