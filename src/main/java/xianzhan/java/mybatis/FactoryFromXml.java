package xianzhan.java.mybatis;

import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import xianzhan.java.mybatis.entity.Users;
import xianzhan.java.mybatis.mapper.UsersMapper;

import java.io.InputStream;

/**
 * Mybatis 例子
 *
 * @author xianzhan
 * @date 2021-04-04
 */
public class FactoryFromXml {

    public static void main(String[] args) throws Exception {

        // 1. 加载配置
        final String path = "mybatis/config.xml";
        InputStream is = Resources.getResourceAsStream(path);
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(is);
        Configuration configuration = xmlConfigBuilder.parse();

        // 2. 创建 session
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(configuration);
        try (SqlSession session = factory.openSession()) {
            // 3. 绑定 mapper
            UsersMapper usersMapper = session.getMapper(UsersMapper.class);
            // 4. 查询数据
            Users users = usersMapper.selectUser(1L);
            System.out.println(users);
        }
    }
}
