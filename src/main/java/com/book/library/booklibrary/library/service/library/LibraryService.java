package com.book.library.booklibrary.library.service.library;

import com.book.library.booklibrary.home.exception.NoSuchResourceException;
import com.book.library.booklibrary.library.model.DTO.EditLibraryDetails;
import com.book.library.booklibrary.library.model.entity.Library;
import com.book.library.booklibrary.library.model.viewmodel.LibraryDetailsViewModel;
import com.book.library.booklibrary.library.repository.LibraryRepository;
import com.book.library.booklibrary.user.enums.UserType;
import com.book.library.booklibrary.user.model.entity.Role;
import com.book.library.booklibrary.user.model.entity.User;
import com.book.library.booklibrary.user.repository.UserRepository;
import com.book.library.booklibrary.user.service.UserServiceInterface;
import org.modelmapper.AbstractProvider;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.Mapping;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.security.Principal;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

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


    public Library getLibraryByUsername(String username) throws NoSuchResourceException {
        Optional<User> libUser = this.userRepository.findByUsername(username);
        if (libUser.isPresent()) {
            String deb = "das";
            if (libUser.get().getLibrary() != null) {
                return libUser.get().getLibrary();
            }
        } else {
            throw new NoSuchResourceException("No such library ");
        }
        return null;
    }

    @Override
    public Long editLibraryDetails(String username, EditLibraryDetails libraryDetails, Principal principal) throws Exception {
        Optional<User> userOptional = this.userRepository.findByUsername(username);
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
    public EditLibraryDetails getEditUserInfo(String id, Principal principal) throws Exception {
        Optional<User> libOptional = this.userRepository.findByUsername(id);
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
            libraryDetails = this.modelMapper.map(libOptional.get().getLibrary(), EditLibraryDetails.class);
        }
        libraryDetails.setEmail(libOptional.get().getEmail());
        return libraryDetails;
    }

    @Override
    public LibraryDetailsViewModel getLibraryDetails(Long id) throws NoSuchResourceException {

        Optional<User> userOptional = this.userRepository.findById(id);

        if (!userOptional.isPresent()) {
            throw new NoSuchResourceException("No such Library");
        }

        if (userOptional.get().getRoles().stream().noneMatch(role -> role.getName().equalsIgnoreCase(UserType.LIBRARY.toString()))) {
            throw new NoSuchResourceException("this is not a library");
        }

        if (userOptional.get().getLibrary() == null) {
            throw new NoSuchResourceException("Library profile is not ready for users");
        }

        LibraryDetailsViewModel libraryDetailsViewModel = this.modelMapper.map(userOptional.get().getLibrary(), LibraryDetailsViewModel.class);
        this.modelMapper.map(userOptional.get(), libraryDetailsViewModel);

        return libraryDetailsViewModel;
    }

    @Async
    @Override
    public CompletableFuture<Void> deleteLibraryByName(String libraryName) {
        try {
            Optional<Library> libraryOptional = this.libraryRepository.findFirstByUserUsername(libraryName);

            if (libraryOptional.isPresent()) {
                this.userRepository.deleteById(libraryOptional.get().getUser().getId());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return CompletableFuture.completedFuture(null);
    }
}
