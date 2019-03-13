import com.sys.pojo.UserInfo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import javax.annotation.Resource;

public class JunitTest extends BaseJunitTest{

        @Resource
        WebApplicationContext wac;

        MockMvc mockMvc;

        @Autowired
        private MockHttpServletRequest request;



        @Autowired
        private MockHttpSession session;

        @Before
        public void setup() {
            this.mockMvc = webAppContextSetup(this.wac).build();//MockMvcBuilders.webAppContextSetup(wac).build();//webAppContextSetup(this.wac).build();
        }
        @Test  
        public void test0() throws Exception {
            System.out.println("第一个测试方法*******");
             mockMvc.perform((post("/submitLogin").
                    param("username", "wangli").
                    param("password", "123456"))).
                    andExpect(status().isOk())
                    .andDo(print());
        }

        @Test
        public void testMyData(){


            UserInfo userInfo = new UserInfo();
            userInfo.setUsername("王利");
            session.setAttribute("user",userInfo);
           // mockMvc.perform()
        }

    @Test
    public void testTest(){
        try {
            ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/account/test"));
            MvcResult mvcResult = resultActions.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
            String result = mvcResult.getResponse().getContentAsString();
            System.out.println("==========结果为：==========\n" + result + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAdd(){
        try {
            ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/account/add"));
            MvcResult mvcResult = resultActions.andReturn();
            String result = mvcResult.getResponse().getContentAsString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



        @Test
        public void test1(){  
            System.out.println("第二个测试方法*******");  
        }  
          
        @Test  
        public void test2(){  
            System.out.println("第三个测试方法*******");  
        }  
  
}  