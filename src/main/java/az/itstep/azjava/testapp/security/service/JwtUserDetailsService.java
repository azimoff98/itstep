package az.itstep.azjava.testapp.security.service;

import az.itstep.azjava.testapp.dao.MemberDao;
import az.itstep.azjava.testapp.security.model.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class JwtUserDetailsService implements UserDetailsService {

    private MemberDao memberDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberDao.findByUsername(username)
                .map(u ->
                        //Rollar elave etmek istesez, null yerine rollar
                        //listini konstruktora oturmek lazimdi
                        new JwtUser(u, null /*List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))*/))
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Autowired
    public void setAppUserDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return memberDao.findByUsername(username)
//                .map(u->
//                    new JwtUser(u, null))
//                .orElseThrow(() -> new UsernameNotFoundException(username));
//    }
}
