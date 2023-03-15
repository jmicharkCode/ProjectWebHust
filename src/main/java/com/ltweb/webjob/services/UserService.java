package com.ltweb.webjob.services;

import com.ltweb.webjob.dtos.ChangePasswordDto;
import com.ltweb.webjob.dtos.SignupDto;
import com.ltweb.webjob.dtos.UserDto;
import com.ltweb.webjob.dtos.UserRequestDto;
import com.ltweb.webjob.entities.Gender;
import com.ltweb.webjob.entities.Role;
import com.ltweb.webjob.entities.User;
import com.ltweb.webjob.exceptions.BadRequestException;
import com.ltweb.webjob.exceptions.NotFoundException;
import com.ltweb.webjob.mappers.UserMapper;
import com.ltweb.webjob.repositories.UserRepository;
import com.ltweb.webjob.utils.Utils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository);
    }


    public UserDto getByEmail(String email) {

        return UserMapper.toDto(this.userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User with email: " + email + " Not Found")));
    }

    public UserDto getById(int id) {

        return UserMapper.toDto(this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with id: " + id + " Not Found")));
    }

    public UserDto changePassword(ChangePasswordDto changePasswordDto, String email) {

        User user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email: " + email + " Not Found"));


        String oldPassword = changePasswordDto.getOldPassword();
        String newPassword = changePasswordDto.getNewPassword();

        // Check if old password is incorrect
        if (oldPassword != null && !passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new BadRequestException("Old password is incorrect");
        }

        // Check if new password is same as old password
        if (passwordEncoder.matches(newPassword, user.getPassword())) {
            throw new BadRequestException("New password must be different from the current password");
        }

        // Check if user is changing password for the first time

        String encodedNewPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedNewPassword);

        User savedUser = userRepository.save(user);
        return UserMapper.toDto(savedUser);
    }

    /**
     * Create new user
     *
     * @return userDto user information
     */
    public UserDto createUser(SignupDto signupDto) {

        Optional<User> user = userRepository.findByEmail(signupDto.getEmail());
        if(!user.isEmpty())
            throw new BadRequestException("User already exist ");
        String email = signupDto.getEmail();
        String password = signupDto.getPassword();
        String firstName = signupDto.getFirstName();
        String lastName = signupDto.getLastName();
        String birthDate = signupDto.getBirthDate();
        Gender gender = signupDto.getGender();
        String location = signupDto.getLocation();
        Role role = signupDto.getRole();

        if (Utils.containsNumber(firstName) || Utils.containsNumber(lastName)) {
            throw new BadRequestException("First name and Last name must not contain number");
        }

        if (Utils.isUserUnder18(birthDate)&& role.equals("OWNER")) {
            throw new BadRequestException("Owner is under 18. Please select a different date");
        }


        String bcryptPassword = passwordEncoder.encode(password);

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(bcryptPassword);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setBirthDate(LocalDate.parse(birthDate));
        newUser.setGender(gender);
        newUser.setCreatedTime(LocalDate.now().atStartOfDay());
        newUser.setRole(role);
        newUser.setLocation(location);
        newUser.setIsDeleted(false);
        return UserMapper.toDto(userRepository.save(newUser));

    }

    /**
     * Edit user
     * @param userRequestDto firstName, lastName, birthDate, gender, createdAt and role
     * @return userDto user information
     */

    public UserDto editUser(UserRequestDto userRequestDto, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email: " + email + " Not Found"));
        ;

        String firstName = userRequestDto.getFirstName();
        String lastName = userRequestDto.getLastName();
        String birthDate = userRequestDto.getBirthDate();
        Gender gender = userRequestDto.getGender();
        String location = userRequestDto.getLocation();

//        if (Utils.isUserUnder18(birthDate)) {
//            throw new BadRequestException("User is under 18. Please select a different date");
//        }

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBirthDate(LocalDate.parse(birthDate));
        user.setGender(gender);
        user.setLocation(location);

        return UserMapper.toDto(userRepository.save(user));
    }

    /**
     * Disable user
     * @return userDto user information
     */

    public UserDto deletetedUser(int id, String adminEmail) {
        User admin = userRepository.findByEmail(adminEmail)
                .orElseThrow(() -> new NotFoundException("Admin with email: " + adminEmail + " Not Found"));
        ;

        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id: " + id + " Not Found"));


        user.setIsDeleted(false) ;

        return UserMapper.toDto(userRepository.save(user));
    }

}
