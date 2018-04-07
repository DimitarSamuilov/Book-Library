package com.book.library.booklibrary.library.controller;

import com.book.library.booklibrary.library.model.DTO.EditLibraryDetails;
import com.book.library.booklibrary.library.service.library.LibraryServiceInterface;
import com.book.library.booklibrary.user.service.UserServiceInterface;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.security.Principal;


@Controller
@RequestMapping("/libraries")
public class LibraryController {

    private UserServiceInterface userService;
    private LibraryServiceInterface libraryService;

    public LibraryController(UserServiceInterface userService, LibraryServiceInterface libraryService) {
        this.userService = userService;
        this.libraryService = libraryService;
    }

    @GetMapping("/all")
    public String allLibraries(@PageableDefault(size = 2) Pageable pageable, Model model) {
        model.addAttribute("libraries", this.userService.getUsersByRole("library", pageable));
        model.addAttribute("pageable", pageable);

        return "library/list";
    }

    @GetMapping("/editDetails/{libraryName}")
    public String editLibraryDetails(Model model, @PathVariable(name = "libraryName") String libraryName, Principal principal) throws Exception {
        model.addAttribute("library", this.libraryService.getEditUserInfo(libraryName, principal));
        model.addAttribute("libId", libraryName);
        System.out.println(this.libraryService.getEditUserInfo(libraryName, principal));
        return "library/edit_details";
    }

    @PostMapping("/editDetails/{libraryId}")
    public String processLibraryDetails(@Valid @ModelAttribute("library") EditLibraryDetails library, BindingResult bindingResult, @PathVariable(name = "libraryId") String libraryId, Principal principal, Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            model.addAttribute("library", library);
            model.addAttribute("libId", libraryId);
            return "library/edit_details";
        }
        this.libraryService.editLibraryDetails(libraryId, library, principal);
//        @todo redirect to lib profile page
        return "redirect:/";
    }

}
