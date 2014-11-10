package beingjavaguys.services;

import beingjavaguys.dao.DuplicatePermissionException;
import beingjavaguys.dao.PermissionNotFoundException;
import beingjavaguys.dao.PermissionsDAOImpl;
import beingjavaguys.domain.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class PermissionServiceImpl {


    @Autowired
    private PermissionsDAOImpl permissionDAO;


    public void addPermission(Permission permission) throws DuplicatePermissionException {
        permissionDAO.addPermission(permission);
    }


    public Permission getPermission(int id) throws PermissionNotFoundException {
        return permissionDAO.getPermission(id);
    }


    public Permission getPermission(String permissionname) throws PermissionNotFoundException {
        return permissionDAO.getPermission(permissionname);
    }


    public void updatePermission(Permission permission) throws PermissionNotFoundException {
        permissionDAO.updatePermission(permission);
    }


    public void deletePermission(int id) throws PermissionNotFoundException {
        permissionDAO.deletePermission(id);
    }


    public List<Permission> getPermissions() {
        return permissionDAO.getPermissions();
    }
}
