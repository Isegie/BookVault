package entity.author;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;


@Builder
@EqualsAndHashCode
public class AuthorDTO extends RepresentationModel<AuthorDTO> {
    private String firstName;
    private String lastName;
    private String email;

    public AuthorDTO() {
    }

    public AuthorDTO(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}