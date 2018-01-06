package com.hellokoding.auth.web;

import com.hellokoding.auth.model.FileBucket;
import com.hellokoding.auth.model.User;
import com.hellokoding.auth.model.UserDocument;
import com.hellokoding.auth.service.SecurityService;
import com.hellokoding.auth.service.UserDocumentService;
import com.hellokoding.auth.service.UserService;
import com.hellokoding.auth.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserDocumentService userDocumentService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(ModelMap model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        } else {
            userService.save(userForm);
        }

        //securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(ModelMap model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = null;
        int id = 0;

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            user = userService.findByUsername(username);
            id = user.getId();
        }

        List<UserDocument> documents = userDocumentService.findAllByUserId(id);
        model.addAttribute("documents", documents);

        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileBucket", fileModel);

        return "welcome";
    }

    @RequestMapping(value = { "/delete-document-{docId}" }, method = RequestMethod.GET)
    public String deleteDocument(@PathVariable int docId) {
        userDocumentService.delete(docId);

        return "redirect:/";
    }

    @RequestMapping(value = { "/download-document-{docId}" }, method = RequestMethod.GET)
    public String downloadDocument(@PathVariable int docId, HttpServletResponse response) throws IOException {
        UserDocument document = userDocumentService.findById(docId);
        response.setContentType(document.getType());
        response.setContentLength(document.getContent().length);
        response.setHeader("Content-Disposition","attachment; filename=\"" + document.getName() +"\"");

        FileCopyUtils.copy(document.getContent(), response.getOutputStream());

        return "redirect:/";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.POST)
    public String welcome(ModelMap model, @RequestParam("file") MultipartFile file, @ModelAttribute("fileBucket") FileBucket fileBucket) throws IOException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = null;
        int id = 0;
        List<UserDocument> documents = userDocumentService.findAllByUserId(id);
        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileBucket", fileModel);
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            user = userService.findByUsername(username);
            id = user.getId();
        }
        saveDocument(fileBucket, id);

        return "redirect:/";
    }

    private void saveDocument(FileBucket fileBucket, int id) throws IOException{

        UserDocument document = new UserDocument();

        MultipartFile multipartFile = fileBucket.getFile();

        document.setName(multipartFile.getOriginalFilename());
        document.setDescription(fileBucket.getDescription());
        document.setType(multipartFile.getContentType());
        document.setContent(multipartFile.getBytes());

        userDocumentService.save(document, id);
    }
}