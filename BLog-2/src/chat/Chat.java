package chat;

public class Chat {
	int chatID;	//chatID 부과 -> sequence 생성 후 자동증가
	String chaName;	//chat user name
	String chatContent;// 채팅 내용
	String chatTime;// 채팅 시간
	
	public int getChatID() {
		return chatID;
	}
	public void setChatID(int chatID) {
		this.chatID = chatID;
	}
	public String getChaName() {
		return chaName;
	}
	public void setChaName(String chaName) {
		this.chaName = chaName;
	}
	public String getChatContent() {
		return chatContent;
	}
	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}
	public String getChatTime() {
		return chatTime;
	}
	public void setChatTime(String chatTime) {
		this.chatTime = chatTime;
	}
	
	
}
