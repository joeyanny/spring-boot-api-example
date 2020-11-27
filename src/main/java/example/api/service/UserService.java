package example.api.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.api.dao.UserEntity;
import example.api.dao.UserRepository;
import example.api.model.User;
import example.api.transformer.UserTransformer;

/**
 * Handles the processing of a user resource
 */
@Service
public class UserService {

	@Autowired
    private UserRepository userRepository;

	@Autowired
    private UserTransformer userTransformer;

	/**
     * Query the data source to retrieve a list of users.
     * 
     * @return users
     * @throws SQLException
     */
    public List<User> getAll() throws ServiceException {
		Iterable<UserEntity> entityList = userRepository.findAll();
		return userTransformer.transform(entityList);
    }

    /**
     * Query the data source to retrieve a user by their ID.
     * 
     * @param id
     * @return user
     * @throws SQLException
     */
    public User get(long id) throws ServiceException {
		Optional<UserEntity> entity = userRepository.findById(id);
		return userTransformer.transform(entity);
    }

    /**
     * Store a new user in the data source.
     * Note: An ID will be auto-generated for the user.
     * 
     * @param user
     * @throws SQLException
     */
    public void save(User user) throws ServiceException {
		UserEntity entity = userTransformer.transform(user);
		userRepository.save(entity);
    }

    /**
     * Update an existing user within the data source.
     * Note: This will overwrite every field.
     * 
     * @param id
     * @param user
     * @throws SQLException
     */
    public void update(User user) throws ServiceException {
		UserEntity entity = userTransformer.transform(user);
		userRepository.save(entity);
    }

    /**
     * Delete a user with a matching ID
     * 
     * @param id
     * @throws SQLException
     */
    public void delete(long id) throws ServiceException {
		userRepository.deleteById(id);
    }
}
