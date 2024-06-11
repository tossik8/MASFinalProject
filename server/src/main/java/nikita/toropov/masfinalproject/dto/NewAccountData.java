package nikita.toropov.masfinalproject.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NewAccountData {

    private long clientId;
    @Min(0)
    private Integer overdraftLimit;
    @Min(0)
    private Float interestRate;
    @Size(min = 1)
    private String investmentObjective;
    @NotBlank
    private String type;
}
