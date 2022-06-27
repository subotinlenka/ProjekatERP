package erp.service.implementation;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import erp.dto.UserDto;
import erp.dto.UserCreateUpdateDto;
import erp.exception.BadRequestException;
import erp.exception.ConflictException;
import erp.exception.NotFoundException;
import erp.model.User;
import erp.model.Role;
import erp.repository.RoleRepository;
import erp.repository.UserRepository;
import erp.service.UserService;


@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	public UserServiceImplementation(ModelMapper modelMapper, UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.modelMapper = modelMapper;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public List<UserDto> getUsers() {
		return userRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
	}

	@Override
	public UserDto getUserById(Integer userId) throws Exception {
		User user = userRepository.findUserById(userId);
        if(user != null)
        {
            return convertEntityToDto(user);
        }
        throw new NotFoundException("User with forwarded ID does not exist!");
	}

	@Override
	public UserDto getUserByUserName(String userUserName) {
		User user = userRepository.findUserByUserName(userUserName);
        if(user != null)
        {
            return convertEntityToDto(user);
        }
        throw new NotFoundException("User with forwarded user name does not exist!");
	}
	
	@Override
	public List<UserDto> getUserByRoleId(Integer roleId) {
		List<User> users = userRepository.findUsersByRoleId(roleId);
		Type listType = new TypeToken<List<UserDto>>(){}.getType();
		List<UserDto> userDtoList = modelMapper.map(users,listType);
		if(!users.isEmpty())
        {
            return userDtoList;
        }
        throw new NotFoundException("Users with forwarded Role ID do not exist!");
	}
	
	@Override
	public List<UserDto> getCustomers() {
		return userRepository.findCustomers().stream().map(this::convertEntityToDto).collect(Collectors.toList());
	}
	
	@Override
	public List<UserDto> getAdmins() {
		return userRepository.findAdmins().stream().map(this::convertEntityToDto).collect(Collectors.toList());
	}


	@Override
	public void insertUser(UserCreateUpdateDto userCreateDto, String roleName) {
		
		 if(userRepository.findUserByEmail(userCreateDto.getUserEmail()) != null)
	     {
			 throw new BadRequestException("User with inserted Email already exists!");
	     }
	     if(userRepository.findUserByUserName(userCreateDto.getUserUserName()) != null)
	     {
	         throw new BadRequestException("User with inserted User name already exists!");
	     }
	   
	     Role role = roleRepository.findRoleByName(roleName);
	     if (role == null) {
	    	 throw new BadRequestException("The inserted name of Role does not exist!");
	     }
		
		 if(userCreateDto.getUserFirstName() == null)
	        throw new BadRequestException("User first name is required field!");
	     else if(userCreateDto.getUserLastName() == null)
	        throw new BadRequestException("User last name is required field!");
	     else if(userCreateDto.getUserDateOfBirth() == null)
	        throw new BadRequestException("User date of birth is required field!");
	     else if(userCreateDto.getUserAddress() == null)
	        throw new BadRequestException("User address is required field!");
	     else if(userCreateDto.getUserCity() == null)
	        throw new BadRequestException("User city is required field!");
	     else if(userCreateDto.getUserCountry() == null)
	        throw new BadRequestException("User country is required field!");
	     else if(userCreateDto.getUserEmail() == null)
	        throw new BadRequestException("User email is required field!");
	     else if(userCreateDto.getUserUserName() == null)
	        throw new BadRequestException("User's user name is required field!");
	     else if(userCreateDto.getUserPassword() == null)
	        throw new BadRequestException("User password is required field!");
		
		User user = convertCreateUpdateDtoToEntity(userCreateDto);
		user.setRole(role); 
		user.setUserPassword(passwordEncoder.encode(userCreateDto.getUserPassword()));
		userRepository.save(user);
	}

	@Override
	public void modifyUser(UserCreateUpdateDto userUpdateDto, Integer userId) {
		
		 if(userRepository.findUserByEmail(userUpdateDto.getUserEmail()) != null)
	     {
			 throw new BadRequestException("User with inserted Email already exists!");
	     }
	     if(userRepository.findUserByUserName(userUpdateDto.getUserUserName()) != null)
	     {
	         throw new BadRequestException("User with inserted User name already exists!");
	     }
		
		User user = userRepository.findUserById(userId);
        if(user == null)
            throw new NotFoundException("User with forwarded ID does not exist!");
    
        user.setUserFirstName(userUpdateDto.getUserFirstName());
        user.setUserLastName(userUpdateDto.getUserLastName());
        user.setUserDateOfBirth(userUpdateDto.getUserDateOfBirth());
        user.setUserPhoneNumber(userUpdateDto.getUserPhoneNumber());
        user.setUserAddress(userUpdateDto.getUserAddress());
        user.setUserCity(userUpdateDto.getUserCity());
        user.setUserCountry(userUpdateDto.getUserCountry());
        user.setUserEmail(userUpdateDto.getUserEmail());
        user.setUserUserName(userUpdateDto.getUserUserName());
        user.setUserPassword(passwordEncoder.encode(userUpdateDto.getUserPassword()));
        
        if(user.getUserFirstName() == null)
        	throw new BadRequestException("User first name is required field!");
        else if(user.getUserLastName() == null)
        	throw new BadRequestException("User last name is required field!");
        else if(user.getUserDateOfBirth() == null)
        	throw new BadRequestException("User date of birth is required field!");
        else if(user.getUserAddress() == null)
        	throw new BadRequestException("User address is required field!");
        else if(user.getUserCity() == null)
        	throw new BadRequestException("User city is required field!");
        else if(user.getUserCountry() == null)
        	throw new BadRequestException("User country is required field!");
        else if(user.getUserEmail() == null)
        	throw new BadRequestException("User email is required field!");
        else if(user.getUserUserName() == null)
        	throw new BadRequestException("User user name is required field!");
        else if(user.getUserPassword() == null)
        	throw new BadRequestException("User password is required field!");
        
        userRepository.save(user);		
	}

	@Override
	public void deleteUser(Integer userId) throws Exception {
		
		User user = userRepository.findUserById(userId);
        if (user != null) {
        	try {
        		userRepository.deleteUserById(userId);
        	}catch(Exception e) {
        		throw new ConflictException("User with forwarded ID is used in other table!");
        	}
        }else
            throw new NotFoundException("User with forwarded ID does not exist!");
	}
	
	//Mapping User Entity to UserDto
	public UserDto convertEntityToDto(User user) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;	
	}
		
	//Mapping UserDto to User Entity
	public User convertDtoToEntity(UserDto userDto) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}
		
	//Mapping User Entity to UserCreateUpdateDto
	public UserCreateUpdateDto convertEntityToCreateUpdateDto(User user) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setAmbiguityIgnored(true);
		UserCreateUpdateDto userCreateUpdateDto = this.modelMapper.map(user, UserCreateUpdateDto.class);
		return userCreateUpdateDto;	
	}

	//Mapping UserCreateUpdateDto to User Entity
	public User convertCreateUpdateDtoToEntity(UserCreateUpdateDto userCreateUpdateDto) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setAmbiguityIgnored(true);
		User user = this.modelMapper.map(userCreateUpdateDto, User.class);
		return user;
	}

}
