package com.book.library.booklibrary.library.service.library;

import com.book.library.booklibrary.library.model.DTO.EditLibraryDetails;
import com.book.library.booklibrary.library.model.viewmodel.LibraryDetailsViewModel;
import com.book.library.booklibrary.user.model.DTO.UserDTO;

import java.security.Principal;
import java.util.List;

public interface LibraryServiceInterface {

    Long editLibraryDetails(EditLibraryDetails libraryDetails , Principal principal);

    EditLibraryDetails getEditUserInfo(Long id,Principal principal) throws Exception;

}
