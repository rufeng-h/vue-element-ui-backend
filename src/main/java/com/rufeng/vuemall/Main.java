package com.rufeng.vuemall;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author 黄纯峰
 * @time 2021-12-09 21:28
 * @package com.rufeng.vuemall
 * @description TODO
 */
public class Main {
    public static void main(String[] args) {
        Path root = Paths.get("E:/");
        Path path = Paths.get("E:/upload/2021-12-10/2bfb8b6b7b2590fdc0755fff3e255d77.jpg");
        URI uri = path.toUri();
        System.out.println(root.toUri().relativize(uri));

        System.out.println(path.getFileName());
    }
}
