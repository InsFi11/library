package beingjavaguys.services;

import beingjavaguys.dao.SecurityUser;
import beingjavaguys.dao.UserDaoImpl;
import beingjavaguys.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService {

	@Autowired
    UserDaoImpl userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        // load user
        User user = userDao.getUserListFromLogin(username).get(0);

        if (user != null) {

            // convert roles

            List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();

                roles.add(new GrantedAuthorityImpl(user.getPrivileges()));


            // initialize user
            SecurityUser securityUser = new SecurityUser(
                    user.getLogin(),
                    user.getPassword(),
                    true, true, true, true,
                    roles
            );

            securityUser.setUser(user);

            return securityUser;
        } else {
            throw new UsernameNotFoundException("No user with username '" + username + "' found!");
        }
    }
	public void insertData(User user) {
		userDao.insertData(user);
	}


	public List<User> getUserList() {
		return userDao.getUserList();
	}


	public void deleteData(String id) {
		userDao.deleteData(id);
		
	}
    public List<User> getUserListFromLoginandPass(String login, String password){return userDao.getUserListFromLoginandPass(login,password);}
    public List<User> getUserListFromEmailandPass(String email, String password){return userDao.getUserListFromEmailandPass(email,password);}
    public List<User> getUserListFromLogin(String login) { return userDao.getUserListFromLogin(login);}
    public List<User> getUserListFromEmail(String email) { return userDao.getUserListFromEmail(email);}
	public User getUser(String id) {
		return userDao.getUser(id);
	}
    public int getUseridFromEmail(String email) {return  userDao.getUseridFromEmail(email);}

    public void updateData(User user) {
		userDao.updateData(user);
		
	}


	
}
