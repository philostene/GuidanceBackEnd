package intra.poleemploi.service;

import intra.poleemploi.dao.AppliRepository;
import intra.poleemploi.dao.RoleAppRepository;
import intra.poleemploi.dao.UserAppRepository;
import intra.poleemploi.entities.Appli;
import intra.poleemploi.entities.RoleApp;
import intra.poleemploi.entities.UserApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    @Autowired
    private RoleAppRepository roleAppRepository;
    @Autowired
    private AppliRepository appliRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserAppRepository userAppRepository;

    @Override
    public UserApp saveUserApp(String username, String password, String confirmedPassword) {
        UserApp user = userAppRepository.findUserByUsername(username);
        if(user != null) throw new RuntimeException("User already exist !");
        if(!password.equals(confirmedPassword)) throw new RuntimeException("Please confirm your password !");
        // si user n'existe pas => on le créé
        UserApp userApp = new UserApp();
        userApp.setUsername(username);
        // cryptage du pwd
        userApp.setPassword(bCryptPasswordEncoder.encode(password));
        // save in the BDD
        userAppRepository.save(userApp);
        // user créé donc compte activé
        userApp.setActivated(true);
        // attribue role par défaut à user
        addRoleToUser(username, "USER");
        return userApp;
    }

    @Override
    public void saveRoleApp(RoleApp role) {
        roleAppRepository.save(role);
    }

    @Override
    public UserApp loadUserAppByUsername(String username) {
        return userAppRepository.findUserByUsername(username);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        // récupère user et role
        UserApp userApp = userAppRepository.findUserByUsername(username);
        RoleApp roleApp = roleAppRepository.findRoleByRoleName(roleName);
        // ajout role à user
        userApp.getRoles().add(roleApp);
    }

    @Override
    public void addAppliToUser(String username, String appliName) {
        UserApp userApp = userAppRepository.findUserByUsername(username);
        Appli appli = appliRepository.findAppliByAppliName(appliName);
        userApp.getApplis().add(appli);
    }

    @Override
    public void delAllAppToAllUser() {
        List<UserApp> userAppList = userAppRepository.findAll();
        Collection<Appli> appliList = new ArrayList<Appli>();
        for (UserApp userApp : userAppList) {
            appliList = userApp.getApplis();
            userApp.getApplis().removeAll(appliList);
        }
    }

}
