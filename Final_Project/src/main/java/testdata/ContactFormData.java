package testdata;

public class ContactFormData {
	
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String message;
	
	public ContactFormData(String firstName, String lastName, String email, String phone, String message) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.message = message;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public String getMessage() {
		return message;
	}
	
	@Override
	public String toString() {
		return "ContactFormData [firstName = " + firstName + ", lastName = " + lastName + ", email = " + email
				+ ", phone = " + phone + ", message = " + message + "]";
	}

}
