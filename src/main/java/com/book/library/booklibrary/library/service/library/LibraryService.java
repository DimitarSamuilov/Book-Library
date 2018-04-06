package com.book.library.booklibrary.library.service.library;

import com.book.library.booklibrary.library.model.DTO.EditLibraryDetails;
import com.book.library.booklibrary.library.model.viewmodel.LibraryDetailsViewModel;
import com.book.library.booklibrary.library.repository.LibraryRepository;
import com.book.library.booklibrary.user.enums.UserType;
import com.book.library.booklibrary.user.model.entity.Role;
import com.book.library.booklibrary.user.model.entity.User;
import com.book.library.booklibrary.user.service.UserServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class LibraryService implements LibraryServiceInterface {

    private UserServiceInterface userService;
    private LibraryRepository libraryRepository;
    private ModelMapper modelMapper;

    public LibraryService(UserServiceInterface userService, LibraryRepository libraryRepository, ModelMapper modelMapper) {
        this.userService = userService;
        this.libraryRepository = libraryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long editLibraryDetails(EditLibraryDetails libraryDetails, Principal principal) {
        return null;
    }

    @Override
    public EditLibraryDetails getEditUserInfo(Long id, Principal principal) throws Exception {
        Optional<User> libOptional = this.userService.getUseById(id);
        if (!libOptional.isPresent()) {

            throw new Exception("User not found");
        }
        if (!principal.getName().equals(libOptional.get().getUsername())) {
            throw new Exception("Cannot edit this user");
        }
        if (libOptional.get().getRoles().stream().noneMatch(role -> role.getName().equalsIgnoreCase(UserType.LIBRARY.toString()))) {
            throw new Exception("Not a Library user");
        }

        EditLibraryDetails libraryDetails = new EditLibraryDetails();
        if(libOptional.get().getLibrary()!=null){
            this.modelMapper.map(libOptional.get().getLibrary(), EditLibraryDetails.class);
        }
        libraryDetails.setEmail(libOptional.get().getEmail());
        return libraryDetails;
    }


}
