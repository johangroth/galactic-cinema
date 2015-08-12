package uk.org.linuxgrotto.form;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

public class MessageForm {
	
	private String name;

	@Email
	private String email;
	
	@Length(min=9)
	private String phone;
	private String website;
	private String message;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MessageForm [name=")
                .append(name)
                .append(", email=")
				.append(email)
                .append(", phone=")
                .append(phone)
				.append(", website=")
                .append(website)
                .append(", message=")
				.append(message)
                .append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(name)
				.append(email)
				.append(phone)
				.append(website)
				.append(message)
				.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
        if (obj instanceof MessageForm) {
            final MessageForm other = (MessageForm) obj;
            return new EqualsBuilder()
                    .append(name, other.name)
                    .append(email, other.email)
                    .append(phone, other.phone)
                    .append(website, other.website)
                    .append(message, other.message)
                    .isEquals();
        } else {
            return false;
        }
	}
}
