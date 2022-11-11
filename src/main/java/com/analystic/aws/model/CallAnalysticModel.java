package com.analystic.aws.model;

import java.util.ArrayList;
import java.util.List;

public class CallAnalysticModel {

	private List<Transcript> messages = new ArrayList<>();

	public CallAnalysticModel() {
	}

	public CallAnalysticModel(List<Transcript> messages) {
		this.messages = messages;
	}

	public List<Transcript> getMessages() {
		return messages;
	}

	public void setMessages(List<Transcript> messages) {
		this.messages = messages;
	}

}
