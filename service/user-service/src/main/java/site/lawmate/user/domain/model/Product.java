package site.lawmate.user.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity(name = "products")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@ToString(exclude = {"id"})
public class Product {
    @Id
    @Column
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemName;
    private Long price;
    private String image;

    @OneToMany(mappedBy = "product")
    private List<UserPayment> payments;
}
