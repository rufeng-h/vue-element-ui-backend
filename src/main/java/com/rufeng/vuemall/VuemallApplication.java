package com.rufeng.vuemall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * @author rufeng
 */
@SpringBootApplication
public class VuemallApplication {

    public static void main(String[] args) {
        SpringApplication.run(VuemallApplication.class);
    }

}
