package com.ltweb.webjob.controllers;

import com.ltweb.webjob.dtos.*;
import com.ltweb.webjob.entities.Candidate;
import com.ltweb.webjob.entities.Role;
import com.ltweb.webjob.mappers.UserMapper;
import com.ltweb.webjob.securities.JwtTokenProvider;
import com.ltweb.webjob.services.CandidateService;
import com.ltweb.webjob.services.CompanyService;
import com.ltweb.webjob.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class AuthController {

    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public AuthController(UserService userService,CompanyService companyService,CandidateService candidateService) {
        this.companyService = Objects.requireNonNull(companyService);
        this.userService = Objects.requireNonNull(userService);
        this.candidateService = Objects.requireNonNull(candidateService);

    }

    @Autowired
    private final CompanyService companyService;

    @Autowired
    private final CandidateService candidateService;

    /**
     * Login
     * @return JWT access token, token type, role, isFirstLogin
     */

    @PostMapping("/auth/signup")
    public ResponseEntity<String> singup(@RequestBody @Valid SignupDto signupDto) {
        UserDto userDto= userService.createUser(signupDto);

        if(signupDto.getRole()== Role.COMPANY){
            CompanyRequestDto companyRequestDto =new CompanyRequestDto();
            companyService.save(companyRequestDto,signupDto.getEmail());
        }
        if(signupDto.getRole()== Role.CANDIDATE){
            CandidateRequestDto candidateRequestDto =new CandidateRequestDto();
            candidateService.save(candidateRequestDto,signupDto.getEmail());
        }

        return new ResponseEntity<>("Sign up successfully", HttpStatus.OK);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody @Valid LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Get user info
        UserDto userDto = userService.getByEmail(loginDto.getEmail());
        String role = userDto.getRole().name();

        // Get token from tokenProvider
        String token = jwtTokenProvider.generateToken(authentication);

        return new ResponseEntity<>(new JwtAuthResponse(role, token), HttpStatus.OK);
    }

    /**
     * Change Password
     * @param changePasswordDto includes old password and new password
     * @param principal Principal of Spring Security which has user details (username, roles)
     * @return userDto user information
     */
    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/auth/change-password")
    public ResponseEntity<UserDto> changePassword(@RequestBody @Valid ChangePasswordDto changePasswordDto, Principal principal){
        String email = principal.getName();
        UserDto userDto = userService.changePassword(changePasswordDto, email);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user")
    public ResponseEntity<UserDto> getByEmail (Principal principal){
        String email = principal.getName();
        return new ResponseEntity<>(userService.getByEmail(email), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/user")
    public ResponseEntity<UserDto> editUser (@RequestBody UserRequestDto userRequestDto,Principal principal){
        String email = principal.getName();
        return new ResponseEntity<>(userService.editUser(userRequestDto,email), HttpStatus.OK);
    }

}
