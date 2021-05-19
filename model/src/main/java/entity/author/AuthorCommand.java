package entity.author;

import javax.validation.constraints.NotBlank;

public class AuthorCommand {
    @NotBlank(message = "First name must not be empty")
    private String firstName;
    @NotBlank(message = "Last name must not be empty")
    private String lastName;
    @NotBlank(message = "Email must not be empty")
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
