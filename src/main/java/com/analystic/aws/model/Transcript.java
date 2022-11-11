package com.analystic.aws.model;

public class Transcript {
	
	private String content;
	private String participantRole;
	private String sentiment;
	
	public Transcript() {

	}

	public Transcript(String content, String participantRole, String sentiment) {
		this.content = content;
		this.participantRole = participantRole;
		this.sentiment = sentiment;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getParticipantRole() {
		return participantRole;
	}

	public void setParticipantRole(String participantRole) {
		this.participantRole = participantRole;
	}

	public String getSentiment() {
		return sentiment;
	}

	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}

}
