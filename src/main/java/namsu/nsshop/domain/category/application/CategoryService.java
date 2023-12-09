package namsu.nsshop.domain.category.application;

import lombok.RequiredArgsConstructor;
import namsu.nsshop.domain.category.dao.CategoryRepository;
import namsu.nsshop.domain.category.domain.Category;
import namsu.nsshop.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Long create(String name, Long parentId, int position) {
        Category category = Category.builder()
                .name(name)
                .parent(parentId == null ? null : Category.builder()
                        .id(parentId)
                        .build())
                .position(position)
                .build();

        return categoryRepository.save(category).getId();
    }

    @Transactional
    public void update(Long id, String name) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        category.changeName(name);
    }

    @Transactional
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
