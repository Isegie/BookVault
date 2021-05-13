package service.category;

import entity.category.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.category.CategoryRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findCategoryById(Long id) {

        Category foundCategory = categoryRepository.getOne(id);

        if (foundCategory == null) {
            throw new EntityNotFoundException(String.format("Category with id=%d not found", id));
        }

        return foundCategory;
    }
}
