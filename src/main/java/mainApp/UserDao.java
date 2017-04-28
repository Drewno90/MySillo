package mainApp;

import org.springframework.beans.factory.annotation.Autowired;


import javax.transaction.Transactional;
@Transactional
public class UserDao implements IUserDao {
    @Autowired
    private HibernateTemplate  hibernateTemplate;
    public void saveUser() {
        User user = new User();
        user.setId(1);
        user.setName("Ram");
        hibernateTemplate.save(user);
    }
}