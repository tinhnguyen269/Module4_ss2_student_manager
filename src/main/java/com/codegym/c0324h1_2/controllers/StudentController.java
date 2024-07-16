package com.codegym.c0324h1_2.controllers;

import com.codegym.c0324h1_2.models.Student;
import com.codegym.c0324h1_2.services.IStudentService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

//Đánh dấu đây là 1 controller (1 bean)
//Annotation Controller
//Bài tập về nhà: phân biệt @Component, @Controller, @Service, @Repository (đều sử dụng để tạo bean)
@Controller
@RequestMapping(value = "/student")
public class StudentController {

//    Cơ chế DI (Dependence injection): Tiêm phụ thuộc để giảm sự phụ thuộc
//    DI: Field/Interface
//    DI: Setter
//    DI: Constructor
    @Autowired
    private IStudentService studentService;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("")
    public String displayAllStudents(Model model) {
        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);
//        ModelAndView modelAndView = new ModelAndView("student/list");
//        modelAndView.addObject("students", studentService.findAll());
//        modelAndView.addObject("student", new Student());
//        return new ModelAndView("student/list", "students", studentService.findAll());
//        Model, ModelMap và ModelAndView
        return "student/list";
    }

//    @RequestMapping (value = "/create", method = RequestMethod.GET)
    @GetMapping("/create")
    public String viewCreate() {
        return "student/create";
    }

    @PostMapping("/create")
    public String newStudent(@RequestParam("name") String name,
                             @RequestParam("address")String address,
                             @RequestParam("score") Float score,
                             RedirectAttributes redirect) {
        Student student = new Student(name, address, score);
        studentService.save(student);
        redirect.addFlashAttribute("message", "Thêm mới thành công");
        return "redirect:/student";
    }

    @GetMapping("/update/{id}")
    public String viewUpdate(@PathVariable Long id,Model model) {
        Student student = studentService.findById(id);
        model.addAttribute("student",student);
        return "/student/edit";
    }
    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id,
                                @RequestParam String name,
                                @RequestParam String address,
                                @RequestParam Float score,
                                RedirectAttributes redirect){
        Student student = new Student(id,name, address, score);
        studentService.update(student);

        List<Student> students = studentService.findAll();
        redirect.addFlashAttribute("students", students);
        redirect.addFlashAttribute("message","Cap nhat thanh cong");
        return "redirect:/student";
    }
    @GetMapping("delete/{id}")
    public String deleteStudent(@PathVariable Long id,RedirectAttributes redirect){
        studentService.deleteStudent(id);

        List<Student> students = studentService.findAll();
        redirect.addFlashAttribute("students", students);
        redirect.addFlashAttribute("message","Cap nhat thanh cong");
        return "redirect:/student";
    }


}
