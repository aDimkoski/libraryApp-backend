package com.emt.libraryappemt.model.dto;

import com.emt.libraryappemt.model.Country;
import lombok.Data;


@Data
public class AuthorDto {
    private String name;

    private String surname;

    private Long country;
}
