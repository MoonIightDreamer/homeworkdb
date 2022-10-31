package repository.jdbc;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import repository.UserRepository;

import java.util.List;

public class UserRepositoryJdbc implements UserRepository {

    private final SimpleJdbcInsert insertUser;

    private final JdbcTemplate jdbcTemplate;

    private static final BeanPropertyRowMapper<User> ROW_MAPPER = BeanPropertyRowMapper.newInstance(User.class);


    public UserRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.insertUser = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean create(User user) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("name", user.getName());
        if (user.isNew()) {
            Number newKey = insertUser.executeAndReturnKey(map);
            user.setId(newKey.intValue());
        }
        return !user.isNew();
    }

    @Override
    public User get(Integer id) {
        List<User> result = jdbcTemplate.query("SELECT * FROM users WHERE id=?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(result);
    }

    @Override
    public boolean delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id=?", id) != 0;
    }
}
