package namsu.nsshop.domain.category.application;

import namsu.nsshop.domain.category.dao.CategoryRepository;
import namsu.nsshop.domain.category.domain.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @InjectMocks
    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @Test
    @DisplayName("카테고리 추가")
    void create() {
        // given
        String name = "카테고리";
        int position = 0;
        Category category = Category.builder()
                .id(1L)
                .name(name)
                .build();
        given(categoryRepository.save(any())).willReturn(category);

        // when
        Long categoryId = categoryService.create(name, null, position);

        // then
        assertThat(categoryId).isEqualTo(1L);
        then(categoryRepository).should().save(any(Category.class));
    }

    @Test
    @DisplayName("카테고리 추가 - 부모 카테고리 지정")
    void createWithParent() {
        // given
        String name = "카테고리";
        Long parentId = 1L;
        int position = 0;
        Category category = Category.builder()
                .id(2L)
                .name(name)
                .build();
        given(categoryRepository.save(any())).willReturn(category);

        // when
        Long categoryId = categoryService.create(name, parentId, position);

        // then
        assertThat(categoryId).isEqualTo(2L);
        then(categoryRepository).should().save(any(Category.class));
    }

    @Test
    @DisplayName("카테고리 상세 내용 조회")
    void details() {
        // given
        Category category = Category.builder()
                .id(1L)
                .name("카테고리")
                .build();
        given(categoryRepository.findById(anyLong()))
                .willReturn(Optional.of(category));

        // when
        Category actual = categoryService.getDetails(1L);

        // then
        assertThat(actual).isEqualTo(category);
    }

    @Test
    @DisplayName("카테고리 정보 수정")
    void update() {
        // given
        Category category = Category.builder()
                .id(1L)
                .name("카테고리")
                .build();
        String newName = "의류";
        given(categoryRepository.findById(anyLong()))
                .willReturn(Optional.of(category));

        // when
        categoryService.update(1L, newName);

        // then
        assertThat(category.getName()).isEqualTo("의류");
    }

    @Test
    @DisplayName("카테고리 단건 삭제")
    void delete() {
        // given
        doNothing().when(categoryRepository).deleteById(anyLong());

        // when
        categoryService.delete(1L);

        // then
        then(categoryRepository).should().deleteById(1L);
    }
}