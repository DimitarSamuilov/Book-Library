package com.book.library.booklibrary.library.service.library;

import com.book.library.booklibrary.library.model.DTO.EditLibraryDetails;
import com.book.library.booklibrary.library.model.viewmodel.LibraryDetailsViewModel;
import com.book.library.booklibrary.user.model.DTO.UserDTO;

import java.security.Principal;
import java.util.List;

public interface LibraryServiceInterface {

    Long editLibraryDetails(String username,EditLibraryDetails libraryDetails , Principal principal) throws Exception;

    EditLibraryDetails getEditUserInfo(String username,Principal principal) throws Exception;

}
