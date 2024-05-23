package nikita.toropov.masfinalproject.model.person;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Credentials {

    @Column(unique = true)
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8)
    private String password;

}
