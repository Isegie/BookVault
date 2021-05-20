package specification.author;

import entity.author.Author;
import org.springframework.data.jpa.domain.Specification;

public class AuthorSpecification {
    private AuthorSpecification() {
    }

    public static Specification<Author> byFirstNameOrLastName(String field) {
        return (root, query, cb) -> cb.or(
                cb.like(cb.lower(root.<String>get("firstName")), containsLowerCase(field)),
                cb.like(cb.lower(root.<String>get("lastName")), containsLowerCase(field)));
    }

    protected static String containsLowerCase(String field) {
        if (field == null || field.isEmpty()) {
            return "%";
        } else {
            return "%" + field.toLowerCase() + "%";
        }
    }
}
