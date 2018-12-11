package com.springmvc.app.controllers;

import com.springmvc.app.model.User;
import com.springmvc.app.repository.UserCatalog;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Simple Controller
 * @author Pedro Caldeira
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserCatalog UserCatalog;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<User>> login() {
       List<User> users = UserCatalog.getAllUsers();
        return users.isEmpty()? new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

}

