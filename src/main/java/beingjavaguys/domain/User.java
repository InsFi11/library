package beingjavaguys.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User{

    @Id
    @Column(name="id_user")
	private int userId;
    @Column(name="firstName")
	private String firstName;
    @Column(name="lastName")
	private String lastName;
    @Column(name="login")
    private String login;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;
    @Column(name="isLibrarian")
    private boolean isLibrarian;
    @Column(name="gender")
	private String gender;
    @Column(name="dateOfBirth")
    private String dateOfBirth;
    @Column(name="secretQuestion")
    private String secretQuestion;


	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public boolean getIsLibrarian() {
        return isLibrarian;
    }

    public void setIsLibrarian(boolean isLibrarian) {
        this.isLibrarian = isLibrarian;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;

    }
    public String getSecretQuestion() {
        return secretQuestion;
    }

    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }

    public String getPrivileges(){
        if(!isLibrarian)
            return "ROLE_USER";
        else return "ROLE_ADMIN";}
}
