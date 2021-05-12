package ru.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.server.data.UserDto;
import ru.server.models.User;
import ru.server.repositories.IRoleRepository;
import ru.server.repositories.IUserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
    @Autowired
    IUserRepository repository;
    @Autowired
    IRoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User result = repository.findByUsername(s);
        if (result !=null) return repository.findByUsername(s);
        else throw new UsernameNotFoundException("Пользователь не найден");
    }
    public User findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public boolean save(User u) {
        try {
            repository.save(u);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }
    public List<UserDto> getAll() {

        return repository.findAll().stream().map(UserDto::new).collect(Collectors.toList());
    }

    @Override
    public boolean save(UserDto userDto) {
        User toModify;
        User.Role role = roleRepository.findById(userDto.getId()).orElse(null);
        if (role == null) return false;
        if (userDto.getId() != null) {
            toModify = repository.findById(userDto.getId()).orElse(null);
            if (toModify == null) return false;
        } else {
            toModify = new User();
        }
            toModify.setLogin(userDto.getLogin());
            if (userDto.getPassword() != null){
                toModify.setPassword(passwordEncoder.encode(userDto.getPassword()));
            }
            toModify.setRole(role);
            repository.save(toModify);
        return true;
    }
}
