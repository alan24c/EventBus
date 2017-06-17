package UtilsTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import utils.BeanCopyUtils;

/**
 * Created by alan on 17-6-17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans-config-test.xml")
public class UtilsTest {

    @Test
    public void testBeanCopy(){

        FromClass fromClass = new FromClass();
        fromClass.setIntNums(1);
        fromClass.setLongNums(100L);

        ToClass toClass = (ToClass) BeanCopyUtils.beanCopy(fromClass,ToClass.class);

        Assert.assertEquals(toClass.getIntNums(),fromClass.getIntNums());
        Assert.assertEquals(toClass.getLongNums(),fromClass.getLongNums());


    }
}