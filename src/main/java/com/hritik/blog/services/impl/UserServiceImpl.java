package com.hritik.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.hritik.blog.entities.User;
import com.hritik.blog.exception.ResourceNotFoundException;
import com.hritik.blog.payloads.UserDto;
import com.hritik.blog.repositories.UserRepo;
import com.hritik.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updatedUser = this.userRepo.save(user);
		return this.userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		if(!this.userRepo.findAll().isEmpty()){
		List<User> listOfAllUsers = this.userRepo.findAll();
		List<UserDto> listOfAllUserDto = listOfAllUsers.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return listOfAllUserDto;
		}else {
			throw new ResourceNotFoundException("No user is present in the database.");
		}
	}

	@Override
	public void deleteUser(Integer userId) {
		User userToBeDeleted = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		this.userRepo.delete(userToBeDeleted);
	}

	public User dtoToUser(UserDto userDto){
		User user = this.modelMapper.map(userDto, User.class);
//		User user = new User();
//		user.setUserId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		return user;
	}

	public UserDto userToDto(User user){
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
//		UserDto userDto=new UserDto();
//		userDto.setId(user.getUserId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		return userDto;
	}

}
