package com.springshop.domains.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

}
