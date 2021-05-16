package specification.book;

import entity.book.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecifications {

    private BookSpecifications() {
    }

    public static Specification<Book> likeTitleOrLanguage(String value) {
        return (root, query, cb) -> cb.or(
                cb.like(cb.lower(root.<String>get("title")), containsLowerCase(value)),
                cb.like(cb.lower(root.<String>get("language")), containsLowerCase(value)));
    }

    protected static String containsLowerCase(String field) {
        if (field == null || field.isEmpty()) {
            return "%";
        } else {
            return "%" + field.toLowerCase() + "%";
        }
    }
}
