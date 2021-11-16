package pl.training.shop.commons.rest;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@RequiredArgsConstructor
public class ExceptionDto {

    @NonNull
    String description;
    LocalDateTime timestamp = LocalDateTime.now();

}
