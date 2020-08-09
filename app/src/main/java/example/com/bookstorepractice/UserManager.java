package example.com.bookstorepractice;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class UserManager {
    //添加用户信息（注册用）
    public boolean insertUser(String name, String pwd){
        User user = new User();
        user.setUserName(name);
        user.setUserPwd(pwd);

        //创建数据库
        Connector.getDatabase();
        return user.save();
    }

    //查找用户名，查看用户名是否存在（注册、找回密码用）
    public boolean findUserByName(String name){
        List<User> users = DataSupport.findAll(User.class);
        for (User person: users){
            if (name.trim().equals(person.getUserName())){
                return true;
            }
        }
        return false;
    }

    //查找用户名、密码是否正确匹配（登录、重置密码用）
    public boolean findNameAndPwd(String name, String password){
        List<User> users = DataSupport.findAll(User.class);
        for (User person: users){
            if (name.trim().equals(person.getUserName())&&(password.trim().equals(person.getUserPwd()))){
                return true;
            }
        }
        return false;
    }
}
