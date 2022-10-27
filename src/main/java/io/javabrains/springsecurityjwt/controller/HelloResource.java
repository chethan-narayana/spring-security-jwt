package io.javabrains.springsecurityjwt.controller;


import io.javabrains.springsecurityjwt.model.AutheticationRequest;
import io.javabrains.springsecurityjwt.model.AutheticationResponse;
import io.javabrains.springsecurityjwt.services.MyUserDetailsService;
import io.javabrains.springsecurityjwt.util.JwUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {

    @Autowired
    private AuthenticationManager autheticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwUtil jwtTokenUtil;

    @RequestMapping("/hello")
    public String hello(){
        return "Hello World";
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AutheticationRequest autheticationRequest) throws Exception{
    try {
        autheticationManager.authenticate(new UsernamePasswordAuthenticationToken(autheticationRequest.getUserName(), autheticationRequest.getPassword()));
    }catch (BadCredentialsException e){
        throw new Exception("Incorrect username or password");
    }
    final UserDetails userDetails = userDetailsService.loadUserByUsername(autheticationRequest.getUserName());
    final String jwt = jwtTokenUtil.generateToken(userDetails);

    return ResponseEntity.ok(new AutheticationResponse(jwt));
    }

}
