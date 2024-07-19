package test_api;

/**
 * @ClassName FanSheTest
 * @Description TODO
 * @Author @hzp
 * @Date 2024/7/17 11:05
 * @Version 1.0
 */
public class FanSheTest {



    public void fansheTest() {
        // 1.类获取Class
        Class<DataStructure> dataStructureClass = DataStructure.class;
        // 2.对象获取Class
        Class<? extends Class> aClass = dataStructureClass.getClass();
        // 3.工具获取Class
        try {
            Class<?> dataStructure = Class.forName("DataStructure");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
