package hr.markic.library.service.impl;

import hr.markic.library.domain.Contact;
import hr.markic.library.domain.User;
import hr.markic.library.dto.UserDTO;
import hr.markic.library.mapper.UserMapper;
import hr.markic.library.repository.UserRepository;
import hr.markic.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findOne(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> findOneByIDNumber(String idNumber) {
        return userRepository.findByIdentityCardId(idNumber);
    }

    @Override
    public Optional<User> findOneByUsername(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public UserDTO saveUser(UserDTO userDto) {
        User user = userMapper.toEntity(userDto);

        Set<Contact> contacts = user.getContacts();
        if (Objects.nonNull(contacts)){
            for (Contact contact : contacts) {
                contact.setUser(user);
            }
        }

        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
