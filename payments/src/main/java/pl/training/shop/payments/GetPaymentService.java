package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PACKAGE;

@RequiredArgsConstructor(access = PACKAGE)
class GetPaymentService  implements GetPaymentUseCase {

    private final PaymentsReader paymentsReader;

    @Override
    public Payment getById(String id) {
        return paymentsReader.getById(id)
                .orElseThrow(PaymentNotFoundException::new);
    }

}
