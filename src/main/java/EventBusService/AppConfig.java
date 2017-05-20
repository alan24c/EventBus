package EventBusService;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by alan on 17-5-20.
 */
@Configuration
@MapperScan(value="EventBusDAO")
public class AppConfig {
}
