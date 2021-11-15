package pl.training.shop.payments.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.training.shop.commons.rest.UriBuilder;
import pl.training.shop.commons.streotype.RestAdapter;
import pl.training.shop.payments.ProcessPaymentUseCase;

import static lombok.AccessLevel.PACKAGE;
import static pl.training.shop.commons.rest.UriBuilder.requestUriWithId;

@RequestMapping("payments")
@RestAdapter
@RequiredArgsConstructor(access = PACKAGE)
class ProcessPaymentUseCaseRestAdapter {

    private final PaymentsRestMapper mapper;
    private final ProcessPaymentUseCase processPaymentUseCase;

    @PostMapping
    ResponseEntity<PaymentDto> process(@RequestBody PaymentRequestDto paymentRequestDto) {
        var paymentRequest = mapper.toModel(paymentRequestDto);
        var payment = processPaymentUseCase.process(paymentRequest);
        var paymentDto = mapper.toDto(payment);
        var locationUri = requestUriWithId(paymentDto.getId());
        return ResponseEntity.created(locationUri).body(paymentDto);
    }

}
