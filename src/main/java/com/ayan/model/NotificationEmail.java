package com.ayan.model;

public class NotificationEmail {
	
	private String subject;
	private String recipient;
	private String body;
	
//	Constructors
	public NotificationEmail(String subject, String recipient, String body) {
		super();
		this.subject = subject;
		this.recipient = recipient;
		this.body = body;
	}

	public NotificationEmail() {
		super();
	}
	
//	Getter Setter
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
//	Equals HashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + ((recipient == null) ? 0 : recipient.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotificationEmail other = (NotificationEmail) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (recipient == null) {
			if (other.recipient != null)
				return false;
		} else if (!recipient.equals(other.recipient))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		return true;
	}
	
//	To String
	@Override
	public String toString() {
		return "NotificationEmail [subject=" + subject + ", recipient=" + recipient + ", body=" + body + "]";
	}
	
	
}
