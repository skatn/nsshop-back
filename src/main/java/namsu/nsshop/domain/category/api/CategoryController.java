package namsu.nsshop.domain.category.api;

import lombok.RequiredArgsConstructor;
import namsu.nsshop.domain.category.application.CategoryService;
import namsu.nsshop.domain.category.dto.CategoryCreateRequest;
import namsu.nsshop.domain.category.dto.CategoryCreateResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryCreateResponse create(@RequestBody CategoryCreateRequest request) {
        Long categoryId = categoryService.create(request.getName(), request.getParentId(), request.getPosition());
        return CategoryCreateResponse.builder()
                .categoryId(categoryId)
                .build();
    }
}
