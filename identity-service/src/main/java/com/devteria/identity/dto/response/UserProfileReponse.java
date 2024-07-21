package com.devteria.identity.dto.response;

import java.time.LocalDate;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfileReponse {
    String id;
    String firstName;
    String lastName;
    LocalDate dob;
    String city;
}
