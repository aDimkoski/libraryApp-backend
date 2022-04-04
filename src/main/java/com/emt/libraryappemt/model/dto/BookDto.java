package com.emt.libraryappemt.model.dto;

import com.emt.libraryappemt.model.Category;
import lombok.Data;

@Data
public class BookDto {
    private String name;

    private Category category;

    private Long author;

    private int availableCopies;
}
