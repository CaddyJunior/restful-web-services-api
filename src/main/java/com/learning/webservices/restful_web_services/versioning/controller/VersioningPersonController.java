package com.learning.webservices.restful_web_services.versioning.controller;

import com.learning.webservices.restful_web_services.versioning.model.Personv1;
import com.learning.webservices.restful_web_services.versioning.model.Name;
import com.learning.webservices.restful_web_services.versioning.model.Personv2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    @GetMapping("/v1/person")
    public Personv1 getFirstVersionOfPerson(){
        return new Personv1("Bob Charlie");
    }

    @GetMapping("/v2/person")
    public Personv2 getSecVersionOfPerson(){
        return new Personv2(new Name("Bob", "Charlie"));
    }

    @GetMapping(path = "/person", params = "version=1")
    public Personv1 getFirstVersionOfPersonRequestParam(){
        return new Personv1("Bob Charlie");
    }

    @GetMapping(path = "/person", params = "version=2")
    public Personv2 getSecVersionOfPersonRequestParam(){
        return new Personv2(new Name("Bob", "Charlie"));
    }

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
    public Personv1 getFirstVersionOfPersonRequestHeader(){
        return new Personv1("Bob Charlie");
    }

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
    public Personv2 getSecVersionOfPersonRequestHeader(){
        return new Personv2(new Name("Bob", "Charlie"));
    }

    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
    public Personv1 getFirstVersionOfPersonAcceptHeader(){
        return new Personv1("Bob Charlie");
    }

    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
    public Personv2 getSecVersionOfPersonAcceptHeader(){
        return new Personv2(new Name("Bob", "Charlie"));
    }


}
