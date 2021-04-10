package ru.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.server.enums.Authority;
import ru.server.services.ITokenService;
import ru.server.services.IUserService;

@Secured({Authority.CAN_ENTER})
@RestController
public class GreetingController {
   @Autowired
   private ITokenService tokenService;
   @Autowired
   private IUserService userService;

   public static class Greeting {
      private String text;
      public Greeting(String text) {
         this.text = text;
      }

      public Greeting() {
      }

      public String getText() {
         return text;
      }

      public void setText(String text) {
         this.text = text;
      }

   }
   @GetMapping("/api/greeting")
   public ResponseEntity<Greeting> greeting(@RequestHeader("Authorization") String token) {
      System.out.println("Trying to start");
      UserDetails u = userService.loadUserByUsername(tokenService.getLoginFromToken(token.substring(7)));
      return new ResponseEntity<>(new Greeting(String.format("Привет, %s", u.getUsername())), HttpStatus.OK);
   }
}
