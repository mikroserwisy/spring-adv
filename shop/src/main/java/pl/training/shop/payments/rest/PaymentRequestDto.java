package pl.training.shop.payments.rest;

import lombok.Data;
import pl.training.shop.commons.BaseValidation;
import pl.training.shop.commons.ExtendedValidation;
import pl.training.shop.commons.money.Money;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Map;

@Data
class PaymentRequestDto {

    @Min(value = 1, groups = ExtendedValidation.class)
    private Long requestId;
    @Pattern(regexp = "\\d+ PLN", groups = ExtendedValidation.class)
    @Money(groups = BaseValidation.class)
    private String value;
    @NotEmpty(groups = {BaseValidation.class, ExtendedValidation.class})
    private Map<String, String> additionalInfo;

}
