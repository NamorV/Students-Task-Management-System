package com.hellokoding.auth.web;

import com.hellokoding.auth.model.*;
import com.hellokoding.auth.service.*;
import com.hellokoding.auth.validator.CourseValidator;
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
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private CourseValidator courseValidator;

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
        List<Faculty> faculty = facultyService.findAll();
        model.addAttribute("faculty", faculty);

        return "welcome";
    }

    @RequestMapping(value = { "/delete-document-{docId}" }, method = RequestMethod.GET)
    public String deleteDocument(@PathVariable int docId) {
        userDocumentService.delete(docId);

        return "redirect:/";
    }

    @RequestMapping(value = {"/courses-{facultyId}"}, method = RequestMethod.GET)
    public String courses(ModelMap model, @PathVariable int facultyId) {
        List<Course> courses = courseService.findByFacultyId(facultyId);
        model.addAttribute("courses", courses);

        List<Integer> faculty_Id = new ArrayList<>();
        faculty_Id.add(facultyId);
        model.addAttribute("facultyId", faculty_Id);
        return "courses";
    }


    @RequestMapping(value = {"/add-course-{facultyId}"}, method = RequestMethod.GET)
    public String addCourses(ModelMap model, @PathVariable int facultyId) {
        model.addAttribute("courseForm", new Course());

        return "addCourse";
    }

    @RequestMapping(value = "/add-course-{facultyId}", method = RequestMethod.POST)
    public String addCourses(@ModelAttribute("courseForm") Course courseForm, @PathVariable int facultyId,
                             BindingResult bindingResult) {
        courseValidator.validate(courseForm, bindingResult);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = null;
        int id = 0;

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            user = userService.findByUsername(username);
            id = user.getId();
        }

        if (bindingResult.hasErrors()) {
            return "addCourse";
        } else {
            courseForm.setFaculty_id(facultyId);
            courseForm.setTeacher_id(id);
            courseService.save(courseForm);
        }

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

    @RequestMapping(value = {"/documents-{courseId}"}, method = RequestMethod.GET)
    public String documents(ModelMap model, @PathVariable int courseId) throws IOException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = null;
        int id = 0;

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            user = userService.findByUsername(username);
            id = user.getId();
        }

        if(roleService.isStudent(id)) {
            List<UserDocument> documents = userDocumentService.findAllForStudent(courseId, id);
            model.addAttribute("documents", documents);
        } else {
            List<UserDocument> documents = userDocumentService.findAllForTeacher(courseId);
            model.addAttribute("documents", documents);
        }



        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileBucket", fileModel);

        return "documents";
    }

    @RequestMapping(value = {"/documents-{courseId}"}, method = RequestMethod.POST)
    public String documents(ModelMap model, @RequestParam("file") MultipartFile file,
                            @PathVariable("courseId") int courseId, @ModelAttribute("fileBucket") FileBucket fileBucket) throws IOException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = null;
        int id = 0;
        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileBucket", fileModel);

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            user = userService.findByUsername(username);
            id = user.getId();
        }

        saveDocument(fileBucket, id, courseId);

        return "redirect:/documents";
    }

    private void saveDocument(FileBucket fileBucket, int id, int courseId) throws IOException{

        UserDocument document = new UserDocument();

        MultipartFile multipartFile = fileBucket.getFile();

        document.setName(multipartFile.getOriginalFilename());
        document.setDescription(fileBucket.getDescription());
        document.setType(multipartFile.getContentType());
        document.setContent(multipartFile.getBytes());

        userDocumentService.save(document, id, courseId);
    }
}