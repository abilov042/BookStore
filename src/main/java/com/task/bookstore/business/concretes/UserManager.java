package com.task.bookstore.business.concretes;

import com.task.bookstore.business.abstracts.UserService;
import com.task.bookstore.core.excepstions.config.ExistsUserException;
import com.task.bookstore.core.result.DataResult;
import com.task.bookstore.core.result.Result;
import com.task.bookstore.core.result.SuccessDataResult;
import com.task.bookstore.core.result.SuccessResult;
import com.task.bookstore.core.security.entities.UserDetailsImpl;
import com.task.bookstore.core.security.jwt.JWTUtils;
import com.task.bookstore.dataAccess.abstracts.RoleDao;
import com.task.bookstore.dataAccess.abstracts.UserDao;
import com.task.bookstore.entity.concretes.dtos.request.LoginRequest;
import com.task.bookstore.entity.concretes.dtos.request.SignupRequest;
import com.task.bookstore.entity.concretes.dtos.response.UserInfoResponse;
import com.task.bookstore.entity.concretes.roles.ERole;
import com.task.bookstore.entity.concretes.roles.Role;
import com.task.bookstore.entity.concretes.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserManager implements UserService {
    private final AuthenticationManager authenticationManager;

    private final UserDao userDao;

    private final RoleDao roleDao;

    private final PasswordEncoder encoder;

    private final JWTUtils jwtUtils;
    @Override
    public DataResult<UserInfoResponse> login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return new SuccessDataResult<>(new UserInfoResponse(
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                jwt,
                roles));
    }

    @Override
    public Result register(SignupRequest signupRequest) {
        if (userDao.existsByUsername(signupRequest.getUsername())) {
            throw new ExistsUserException("Error: Username is already taken!");
        }

        if (userDao.existsByEmail(signupRequest.getEmail())) {
            throw new ExistsUserException("Error: Email is already in use!");
        }

        // Create new user's account
        User user = new User(signupRequest.getUsername(),
                signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword()));

        Set<String> strRoles = signupRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleDao.findByName(ERole.STUDENT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "student":
                        Role adminRole = roleDao.findByName(ERole.AUTHOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "author":
                        Role modRole = roleDao.findByName(ERole.STUDENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;

                }
            });
        }

        user.setRoles(roles);
        userDao.save(user);


        return new SuccessResult("User successfully added");
    }
}
