package school21.spring.service.repositories;

import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;


public class UsersRepositoryJdbcTemplateImpl implements UsersRepository{

    private JdbcTemplate jdbcTemplate;

    final String getUserId =  "SELECT * FROM public.emails WHERE userid = ";
    final String getUserEmail =  "SELECT * FROM public.emails WHERE email = ";
    final String selectAll =  "SELECT * FROM public.emails";
    final String updUser =  "UPDATE public.emails SET ";
    final String delUser =  "DELETE FROM public.emails WHERE userid = ";

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User findById(Long id) {
        return jdbcTemplate.queryForObject(getUserId + id, new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public List<User> findAll() {
        List<User> list = jdbcTemplate.query(selectAll, new BeanPropertyRowMapper<>(User.class));
        return list;
    }

    @Override
    public void save(User entity) {
        jdbcTemplate.update(String.format("INSERT INTO public.emails VALUES ('%d', %s);", entity.getIdentifier(), entity.getEmail()));
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update(updUser + "email = " + '\'' + entity.getEmail() + '\'' + " WHERE userid = " + entity.getIdentifier());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(delUser + id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.of(jdbcTemplate.queryForObject(getUserEmail + email, new Object[]{email}, new BeanPropertyRowMapper<>(User.class)));
    }
}
