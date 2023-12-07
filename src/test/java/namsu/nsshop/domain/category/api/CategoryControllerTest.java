package namsu.nsshop.domain.category.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import namsu.nsshop.domain.category.application.CategoryService;
import namsu.nsshop.domain.category.dto.CategoryCreateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = CategoryController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
@AutoConfigureRestDocs
class CategoryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CategoryService categoryService;

    ObjectMapper objectMapper;

    @BeforeEach
    void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("카테고리 추가 API 요청")
    void create() throws Exception {
        // given
        CategoryCreateRequest request = CategoryCreateRequest.builder()
                .name("카테고리")
                .parentId(null)
                .position(0)
                .build();
        given(categoryService.create(anyString(), any(), anyInt()))
                .willReturn(1L);

        // when
        ResultActions result = mockMvc.perform(post("/admin/category")
                .content(objectMapper.writeValueAsBytes(request))
                .contentType(MediaType.APPLICATION_JSON));

        // then
        result.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.categoryId").value(1))
                .andDo(document("admin/category/create",
                        requestFields(
                                fieldWithPath("name").type(STRING).description("카테고리명"),
                                fieldWithPath("parentId").type(NUMBER).description("상위 카테고리 ID, 없으면 null").optional(),
                                fieldWithPath("position").type(NUMBER).description("출력 순서")
                        ),
                        responseFields(
                                fieldWithPath("categoryId").type(NUMBER).description("생성된 카테고리 ID")
                        )
                ));
    }
}