package com.book.library.booklibrary.library.model.DTO;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class EditLibraryDetails {

    private static final String LATITUDE_ERROR_MESSAGE = "Latitude values range in -90 -> 90";
    private static final String LONGITUDE_ERROR_MESSAGE = "Longitude values range in -90 -> 90";
    private static final String EMPTY_FIELD_ERROR = "Field cannot be empty";
    private static final String SHORT_DESCRIPTION_MESSAGE = "Description must be at least 10 chars";

    @NotEmpty(message = EMPTY_FIELD_ERROR)
    @Max(value = 180, message = LONGITUDE_ERROR_MESSAGE)
    @Min(value = -180, message = LONGITUDE_ERROR_MESSAGE)
    private Double longitude;

    @NotEmpty(message = EMPTY_FIELD_ERROR)
    @Max(value = 90, message = LATITUDE_ERROR_MESSAGE)
    @Min(value = -90, message = LATITUDE_ERROR_MESSAGE)
    private Double latitude;

    @Length(min = 10, message = SHORT_DESCRIPTION_MESSAGE)
    @NotEmpty(message = EMPTY_FIELD_ERROR)
    private String libraryDescription;


    @NotEmpty(message = "Email cannot be empty")
    private String email;


    public EditLibraryDetails() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getLibraryDescription() {
        return libraryDescription;
    }

    public void setLibraryDescription(String libraryDescription) {
        this.libraryDescription = libraryDescription;
    }

    @Override
    public String toString() {
        return "EditLibraryDetails{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                ", libraryDescription='" + libraryDescription + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
