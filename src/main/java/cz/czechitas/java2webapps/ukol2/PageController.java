package cz.czechitas.java2webapps.ukol2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@Controller
public class PageController {
    private final Random random = new Random();

    @GetMapping("/")
    public ModelAndView pageView() throws IOException {
        int randomNumber = random.nextInt(4) + 1;
        ModelAndView result = new ModelAndView("index");

        List<String> citaty = readAllLines("citaty.txt");
        int randomCitat = random.nextInt(citaty.size());
        String quote = citaty.get(randomCitat);

        result.addObject("image", randomNumber);
        result.addObject("quote", quote);
        return result;
    }

    private static List<String> readAllLines(String resource) throws IOException {

        ClassLoader classLoader=Thread.currentThread().getContextClassLoader();

        try(InputStream inputStream=classLoader.getResourceAsStream(resource);
            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))){

            return reader
                    .lines()
                    .collect(Collectors.toList());
        }
    }
}
