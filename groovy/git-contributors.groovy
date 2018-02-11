/*
    List of top contributors of the GIT project.

    This small groovy script demonstrates the use of built-in groovy dependency
    management system (Grape) and Feign HTTP library.

    Grape: http://docs.groovy-lang.org/latest/html/documentation/grape.html
    Feign: https://github.com/OpenFeign/feign
 */
@Grab(group='com.netflix.feign', module='feign-gson', version='8.13.0')
import feign.*
import feign.gson.*

GitHub github = Feign.builder().encoder(new GsonEncoder()).decoder(new GsonDecoder())
        .target(GitHub.class, "https://api.github.com");

interface GitHub {
    @RequestLine("GET /repos/{owner}/{repo}/contributors")
    List<Contributor> contributors(@Param("owner") String owner, @Param("repo") String repo)
}

class Contributor {
    String login;
    int contributions;
}

println "Top GitHub contributors:"
github.contributors("gitster", "git")
        .forEach({ println "$it.login ($it.contributions)" })

