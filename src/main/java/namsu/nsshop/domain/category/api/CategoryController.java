package namsu.nsshop.domain.category.api;

import lombok.RequiredArgsConstructor;
import namsu.nsshop.domain.category.application.CategoryService;
import namsu.nsshop.domain.category.dao.CategoryRepository;
import namsu.nsshop.domain.category.domain.Category;
import namsu.nsshop.domain.category.dto.CategoryCreateRequest;
import namsu.nsshop.domain.category.dto.CategoryCreateResponse;
import namsu.nsshop.domain.category.dto.CategoryDetailsResponse;
import namsu.nsshop.domain.category.dto.CategoryUpdateRequest;
import namsu.nsshop.global.error.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryCreateResponse create(@RequestBody CategoryCreateRequest request) {
        Long categoryId = categoryService.create(request.getName(), request.getParentId(), request.getPosition());
        return CategoryCreateResponse.builder()
                .categoryId(categoryId)
                .build();
    }

    @GetMapping("/{categoryId}")
    public CategoryDetailsResponse getDetails(@PathVariable("categoryId") Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(EntityNotFoundException::new);

        return CategoryDetailsResponse.from(category);
    }

    @PatchMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDetails(@PathVariable("categoryId") Long categoryId,
                              @RequestBody CategoryUpdateRequest request) {

        categoryService.update(categoryId, request.getName());
    }

    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("categoryId") Long categoryId) {
        categoryService.delete(categoryId);
    }
}
