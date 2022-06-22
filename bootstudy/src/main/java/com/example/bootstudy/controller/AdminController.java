package com.example.bootstudy.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.bootstudy.model.Role;
import com.example.bootstudy.model.User;
import com.example.bootstudy.service.RoleService;
import com.example.bootstudy.service.UserService;

import javax.validation.Valid;
import java.util.Set;

@Controller
//@RestController   //почему страница не полностью  отображается
@RequestMapping("/")

public class AdminController {


    @GetMapping(value="/login")
    public  String getLoginPage() {
        System.out.println("Log::");
        return "login";
    }

    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final RoleService roleService;


    public AdminController(UserService userService, UserDetailsService userDetailsService,
                           RoleService roleService) {

        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String getUsers(Model model,@AuthenticationPrincipal UserDetails logedInUser) {

        User user = (User) userDetailsService.loadUserByUsername(logedInUser.getUsername());
        System.out.println("ID: " +user.getId() +" " +user.toString() +"roles: "+ user.getRoles());
        model.addAttribute("user",new User());


        model.addAttribute("usersSet",userService.getAll());
        model.addAttribute("RolesSet",roleService.getAllRoles());
        return "admins_pages/listBS";


    }


    @PostMapping(value="/saveUser")
    public String saveUser(@ModelAttribute("user") User user,
                           @RequestParam(value="roles_List") Long [] ids) {
        user.setRoles( roleService.getRolesByIds(ids));
        userService.saveUser(user);
        System.out.println("User was saved succesfully");
        return "redirect:/admin";
    }


   @PostMapping(value = "/edit/{id}")
   public String update(@ModelAttribute("user") User user,
//                         @PathVariable(value = "id") Long id,
                        @RequestParam(value = "roles_List") Long [] ids){
      //user= userService.getUserById(id);
      //  model.addAttribute("user",user);

       user.setRoles(roleService.getRolesByIds(ids));
       userService.update(user);
        return "redirect:/admin";

    }


//    @PostMapping("/delete/{id}")
//    public String delete(@PathVariable("id") Long id) {
//        System.out.println("user with id " +id +" " + userService.getUserById(id) +"was removed");
//        userService.deleteUserById(id);
//        return "redirect:/admin";
//    }

    @PostMapping("/delete/") //типо тут можно попробовать с requestparam вместо pathvariable
    public String delete(@ModelAttribute("user") User user,@RequestParam(value="idDelete") Long id){
       // model.addAttribute("idDelete",id);
        System.out.println("User with id " +  user.getId());
        user=userService.getUserById(id);
        userService.deleteUser(user);
        return "redirect:/admin";
    }

    //Code for user's page
    @GetMapping("/user")
    public String showUserPage(Model model, @AuthenticationPrincipal UserDetails logedInUser) {
        User user = (User) userDetailsService.loadUserByUsername(logedInUser.getUsername());
        model.addAttribute("user",user);
        return  "userPage";

    }


    //Adding roles





//    @GetMapping("/showFormRoles")
//    public String showFormForAddingRoles(Model model, Role role) {
//        model.addAttribute("Role", role);
//        return "admins_pages/newRole";
//    }
//
//
//    @PostMapping("saveRole")
//    public String saveRole( @Valid @ModelAttribute("Role") Role role
//                            ,BindingResult bindingResult)  {
//
//        if (bindingResult.hasErrors()){
//            return  "admins_pages/newRole";
//        }
//
//
//        roleService.saveRole(role);
//        return "redirect:/admin ";
//
//    }
//
//    @GetMapping("editRole/{id}")
//    public String showEditRoleForm(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("Role",roleService.getRoleById(id));
//        return "admins_pages/editRoleForm";
//    }
//
//    @PostMapping("/role{id}")
//    public String updateRole(@Valid @ModelAttribute("Role") Role role,BindingResult bindingResult ) {
//
//        if (bindingResult.hasErrors()) {
//            return  "admins_pages/editRoleForm";
//        }
//
//
//        roleService.update(role);
//        return "redirect:/admin";
//    }
//
//    @GetMapping("/role{id}")
//    public String deleteRole(@PathVariable("id") Long id) {
//        roleService.deleteRoleById(id);
//        return "redirect:/admin";
//    }









}
