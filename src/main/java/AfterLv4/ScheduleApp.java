package AfterLv4;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ScheduleApp {
    public static void main(String[] args) {
        SpringApplication.run(ScheduleApp.class, args);
    }
}
