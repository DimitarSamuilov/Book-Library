package com.book.library.booklibrary.library.service.library;

import com.book.library.booklibrary.library.model.DTO.EditLibraryDetails;
import com.book.library.booklibrary.library.model.entity.Library;
import com.book.library.booklibrary.library.model.viewmodel.LibraryDetailsViewModel;
import com.book.library.booklibrary.library.repository.LibraryRepository;
import com.book.library.booklibrary.user.enums.UserType;
import com.book.library.booklibrary.user.model.entity.Role;
import com.book.library.booklibrary.user.model.entity.User;
import com.book.library.booklibrary.user.repository.UserRepository;
import com.book.library.booklibrary.user.service.UserServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Optional;

@Service
@Transactional
public class LibraryService implements LibraryServiceInterface {

    private UserRepository userRepository;
    private LibraryRepository libraryRepository;
    private ModelMapper modelMapper;

    public LibraryService(UserRepository userRepository, LibraryRepository libraryRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.libraryRepository = libraryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long editLibraryDetails(Long id, EditLibraryDetails libraryDetails, Principal principal) throws Exception {
        Optional<User> userOptional = this.userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new Exception("invalid id");
        }
        if (!principal.getName().equals(userOptional.get().getUsername())) {
            throw new Exception("You annot edit other library details");
        }

        User userEntity = userOptional.get();
        userEntity.setEmail(libraryDetails.getEmail());

        Library library = userEntity.getLibrary();
        if (library == null) {
            library = new Library();
        }
        library.setLatitude(libraryDetails.getLatitude());
        library.setLongitude(libraryDetails.getLongitude());
        library.setLibraryDescription(libraryDetails.getLibraryDescription());
        library.setUser(userEntity);
//        userEntity.setLibrary(library);
        this.libraryRepository.save(library);
        this.userRepository.save(userEntity);
        return userEntity.getId();
    }

    @Override
    public EditLibraryDetails getEditUserInfo(Long id, Principal principal) throws Exception {
        Optional<User> libOptional = this.userRepository.findById(id);
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
        if (libOptional.get().getLibrary() != null) {
            System.out.println(libOptional.get().getLibrary());
            libraryDetails = this.modelMapper.map(libOptional.get().getLibrary(), EditLibraryDetails.class);
        }
        libraryDetails.setEmail(libOptional.get().getEmail());
        return libraryDetails;
    }


}
