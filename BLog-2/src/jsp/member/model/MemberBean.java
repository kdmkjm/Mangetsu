package jsp.member.model;

import java.sql.Timestamp;

import oracle.sql.CHAR;

// 데이터의 전달을 담당하는 클래스 - DTO
public class MemberBean {
	private String Userno; // 유저고유번호
	private String id; // 아이디
	private String password; // 비밀번호
	private String name; // 이름
	private String mail1; // 이메일 - @ 앞부분
	private String mail2; // 이메일 - @ 뒷부분
	private String phone; // 전화
	private String address1; // 주소
	private String address2; // 주소
	private String address3; // 주소
	private String address4; // 주소
	private Timestamp reg; // 가입일
	private String mailcheck; // 가입일
	private String status; // 권한설정
	private String intro; // 소개글
	private String intro_blog; // 블로그 소개글
	private String pic_blog; // 블로그 사진
	
	

//    private String chk;	//메일 인증 확인

	public String getUserno() {
		return Userno;
	}

	public void setUserno(String userno) {
		Userno = userno;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail1() {
		return mail1;
	}

	public void setMail1(String mail1) {
		this.mail1 = mail1;
	}

	public String getMail2() {
		return mail2;
	}

	public void setMail2(String mail2) {
		this.mail2 = mail2;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress1() {
		return address1;
	}// 1과 4는 사용하지 않을거에요!

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getAddress4() {
		return address4;
	}

	public void setAddress4(String address4) {
		this.address4 = address4;
	}

	public Timestamp getReg() {
		return reg;
	}

	public void setReg(Timestamp reg) {
		this.reg = reg;
	}
//	public String getChk() {return chk;}
//	public void setChk(String chk) {this.chk = chk;}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getIntro_blog() {
		return intro_blog;
	}

	public void setIntro_blog(String intro_blog) {
		this.intro_blog = intro_blog;
	}

	public String getPic_blog() {
		return pic_blog;
	}

	public void setPic_blog(String pic_blog) {
		this.pic_blog = pic_blog;
	}

	public String getMailcheck() {
		return mailcheck;
	}

	public void setMailcheck(String mailcheck) {
		this.mailcheck = mailcheck;
	}

}