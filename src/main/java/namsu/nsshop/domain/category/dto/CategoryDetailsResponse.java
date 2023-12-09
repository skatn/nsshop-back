package namsu.nsshop.domain.category.dto;

import lombok.Getter;
import namsu.nsshop.domain.category.domain.Category;

@Getter
public class CategoryDetailsResponse {
    private Long id;
    private String name;
    private int position;


    public static CategoryDetailsResponse from(Category category) {
        CategoryDetailsResponse categoryDetailsResponse = new CategoryDetailsResponse();
        categoryDetailsResponse.id = category.getId();
        categoryDetailsResponse.name = category.getName();
        categoryDetailsResponse.position = category.getPosition();

        return categoryDetailsResponse;
    }
}
