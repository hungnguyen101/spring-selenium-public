package com.hung.spring.springselenium.resource;

import com.hung.spring.springselenium.SpringBaseTestNGTest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class ResourceTest extends SpringBaseTestNGTest {

    //@Value("classpath:data/testdata.csv")
    @Value("file:/Users/ruchaburu/spring-selenium/src/test/resources/data/testdata.csv")
    private Resource resource;

    @Value("https://www.google.com")
    private Resource googleURL;

    @Value("https://www.w3.org/TR/PNG/iso_8859-1.txt")
    private Resource textW3;

    @Value("${screenshot.path}/resource_output/some.txt")
    private Path path;

    @Test
    public void absolutePathTest() throws IOException {
        Files.readAllLines(resource.getFile().toPath()).forEach(System.out::println);
    }

    @Test
    public void googleURLTest() throws IOException {
        System.out.println(
                new String(googleURL.getInputStream().readAllBytes())
        );
    }

    @Test
    public void textW3Test() throws IOException {
        System.out.println(
                new String(textW3.getInputStream().readAllBytes())
        );
        FileCopyUtils.copy(resource.getInputStream(), Files.newOutputStream(path));
    }


}
