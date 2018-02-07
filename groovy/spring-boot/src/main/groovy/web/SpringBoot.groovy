package web

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@SpringBootApplication
class SpringBoot {
    static void main(String[] args) {
        SpringApplication.run(SpringBoot.class, args);
    }

    @RequestMapping("/")
    Object index() {
        [
                version    : '1.0',
                description: 'index page',
                author     : [
                        name : 'Jafar Sadik',
                        email: 'dzafar.sadik@gmail.com'
                ]
        ]
    }
}
