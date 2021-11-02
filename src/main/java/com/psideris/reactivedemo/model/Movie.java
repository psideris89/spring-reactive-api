package com.psideris.reactivedemo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public record Movie(@Id String id, String name, String releaseYear) {
}
