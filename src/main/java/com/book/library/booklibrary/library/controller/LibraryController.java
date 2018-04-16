package com.book.library.booklibrary.library.controller;

import com.book.library.booklibrary.library.model.DTO.EditLibraryDetails;
import com.book.library.booklibrary.library.model.viewmodel.LibraryDetailsViewModel;
import com.book.library.booklibrary.library.service.library.LibraryServiceInterface;
import com.book.library.booklibrary.user.service.UserServiceInterface;
import jdk.nashorn.api.scripting.JSObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public String allLibraries(@PageableDefault(size = 2) Pageable pageable, Model model) {
        model.addAttribute("libraries", this.userService.getUsersByRole("library", pageable));
        model.addAttribute("pageable", pageable);
        return "library/list";
    }

    // TODO: 16.4.2018 г. maybe delete library by admin
    
    @PreAuthorize("hasAnyRole('ROLE_LIBRARY')")
    @GetMapping("/editDetails/{libraryName}")
    public String editLibraryDetails(Model model, @PathVariable(name = "libraryName") String libraryName, Principal principal) throws Exception {
        model.addAttribute("library", this.libraryService.getEditUserInfo(libraryName, principal));
        model.addAttribute("libId", libraryName);
        return "library/edit_details";
    }

    @PreAuthorize("hasAnyRole('ROLE_LIBRARY')")
    @PostMapping("/editDetails/{libraryId}")
    public String processLibraryDetails(@Valid @ModelAttribute("library") EditLibraryDetails library, BindingResult bindingResult, @PathVariable(name = "libraryId") String libraryId, Principal principal, Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            model.addAttribute("library", library);
            model.addAttribute("libId", libraryId);
            return "library/edit_details";
        }
        Long libraryDetails = this.libraryService.editLibraryDetails(libraryId, library, principal);
        return "redirect:/libraries/profileDetails/"+libraryDetails;
    }

    @GetMapping("/profileDetails/{libraryId}")
    public String libraryDetails(@PathVariable(name = "libraryId") Long libraryId, Model model) throws Exception {
        JSONObject googleMapJsonObject = null;
        LibraryDetailsViewModel libraryDetails = this.libraryService.getLibraryDetails(libraryId);

        googleMapJsonObject = new JSONObject();

        JSONObject libraryMapInfo=  new JSONObject()
                        .put("title", libraryDetails.getUsername())
                        .put("description", libraryDetails.getLibraryDescription())
                        .put("email", libraryDetails.getEmail())
                        .put("coordinates", new JSONObject()
//                                .put("lat",libraryDetails.getLatitude())
//                                .put("lng",libraryDetails.getLongitude()));
                                .put("lat",libraryDetails.getLatitude())
                                .put("lng",libraryDetails.getLongitude()));
        googleMapJsonObject.put("library",libraryMapInfo);
        model.addAttribute("library", libraryDetails);
        model.addAttribute("geoJson", googleMapJsonObject.toString());
        String de = "das";
        return "library/map";
    }

}
