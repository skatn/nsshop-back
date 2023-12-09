package namsu.nsshop.domain.category.dao;

import namsu.nsshop.domain.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
