package com.example.demo.controllers;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/hello")
public class HelloController {
 // GET /api/hello
 @GetMapping
 public Map<String, String> hello() {
 return Map.of("message", "Hola Spring Boot");
 }
 // GET /api/hello/{name}
 @GetMapping("/{name}")
 public Map<String, String> greetByName(@PathVariable String name) {
 return Map.of("message", "Hola " + name);
 }
 // GET /api/hello/echo?msg=...
 @GetMapping("/echo")
 public Map<String, String> echo(@RequestParam(defaultValue = "sin mensaje")
String msg) {
 return Map.of("echo", msg);
 }
}