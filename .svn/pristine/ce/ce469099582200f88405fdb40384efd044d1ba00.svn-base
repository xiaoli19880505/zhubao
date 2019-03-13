
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sys.pojo.ApplyUserinfo;
import com.sys.service.ApplyUserinfoService;
@Transactional
@Service
public class TestTableService {
   /* @Autowired
    private ApplyUserinfoService applyUserinfoService;*/

    int i = 1;

    /**
     * 测试链接
     */
    @Test
    public void Test1(){
        ApplicationContext cx = null;
        cx = new FileSystemXmlApplicationContext("src/main/resources/applicationContext.xml","src/main/resources/spring-mvc.xml");
        //System.out.println(cx.toString());
        System.out.println(cx);
        System.out.println("-------------------1----------------");
        ApplyUserinfoService applyUserinfoService = (ApplyUserinfoService)cx.getBean("applyUserinfoService");
        Map<String, Object> map = new HashMap<String, Object>();
        //List<TestTable> testTables = testTableService.getListTestTables(map);
        ApplyUserinfo applyUserinfo = applyUserinfoService.selectById("5C57D84E-6C21-008A-E053-C0A86406008G");
        System.out.println("..."+applyUserinfo.getSfzh()+"..."); //测试通过
        System.out.println("-------------------2----------------");
        ApplyUserinfo applyUserinfo3 = applyUserinfoService.selectById("5C57D84E-6C21-008A-E053-C0A86406008G");
    }


}