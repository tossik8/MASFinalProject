package nikita.toropov.masfinalproject.model.loan;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class House extends Collateral{

    @NotBlank
    private String address;

    @Min(0)
    private float area;

    @Min(0)
    private int rooms;

    @Builder
    public House(int price, int yearBuilt, String address, float area, int rooms){
        super(price, yearBuilt);
        setAddress(address);
        setArea(area);
        setRooms(rooms);
    }
}
