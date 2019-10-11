package ru.graduation.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.graduation.model.User;
import ru.graduation.service.UserService;
import ru.graduation.to.UserTo;

import java.util.List;

import static ru.graduation.util.ValidationUtil.assureIdConsistent;
import static ru.graduation.util.ValidationUtil.checkNew;

public abstract class AbstractUserConroller {

    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    public List<User> getAll() {
        LOG.info("getAll");
        return service.getAll();
    }

    public User get(int id) {
        LOG.info("get {}", id);
        return service.get(id);
    }

    public User create(User user) {
        LOG.info("create {}", user);
        checkNew(user);
        return service.create(user);
    }

    public void delete(int id) {
        LOG.info("delete {}", id);
        service.delete(id);
    }

    public void update(User user, int id) {
        LOG.info("update {} with id={}", user, id);
        assureIdConsistent(user, id);
        service.update(user);
    }


    public void update(UserTo userTo, int id) {
        LOG.info("update {} with id={}",userTo,id);
        assureIdConsistent(userTo,id);
        service.update(userTo);
    }

    public User getByMail(String email) {
        LOG.info("getByEmail {}", email);
        return service.getByEmail(email);
    }

    public void enable(int id, boolean enabled) {
        LOG.info(enabled ? "enable {}" : "disable {}", id);
        service.enable(id, enabled);
    }
}
