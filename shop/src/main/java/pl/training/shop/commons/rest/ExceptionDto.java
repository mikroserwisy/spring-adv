package pl.training.shop.commons.rest;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class ExceptionDto {

    @NonNull
    private String description;
    private LocalDateTime timestamp = LocalDateTime.now();

}
