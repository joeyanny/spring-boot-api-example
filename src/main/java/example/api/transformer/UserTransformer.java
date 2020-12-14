package example.api.transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import example.api.dao.UserEntity;
import example.api.model.User;

/**
 * Transformer used to convert between the resource and data
 * store representations. This is used to decouple the API
 * endpoints from the data store.
 */
@Component
public class UserTransformer {

    /**
     * Transform from the resource representation to the data store representation
     * 
     * @param user Data model representing the resource
     * @return entity Data model representing the data store
     */
    public UserEntity transform(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());

        return entity;
    }

    /**
     * Transform from the data store representation to the resource representation
     * 
     * @param entityList Data model representing the data store
     * @return userList Data model representing the resource
     */
    public List<User> transform(Iterable<UserEntity> entityList) {
        List<User> userList = new ArrayList<>();

        entityList.forEach(new Consumer<UserEntity>() {
            @Override
            public void accept(UserEntity entity) {
                userList.add(transform(entity));
            }
        });

        return userList;
    }

    /**
     * Transform from the data store representation to the resource representation
     * 
     * @param entity Data model representing the data store
     * @return user Data model representing the resource
     */
    public User transform(Optional<UserEntity> optionalEntity) {
        UserEntity entity = optionalEntity.get();
        return entity == null ? null : transform(entity);
    }

    /**
     * Transform from the data store representation to the resource representation
     * 
     * @param entity Data model representing the data store
     * @return user Data model representing the resource
     */
    public User transform(UserEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());

        return user;
    }
}
