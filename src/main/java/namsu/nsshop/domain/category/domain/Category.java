package namsu.nsshop.domain.category.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ColumnDefault("0")
    private int position;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category parent;

    public void changeName(String name) {
        this.name = name;
    }
}
