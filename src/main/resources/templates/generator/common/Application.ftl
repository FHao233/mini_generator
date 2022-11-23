package ${packageName};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ${tableInfo.className} {

    public static void main(String[] args) {
        SpringApplication.run(${tableInfo.className}.class, args);
    }
}