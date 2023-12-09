package namsu.nsshop.domain.category.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CategoryTest {

    @Test
    @DisplayName("name 변경")
    void updateName() {
        // given
        String name = "카테고리";
        Category category = new Category();

        // when
        category.changeName(name);

        // then
        assertThat(category.getName()).isEqualTo("카테고리");
    }

}