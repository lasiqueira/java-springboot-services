package br.unisinos.apps4business.users.service;

import br.unisinos.apps4business.users.enumerators.Role;
import br.unisinos.apps4business.users.model.User;
import br.unisinos.apps4business.users.model.UserGroup;
import br.unisinos.apps4business.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> fetchAllUsers(){
        List<User> list = new ArrayList<>();
        userRepository.findAll().forEach(list::add);
        return list;
    }

    public List<User> findByUserGroup(UserGroup userGroup) {
        return userRepository.findByUserGroups(userGroup);
    }

    public List<User> findByRole(Role role){
        return userRepository.findByRole(role);
    }
    public User findById(Long id){
        return userRepository.findById(id).orElse(null);
    }
    public User createUser(User user){
       return userRepository.save(user);
    }
    public User updateUser(Long id, User user){
        if(userRepository.existsById(id)){
            user.setId(id);
            return userRepository.save(user);
        } else{
            throw new EntityNotFoundException();
        }
    }

    public void deleteUser(Long id){
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else{
            throw new EntityNotFoundException();
        }
    }
}
